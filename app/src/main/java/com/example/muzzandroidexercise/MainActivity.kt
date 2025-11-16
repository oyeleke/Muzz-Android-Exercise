package com.example.muzzandroidexercise

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.muzzandroidexercise.ui.muzz.MuzzApp
import com.example.muzzandroidexercise.ui.muzz.MuzzChatViewModel
import com.example.muzzandroidexercise.ui.theme.MuzzAndroidExerciseTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MuzzChatViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MuzzAndroidExerciseTheme {
                val uiState by viewModel.uiState.collectAsStateWithLifecycle()
                MuzzApp(
                    muzzChatUiState = uiState,
                    onSendChatClickListener = { message ->
                        viewModel.onUserSendMessage(message = message)
                    },
                )
            }
        }
    }
}
