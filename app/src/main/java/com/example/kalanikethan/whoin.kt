package com.example.kalanikethan
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import java.io.IOException


@Composable
fun SignedInStudentBox(
    studentName: String,
    age: Int,
    contactInfo: String,
    parentContactInfo: String,
    canLeaveAlone: Boolean,
    onClick: () -> Unit,
    onSignOut: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .background(color = Color.White)
            .clip(RoundedCornerShape(8.dp))
            .border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(8.dp))
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            // Student Details
            Column(
                modifier = Modifier.weight(2f)
            ) {
                Text(
                    text = studentName,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start
                )
                Text(
                    text = "Age: $age",
                    fontSize = 16.sp,
                    textAlign = TextAlign.Start
                )
                Text(
                    text = "Contact: $contactInfo",
                    fontSize = 16.sp,
                    textAlign = TextAlign.Start
                )
                Text(
                    text = "Parent Contact: $parentContactInfo",
                    fontSize = 16.sp,
                    textAlign = TextAlign.Start
                )

            }

            // Fifth Column: Can Leave Alone (Checkbox)
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
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
                    onCheckedChange = null, // Make it read-only
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }

            // Sign Out Button
            Button(
                onClick = onSignOut,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .weight(1f),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1b69b2))
            ) {
                Text(
                    text = "Sign Out",
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}


@Composable
fun Whoin(
    context: Context,
    onScreenSelected: (String, Student) -> Unit
) {
    val signedInStudents = remember { mutableStateListOf<Student>() }

    LaunchedEffect(Unit) {
        signedInStudents.addAll(readSignedInStudentsData(context))
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFebefef))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 0.dp, vertical = 0.dp)
                .verticalScroll(enabled = true, state = rememberScrollState()),
            verticalArrangement = Arrangement.Top,
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .clip(RoundedCornerShape(0.dp))
                    .background(color = Color(0xFF1b69b2))
                    .padding(horizontal = 30.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = "Who's In",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .wrapContentHeight(align = Alignment.CenterVertically),
                    textAlign = TextAlign.Right
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            signedInStudents.forEach { student ->
                SignedInStudentBox(
                    studentName = student.studentName,
                    age = student.age,
                    contactInfo = student.contactInfo,
                    parentContactInfo = student.parentContactInfo,
                    canLeaveAlone = student.canLeaveAlone,
                    onClick = { onScreenSelected("edit", student) },
                    onSignOut = {
                        // Remove from signed in list
                        signedInStudents.remove(student)
                        // Update signed in students JSON file
                        writeSignedInStudentsData(context, signedInStudents)
                        // Add back to students list
                        val allStudents = loadStudents(context)
                        allStudents.add(student)
                        // Update students JSON file
                        writeStudentsData(context, allStudents)
                    }
                )
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}

// Function to load students from students.json
fun loadStudents(context: Context): MutableList<Student> {
    val file = File(context.filesDir, "students.json")
    return try {
        val type = object : TypeToken<List<Student>>() {}.type
        Gson().fromJson(file.readText(), type)
    } catch (e: IOException) {
        e.printStackTrace()
        mutableListOf()
    }
}



