package com.example.muzzandroidexercise.ui.muzz

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.DoneAll
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.muzzandroidexercise.data.MuzzMessage
import com.example.muzzandroidexercise.ui.theme.MuzzAndroidExerciseTheme
import com.example.muzzandroidexercise.ui.theme.MuzzDarkTextColor
import com.example.muzzandroidexercise.ui.theme.MuzzLightGreyTextColor
import com.example.muzzandroidexercise.ui.theme.MuzzLightTextColor
import com.example.muzzandroidexercise.ui.theme.MuzzMessageSentReceiptColor
import com.example.muzzandroidexercise.ui.theme.MuzzMoreIcon
import com.example.muzzandroidexercise.ui.theme.MuzzPink
import com.example.muzzandroidexercise.ui.theme.MuzzReceivedChatItemBackgroundColor
import com.example.muzzandroidexercise.ui.theme.MuzzTextFieldUnFocusedBorderColor


@Preview(showSystemUi = true)
@Composable
fun MuzzAppPreview() {
    MuzzAndroidExerciseTheme {
        MuzzApp(
            muzzChatUiState = MuzzChatUiState(),
            onSendChatClickListener = {},
            modifier = Modifier
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MuzzApp(
    muzzChatUiState: MuzzChatUiState,
    onSendChatClickListener: (String) -> Unit,
    modifier: Modifier = Modifier,
    onMoreClick: () -> Unit = {}
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                modifier = Modifier.shadow(elevation = 8.dp),
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                ),
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                            contentDescription = "Back",
                            tint = MuzzPink,
                            modifier = Modifier.size(60.dp)
                        )
                    }
                },
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Avatar(name = "Timi")
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = "Timi",
                            fontWeight = FontWeight.SemiBold,
                            color = MuzzDarkTextColor
                        )
                    }
                },
                actions = {
                    IconButton(onClick = onMoreClick) {
                        Icon(
                            imageVector = Icons.Filled.MoreHoriz,
                            contentDescription = "More",
                            tint = MuzzMoreIcon
                        )
                    }
                }
            )
        },
        bottomBar = {

            BottomAppBar(
                containerColor = Color.White,
                tonalElevation = 18.dp,
                modifier = Modifier
                    .shadow(elevation = 18.dp)
                    .height(135.dp)
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    MuzzChatBox(
                        modifier = Modifier
                            .fillMaxWidth(),
                        onSendChatClickListener,
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

        }
    ) { innerPadding ->
        Surface(
            color = Color.White
        ) {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {

                val listState = rememberLazyListState()
                LaunchedEffect(muzzChatUiState.messages.size) {
                    listState.animateScrollToItem(muzzChatUiState.messages.size)
                }
                LazyColumn(
                    state = listState,
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentPadding = PaddingValues(16.dp)
                ) {
                    items(muzzChatUiState.messages) { message ->
                        if (message.getDurationToLastMessage.second < 1) {
                            Spacer(modifier = Modifier.height(8.dp))
                            Row(
                                horizontalArrangement = Arrangement.Center,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text = message.dayOfMessage,
                                    color = MuzzLightGreyTextColor,
                                    fontWeight = FontWeight.SemiBold
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = message.hourAndMinuteOfMessage,
                                    color = MuzzLightGreyTextColor,
                                    fontWeight = FontWeight.Normal
                                )
                            }

                            Spacer(modifier = Modifier.height(8.dp))
                        }
                        ChatItem(message = message)
                    }
                }

            }
        }


    }
}


@Composable
fun Avatar(name: String?) {
    val gradient = Brush.verticalGradient(
        colors = listOf(
            Color(0xFFfd277b),
            Color(0xFFfc3d7b),
            Color(0xFFfd656e)
        )
    )
    val initials = name
        ?.split(" ")
        ?.mapNotNull { it.firstOrNull()?.toString() }
        ?.take(2)
        ?.joinToString("")
        ?: "?"
    Box(
        modifier = Modifier
            .size(30.dp)
            .clip(CircleShape)
            .background(brush = gradient),
        contentAlignment = Alignment.Center
    ) {
        Text(text = initials.uppercase(), color = Color.White, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun ChatItem(message: MuzzMessage) {
    val paddingValues = if (message.isFromMe) {
        PaddingValues(
            start = 10.dp,
            top = 4.dp,
            end = 10.dp,
            bottom = 4.dp
        )
    } else {
        PaddingValues(
            start = 10.dp,
            top = 16.dp,
            end = 10.dp,
            bottom = 8.dp
        )
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        Box(
            modifier = Modifier
                .align(if (message.isFromMe) Alignment.End else Alignment.Start)
                .clip(
                    RoundedCornerShape(
                        topStart = 48f,
                        topEnd = 48f,
                        bottomStart = if (message.isFromMe) 48f else 0f,
                        bottomEnd = if (message.isFromMe) 0f else 48f
                    )
                )
                .background(if (message.isFromMe) MuzzPink else MuzzReceivedChatItemBackgroundColor)
                .padding(
                    paddingValues
                )
        ) {
            Column {
                Text(
                    text = message.content,
                    color = if (message.isFromMe) MuzzLightTextColor else MuzzDarkTextColor,
                    modifier = Modifier.padding(paddingValues = PaddingValues(end = 10.dp))
                )
                Spacer(modifier = Modifier.height(1.dp))
                if (message.isFromMe) {
                    Icon(
                        imageVector = Icons.Default.DoneAll,
                        contentDescription = "",
                        tint = MuzzMessageSentReceiptColor,
                        modifier = Modifier
                            .height(10.dp)
                            .width(10.dp)
                            .align(Alignment.End)
                    )
                }

            }

        }
    }
}

@Composable
fun MuzzChatBox(
    modifier: Modifier = Modifier,
    sendMessage: (String) -> Unit = {}
) {
    var chatBoxValue by remember { mutableStateOf(TextFieldValue("")) }

    val sendGradient = Brush.verticalGradient(
        colors = listOf(
            Color(0xFFfd277b),
            Color(0xFFfc3d7b),
            Color(0xFFfd656e)
        )
    )

    Row(
        modifier = modifier.padding(
            paddingValues = PaddingValues(
                vertical = 16.dp,
                horizontal = 6.dp
            )
        )
    ) {
        OutlinedTextField(
            value = chatBoxValue,
            onValueChange = { newText ->
                chatBoxValue = newText
            },
            modifier = Modifier
                .weight(1f)
                .height(75.dp)
                .padding(8.dp),
            shape = RoundedCornerShape(24.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MuzzPink,
                unfocusedBorderColor = MuzzTextFieldUnFocusedBorderColor,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
            ),
            placeholder = {
                Text(text = "")
            }
        )
        Spacer(modifier = Modifier.width(8.dp))
        IconButton(
            onClick = {
                val msg = chatBoxValue.text
                if (msg.isBlank()) return@IconButton
                sendMessage(chatBoxValue.text)
                chatBoxValue = TextFieldValue("")
            },
            enabled = chatBoxValue.text.isNotBlank(),
            modifier = Modifier
                .clip(CircleShape)
                .background(brush = sendGradient)
                .align(Alignment.CenterVertically)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.Send,
                tint = Color.White,
                contentDescription = "Send",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            )
        }
    }
}
