package com.example.kalanikethan

import Edit
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kalanikethan.ui.theme.KalanikethanTheme
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File

// Function to read JSON data from students.json
fun readStudentsData(context: android.content.Context): List<Student> {
    val jsonFile = File(context.filesDir, "students.json")
    val jsonText = jsonFile.readText()
    val listType = object : TypeToken<List<Student>>() {}.type
    return Gson().fromJson(jsonText, listType)
}

// Function to write JSON data to students.json
fun writeStudentsData(context: android.content.Context, students: List<Student>) {
    val jsonFile = File(context.filesDir, "students.json")
    val jsonString = Gson().toJson(students)
    jsonFile.writeText(jsonString)
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KalanikethanTheme {
                var selectedScreen by remember { mutableStateOf("signIn") } // Default screen
                var selectedStudent by remember {
                    mutableStateOf(
                        Student(
                            ID = 0,
                            studentName = "",
                            age = 0,
                            contactInfo = "",
                            parentName = "",
                            parentContactInfo = "",
                            address = "",
                            canLeaveAlone = false,
                            additionalInfo = ""
                        )
                    )
                }

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Row(Modifier.fillMaxSize()) {
                        // Appbar on the left with top padding
                        Appbar(
                            modifier = Modifier
                                .padding(top = 24.dp) // Add top padding
                                .width(200.dp) // Adjust width as needed
                                .fillMaxHeight(),
                            onScreenSelected = { screen ->
                                selectedScreen = screen
                            }
                        )

                        // Main content
                        Column(modifier = Modifier.fillMaxSize()) {
                            when (selectedScreen) {
                                "signIn" -> SignInScreen(
                                    context = applicationContext,
                                    onScreenSelected = { screen, student ->
                                        selectedScreen = screen
                                        selectedStudent = student
                                    }
                                )
                                "add" -> Add(context = applicationContext)
                                "whoin" -> Whoin()
                                "History" -> History()
                                "edit" -> Edit(
                                    ID = selectedStudent.ID,
                                    studentName = selectedStudent.studentName,
                                    age = selectedStudent.age,
                                    contactInfo = selectedStudent.contactInfo,
                                    parentName = selectedStudent.parentName,
                                    parentContactInfo = selectedStudent.parentContactInfo,
                                    address = selectedStudent.address,
                                    canLeaveAlone = selectedStudent.canLeaveAlone,
                                    additionalInfo = selectedStudent.additionalInfo,
                                    onSave = { ID: Int, name: String, age: Int, contact: String, parent: String, parentContact: String, address: String, canLeave: Boolean, additional: String ->
                                        // Read existing students data
                                        val students = readStudentsData(applicationContext)

                                        // Find the student with matching ID
                                        val studentToUpdate = students.find { it.ID == ID }

                                        if (studentToUpdate != null) {
                                            // Update the fields
                                            studentToUpdate.studentName = name
                                            studentToUpdate.age = age
                                            studentToUpdate.contactInfo = contact
                                            studentToUpdate.parentName = parent
                                            studentToUpdate.parentContactInfo = parentContact
                                            studentToUpdate.address = address
                                            studentToUpdate.canLeaveAlone = canLeave
                                            studentToUpdate.additionalInfo = additional

                                            // Write back updated data to JSON file
                                            writeStudentsData(applicationContext, students)

                                            // Update selectedStudent directly
                                            selectedStudent = studentToUpdate
                                            selectedScreen = "signIn"
                                        } else {
                                            // Handle case where student with given ID is not found
                                            println("Student with ID $ID not found.")


                                        }
                                    },
                                    onDelete = { ID ->
                                        // Read existing students data
                                        val students = readStudentsData(applicationContext)

                                        // Filter out the student with matching ID
                                        val updatedStudents = students.filter { it.ID != ID }

                                        // Write back updated data to JSON file
                                        writeStudentsData(applicationContext, updatedStudents)

                                        // Update selectedStudent and selectedScreen if the deleted student was selected
                                        if (selectedStudent.ID == ID) {
                                            selectedStudent = Student(
                                                ID = 0,
                                                studentName = "",
                                                age = 0,
                                                contactInfo = "",
                                                parentName = "",
                                                parentContactInfo = "",
                                                address = "",
                                                canLeaveAlone = false,
                                                additionalInfo = ""
                                            )
                                            selectedScreen = "signIn" // or any default screen after deletion
                                        }
                                    }
                                )
                                // Add more cases for other screens
                            }
                        }
                    }
                }
            }
        }
    }
}








