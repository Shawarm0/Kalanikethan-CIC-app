package com.example.kalanikethan
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.draw.clip


@Composable
fun Placeholder1Screen() {
    // Outer container with the background color #f3f6f6
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFebefef)) // Background color for the entire screen
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 0.dp, vertical = 24.dp),
            verticalArrangement = Arrangement.Top,
        ) {
            // White box for sign-in content
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .clip(RoundedCornerShape(0.dp)) // Rounded corners for the white box
                    .background(color = Color(0xFF1b69b2)) // White background color for the box
                    .padding(horizontal = 30.dp), // Horizontal padding
                contentAlignment = Alignment.CenterStart // Align content to the start horizontally
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Add Student",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .wrapContentHeight(align = Alignment.CenterVertically),
                        textAlign = TextAlign.Right // Align text to the right
                    )
                }
            }
        }
    }
}
