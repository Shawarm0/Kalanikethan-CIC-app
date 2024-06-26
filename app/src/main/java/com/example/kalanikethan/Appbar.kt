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
import androidx.compose.runtime.Composable


@Composable
fun Appbar(
    modifier: Modifier = Modifier,
    onScreenSelected: (String) -> Unit // Add this parameter
) {
    var selectedScreen by remember { mutableStateOf("screen1") } // Track selected screen

    Surface(
        modifier = modifier.height(500.dp).width(200.dp),
        color = Color(0xFF135897), // AppBar background color
        shape = RoundedCornerShape(0.dp),
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo), // Replace with your logo
                contentDescription = "App Logo",
                modifier = Modifier.size(100.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))

            // Inside Appbar composable
            AppBarButton(
                symbol = "⎘",
                text = " Sign In",
                isSelected = selectedScreen == "screen1",
                onClick = {
                    selectedScreen = "screen1"
                    onScreenSelected("signIn")
                }
            )


            // Button 2
            AppBarButton(
                symbol = "✚",
                text = " Add",
                isSelected = selectedScreen == "screen2",
                onClick = {
                    selectedScreen = "screen2"
                    onScreenSelected("add")
                }
            )

            // Button 3
            AppBarButton(
                symbol = " ?",
                text = " Who's In",
                isSelected = selectedScreen == "screen3",
                onClick = {
                    selectedScreen = "screen3"
                    onScreenSelected("whoin")
                }
            )

//            // Button 3
//            AppBarButton(
//                symbol = " ?",
//                text = " History",
//                isSelected = selectedScreen == "screen4",
//                onClick = {
//                    selectedScreen = "screen4"
//                    onScreenSelected("History")
//                }
//            )

            // Add more buttons as needed
        }
    }
}

@Composable
fun AppBarButton(symbol: String, text: String, isSelected: Boolean, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.width(150.dp),
        colors = if (isSelected) {
            ButtonDefaults.buttonColors(containerColor = Color(
                0xFF1b69b2))
        } else {
            ButtonDefaults.buttonColors(containerColor = Color.Transparent) // Non-selected color
        },
        shape = RoundedCornerShape(8.dp) // Rounded corners with 8.dp radius
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = symbol,
                color = if (isSelected) Color.White else Color.White, // Text color
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.width(8.dp)) // Adjust spacing as needed
            Text(
                text = text,
                color = if (isSelected) Color.White else Color.White, // Text color
                fontSize = 16.sp,
                modifier = Modifier.padding(end = 8.dp) // Add padding between symbol and text
            )
        }
    }
}
