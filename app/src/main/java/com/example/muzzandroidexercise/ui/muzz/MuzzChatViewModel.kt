package com.example.muzzandroidexercise.ui.muzz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.muzzandroidexercise.data.MuzzMessage
import com.example.muzzandroidexercise.repositories.MessageRepository
import com.example.muzzandroidexercise.utils.TimeUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MuzzChatViewModel @Inject constructor(private val messageRepository: MessageRepository) :
    ViewModel() {

    private val _uiState = MutableStateFlow(MuzzChatUiState(isLoading = true))

    private val _textQuestions = mutableListOf(
        "What about yesterday?",
        "Can you tell me what inside your head?",
        "Lately, I've been wondering if I can really do anything, do you?",
        "You know fear is often just an illusion, have you ever experienced it?",
        "If you were me, what would you do?"
    )

    private val _delayMillisBetweenMessages = mutableListOf(
        1000L, 1500L, 2000L, 2500L, 3000L
    )

    val uiState: StateFlow<MuzzChatUiState> = _uiState

    init {
        // Simulate loading messages
        loadMessages()
    }

    private fun loadMessages() = viewModelScope.launch {
        viewModelScope
        messageRepository.allItems.collect { messageList ->
            _uiState.value = MuzzChatUiState(messages = messageList, isLoading = false)
        }

        if (_uiState.value.messages.isEmpty()) {
            sendMessage("Hello! How are you feeling today?", senderId = 1)
        }
    }

    fun onUserSendMessage(message: String) {
        viewModelScope.launch {
            sendMessage(message, senderId = -1)
            // Simulate bot response after a delay
            delay(_delayMillisBetweenMessages.random())
            sendBotResponse()
        }
    }

    private fun sendMessage(message: String, senderId: Int = -1) = viewModelScope.launch {
        val timeUtils = TimeUtils()
        val lastMessageTime: Long? = _uiState.value.messages.lastOrNull()?.timestamp
        val currentTime = timeUtils.now()
        val diff = if (lastMessageTime != null) timeUtils.diffMillis(
            startTime = lastMessageTime,
            endTime = currentTime
        ) else 0L
        val message = MuzzMessage(
            content = message,
            senderId = senderId,
            timestamp = currentTime,
            durationToLastMessage = diff
        )
        messageRepository.insertMessage(message)
    }

    private fun sendBotResponse() = viewModelScope.launch {
        val text = getRandomText()
        sendMessage(text, senderId = 1)
    }

    private fun getRandomText(): String {
        val textResponse = if (_textQuestions.isEmpty()) {
            "no further questions, please leave me alone"
        } else {
            _textQuestions.random()
        }

        if (_textQuestions.isNotEmpty()) _textQuestions.remove(textResponse)
        return textResponse
    }


}

data class MuzzChatUiState(
    val messages: List<MuzzMessage> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)