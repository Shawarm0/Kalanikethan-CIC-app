package com.example.kalanikethan

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.runtime.remember
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable








@Composable
fun StudentBox(studentName: String, age: Int, contactInfo: String, parentContactInfo: String, canLeaveAlone: Boolean) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(horizontal = 16.dp, vertical = 5.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Color.White),

    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // First Column: Name
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Name",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                )
                Text(
                    text = studentName,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }

            // Second Column: Age
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Age",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                )
                Text(
                    text = "$age", // Replace with actual age data
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }

            // Third Column: Contact Info
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Contact Info",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                )
                Text(
                    text = contactInfo, // Replace with actual contact info
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }

            // Fourth Column: Parent Contact Info
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Parent Contact Info",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                )
                Text(
                    text = parentContactInfo, // Replace with actual parent contact info
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }

            // Fifth Column: Can Leave Alone
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Can leave alone",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                )
                Checkbox(
                    checked = canLeaveAlone,
                    onCheckedChange = null, // Pass null to make it disabled
                    enabled = false, // Disable checkbox so it cannot be changed
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }

//            // Sixth Column: Edit and Sign In Buttons
//            Column(
//                modifier = Modifier.weight(1f)
//            ) {
//                Button(
//                    onClick = { /* Handle Edit button click */ },
//                    modifier = Modifier
//                        .padding(horizontal = 8.dp, vertical = 4.dp)
//                        .height(25.dp).width(100.dp),
//                ) {
//                    Text(
//                        text = "Edit",
//                        fontSize = 5.sp,
//                        fontWeight = FontWeight.Bold
//                    )
//                }
//                Button(
//                    onClick = { /* Handle Sign In button click */ },
//                    modifier = Modifier
//                        .padding(horizontal = 8.dp, vertical = 4.dp)
//                        .height(25.dp).width(100.dp),
//                ) {
//                    Text(
//                        text = "Sign In",
//                        fontSize = 5.sp,
//                        fontWeight = FontWeight.Bold
//                    )
//                }
//            }
        }
    }
}


@Composable
fun SignInScreen() {
    val sortedStudentList = remember {
        listOf(
            Student("Abigail George", 17, "07476423570", "07886506456", true),
            Student("Zachary Brown", 18, "07234567890", "07987654321", false),
            Student("Emma Johnson", 16, "07654321098", "07543210987", true),
            Student("Angel Benny", 13, "29837423", "293874933", false),
            // Add more students as needed
        ).sortedBy { it.name }
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFebefef)) // Background color for the entire screen
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 0.dp, vertical = 24.dp)
                .verticalScroll(enabled = true, state = rememberScrollState()),
            verticalArrangement = Arrangement.Top,
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .clip(RoundedCornerShape(0.dp)) // Rounded corners for the white box
                    .background(color = Color(0xFF1b69b2)) // White background color for the box
                    .padding(horizontal = 30.dp), // Horizontal padding
                contentAlignment = Alignment.CenterStart // Align content to the start horizontally
            ) {
                Text(
                    text = "Sign In",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .wrapContentHeight(align = Alignment.CenterVertically),
                    textAlign = TextAlign.Right // Align text to the right
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            // Display student boxes
            sortedStudentList.forEach { student ->
                StudentBox(studentName = student.name,
                           age = student.age,
                           contactInfo = student.contactInfo,
                           parentContactInfo = student.parentContactInfo,
                           canLeaveAlone = student.canLeaveAlone)
                Spacer(modifier = Modifier.height(10.dp)) // Vertical gap between boxes
            }
        }
    }
}

data class Student(
    val name: String,
    val age: Int,
    val contactInfo: String,
    val parentContactInfo: String,
    val canLeaveAlone: Boolean
)







