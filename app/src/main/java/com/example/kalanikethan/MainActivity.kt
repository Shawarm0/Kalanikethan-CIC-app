package com.example.kalanikethan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.kalanikethan.ui.theme.KalanikethanTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KalanikethanTheme {
                var selectedScreen by remember { mutableStateOf("signIn") } // Default screen

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Row(Modifier.fillMaxSize()) {
                        Appbar(
                            modifier = Modifier.padding(innerPadding),
                            onScreenSelected = { screen ->
                                selectedScreen = screen
                            }
                        )

                        when (selectedScreen) {
                            "signIn" -> SignInScreen(context = applicationContext)
                            "add" -> Add(context = applicationContext)
                            "whoin" -> Whoin()
                            "History" -> History()
                            // Add more cases for other screens
                        }
                    }
                }
            }
        }
    }
}



