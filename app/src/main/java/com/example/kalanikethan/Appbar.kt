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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun CustomIcon(iconResId: Int, contentDescription: String) {
    // Load the custom icon using painterResource
    val painter = painterResource(id = iconResId)

    // Display the icon using Icon composable
    Icon(
        painter = painter,
        contentDescription = contentDescription,
        modifier = Modifier.padding(0.dp).size(25.dp) // Adjust padding as needed
    )
}

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
                iconResId = R.drawable.login,
                text = " Sign In",
                isSelected = selectedScreen == "screen1",
                onClick = {
                    selectedScreen = "screen1"
                    onScreenSelected("signIn")
                }
            )


            // Button 2
            AppBarButton(
                iconResId = R.drawable.add,
                text = " Add",
                isSelected = selectedScreen == "screen2",
                onClick = {
                    selectedScreen = "screen2"
                    onScreenSelected("add")
                }
            )

            // Button 3
            AppBarButton(
                iconResId = R.drawable.search,
                text = " Who's In",
                isSelected = selectedScreen == "screen3",
                onClick = {
                    selectedScreen = "screen3"
                    onScreenSelected("whoin")
                }
            )

            // Button 3
            AppBarButton(
                iconResId = R.drawable.history,
                text = " History",
                isSelected = selectedScreen == "screen4",
                onClick = {
                    selectedScreen = "screen4"
                    onScreenSelected("History")
                }
            )


            // Button 2
            AppBarButton(
                iconResId = R.drawable.wallet,
                text = " Payments",
                isSelected = selectedScreen == "screen5",
                onClick = {
                    selectedScreen = "screen5"
                    onScreenSelected("payments")
                }
            )

            // Add more buttons as needed
        }
    }
}

@Composable
fun AppBarButton(iconResId: Int, text: String, isSelected: Boolean, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.width(180.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) Color(0xFF1b69b2) else Color.Transparent
        ),
        shape = MaterialTheme.shapes.medium // Use MaterialTheme's default button shape
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.fillMaxWidth()
        ) {
            // Use CustomIcon composable to display the custom icon
            CustomIcon(iconResId = iconResId, contentDescription = text)

            Spacer(modifier = Modifier.width(8.dp)) // Adjust spacing as needed

            Text(
                text = text,
                color = Color.White, // Text color
                fontSize = 16.sp,
                modifier = Modifier.padding(end = 8.dp) // Add padding between icon and text
            )
        }
    }
}
