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

import androidx.compose.material3.Text
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import android.content.Context
import android.hardware.SensorAdditionalInfo
import androidx.compose.foundation.border
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException
import java.io.InputStreamReader

import java.io.File


data class Student(
    val ID: Int,
    var studentName: String,
    var age: Int,
    var contactInfo: String,
    var parentName: String,
    var parentContactInfo: String,
    var address: String,
    var canLeaveAlone: Boolean,
    var additionalInfo: String
)



class Load(private val context: Context, ) {
    fun loadStudents(): List<Student> {
        val file = File(context.filesDir, "students.json")
        return try {
            val type = object : TypeToken<List<Student>>() {}.type
            Gson().fromJson(file.readText(), type)
        } catch (e: IOException) {
            e.printStackTrace()
            emptyList()
        }
    }
}

// Function to write JSON data to signedin.json
fun writeSignedInStudentsData(context: Context, students: List<Student>) {
    val jsonFile = File(context.filesDir, "signedin.json")
    val jsonString = Gson().toJson(students)
    jsonFile.writeText(jsonString)
}

// Function to read JSON data from signedin.json
fun readSignedInStudentsData(context: Context): List<Student> {
    val jsonFile = File(context.filesDir, "signedin.json")
    return if (jsonFile.exists()) {
        val jsonText = jsonFile.readText()
        val listType = object : TypeToken<List<Student>>() {}.type
        Gson().fromJson(jsonText, listType)
    } else {
        emptyList()
    }
}


@Composable
fun StudentBox(
    studentName: String,
    age: Int,
    contactInfo: String,
    parentContactInfo: String,
    canLeaveAlone: Boolean,
    onClick: () -> Unit,
    onSignIn: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(horizontal = 16.dp, vertical = 5.dp)
            .background(Color.White)
            .border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(8.dp))
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
                    text = age.toString(),
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
                    text = "Student Num",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                )
                Text(
                    text = contactInfo,
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
                    text = "Parent Num",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                )
                Text(
                    text = parentContactInfo,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
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

            // Sixth Column: Edit Button
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Button(
                    onClick = onClick,
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                        .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1b69b2))
                ) {
                    Text(
                        text = "Edit",
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center,
                        color = Color.White
                    )
                }
            }

            // Seventh Column: Sign In Button
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Button(
                    onClick = { onSignIn() }, // Call the onSignIn callback
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                        .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1b69b2))
                ) {
                    Text(
                        text = "Sign In",
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center,
                        color = Color.White
                    )
                }
            }
        }
    }
}


@Composable
fun SignInScreen(
    context: Context,
    onScreenSelected: (String, Student) -> Unit
) {
    val load = Load(context)
    val allStudents = load.loadStudents()
    val signedInStudents = remember { mutableStateListOf<Student>() }

    // Load initially signed in students
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
                    text = "Sign In",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .wrapContentHeight(align = Alignment.CenterVertically),
                    textAlign = TextAlign.Right
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            allStudents.forEach { student ->
                // Only show students who are not already signed in
                if (!signedInStudents.any { it.ID == student.ID }) {
                    StudentBox(
                        studentName = student.studentName,
                        age = student.age,
                        contactInfo = student.contactInfo,
                        parentContactInfo = student.parentContactInfo,
                        canLeaveAlone = student.canLeaveAlone,
                        onClick = { onScreenSelected("edit", student) },
                        onSignIn = {
                            // Remove student from all students list
                            val updatedAllStudents = allStudents.filter { it.ID != student.ID }
                            // Update UI
                            onScreenSelected("signIn", student)
                            // Update signed in list
                            signedInStudents.add(student)
                            // Write updated signed in students list
                            writeSignedInStudentsData(context, signedInStudents)
                            // Write updated all students list
                            writeStudentsData(context, updatedAllStudents)
                        }
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }
    }
}










