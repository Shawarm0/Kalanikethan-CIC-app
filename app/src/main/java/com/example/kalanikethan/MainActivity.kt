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

data class History(
    val date: String,
    val day: String,
    val activities: MutableList<Activity>
)

data class Activity(
    val timestamp: String,
    val description: String
)




// Function to write JSON data to students.json
fun writeStudentsData(context: android.content.Context, students: List<Student>) {
    val jsonFile = File(context.filesDir, "students.json")
    val jsonString = Gson().toJson(students)
    jsonFile.writeText(jsonString)
}


private fun createHistoryFile(context: android.content.Context) {
    val file = File(context.filesDir, "history.json")
    if (!file.exists()) {
        val templateData = """
                []
            """.trimIndent()
        file.writeText(templateData)
    }
}



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KalanikethanTheme {
                var selectedScreen by remember { mutableStateOf("signIn") }
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

                createHistoryFile(applicationContext)

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Row(Modifier.fillMaxSize()) {
                        Appbar(
                            modifier = Modifier
                                .padding(top = 24.dp)
                                .width(200.dp)
                                .fillMaxHeight(),
                            onScreenSelected = { screen ->
                                selectedScreen = screen
                            }
                        )
                        Column(modifier = Modifier.fillMaxSize().padding(top = 24.dp)) {
                            when (selectedScreen) {
                                "signIn" -> SignInScreen(
                                    context = applicationContext,
                                    onScreenSelected = { screen, student ->
                                        selectedScreen = screen
                                        selectedStudent = student
                                    }
                                )
                                "add" -> Add(context = applicationContext)
                                "whoin" -> Whoin(
                                    context = applicationContext,
                                    onScreenSelected = { screen, student ->
                                        selectedScreen = screen
                                        selectedStudent = student
                                    }
                                )
                               "History" -> History(context = applicationContext)
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
                                        val students = readStudentsData(applicationContext)
                                        val studentToUpdate = students.find { it.ID == ID }
                                        if (studentToUpdate != null) {
                                            updateHistory(applicationContext, description = "${studentToUpdate.studentName} Has been edited.\nChanges have been saved")
                                        }
                                        if (studentToUpdate != null) {
                                            studentToUpdate.studentName = name
                                            studentToUpdate.age = age
                                            studentToUpdate.contactInfo = contact
                                            studentToUpdate.parentName = parent
                                            studentToUpdate.parentContactInfo = parentContact
                                            studentToUpdate.address = address
                                            studentToUpdate.canLeaveAlone = canLeave
                                            studentToUpdate.additionalInfo = additional

                                            writeStudentsData(applicationContext, students)

                                            selectedStudent = studentToUpdate
                                            selectedScreen = "signIn"
                                        } else {
                                            println("Student with ID $ID not found.")
                                        }
                                    },
                                    onDelete = { ID ->
                                        val students = readStudentsData(applicationContext)
                                        val updatedStudents = students.filter { it.ID != ID }

                                        val studentToDelete = students.find { it.ID == ID }

                                        updateHistory(applicationContext, description = "${studentToDelete?.studentName} has been deleted.\nChanges have been saved")

                                        writeStudentsData(applicationContext, updatedStudents)

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
                                            selectedScreen = "signIn"
                                        }
                                    },

                                )
                                "payments" -> Payments()
                            }
                        }
                    }
                }
            }
        }
    }
}











