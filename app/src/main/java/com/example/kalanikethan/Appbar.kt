package com.example.kalanikethan

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.ButtonDefaults


@Composable
fun Appbar(modifier: Modifier = Modifier) {
    var selectedScreen by remember { mutableStateOf("screen1") } // Track selected screen

    Surface(
        modifier = modifier.fillMaxHeight(),
        color = Color(0xFF98999D), // AppBar background color
        shape = RoundedCornerShape(8.dp),
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo), // Replace with your logo
                contentDescription = "App Logo",
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Button 1
            AppBarButton(
                text = "Screen 1",
                isSelected = selectedScreen == "screen1",
                onClick = { selectedScreen = "screen1" }
            )

            // Button 2
            AppBarButton(
                text = "Screen 2",
                isSelected = selectedScreen == "screen2",
                onClick = { selectedScreen = "screen2" }
            )

            // Add more buttons as needed
        }
    }
}

@Composable
fun AppBarButton(text: String, isSelected: Boolean, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        colors = if (isSelected) {
            ButtonDefaults.buttonColors(backgroundColor = Color.Transparent)
        } else {
            ButtonDefaults.buttonColors(backgroundColor = Color(0xFF646569)) // Non-selected color
        },
        shape = RoundedCornerShape(0.dp) // No rounded corners for extending background
    ) {
        Text(
            text = text,
            color = if (isSelected) Color(0xFF98999D) else Color.White, // Text color
            fontSize = 16.sp
        )
    }
}
