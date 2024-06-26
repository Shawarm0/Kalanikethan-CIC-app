package com.example.kalanikethan

import android.content.Context
import android.content.DialogInterface
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.window.Dialog
import org.json.JSONArray
import org.json.JSONObject
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import kotlin.random.Random



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Add(context: Context) {
    var childName by remember { mutableStateOf("") }
    var age by remember { mutableStateOf(0) }
    var contactInfo by remember { mutableStateOf("") }
    var parentName by remember { mutableStateOf("") }
    var parentContactInfo by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var additionalInfo by remember { mutableStateOf("") }
    var canWalkHomeAlone by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFebefef))
            .padding(horizontal = 0.dp, vertical = 0.dp)
    ) {
        // Box at the top for "Add Student"
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
                text = "Add Student",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        // White box for form content
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            shape = RoundedCornerShape(8.dp),
        ) {
            Row(modifier = Modifier.fillMaxSize()) {
                // Left side - Student information
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(16.dp)
                ) {
                    OutlinedTextField(
                        value = childName,
                        onValueChange = { childName = it },
                        label = { Text("Student Name") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp),
                        textStyle = TextStyle(color = Color.Black),
                        singleLine = true,
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            cursorColor = Color.Black,
                            focusedBorderColor = Color.Black,
                            unfocusedBorderColor = Color.LightGray
                        )
                    )

                    OutlinedTextField(
                        value = if (age == 0) "" else age.toString(),
                        onValueChange = { newValue ->
                            age = if (newValue.isNotEmpty()) {
                                newValue.toIntOrNull() ?: 0
                            } else {
                                0
                            }
                        },
                        label = { Text("Age") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp),
                        textStyle = TextStyle(color = Color.Black),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            cursorColor = Color.Black,
                            focusedBorderColor = Color.Black,
                            unfocusedBorderColor = Color.LightGray
                        )
                    )

                    OutlinedTextField(
                        value = contactInfo,
                        onValueChange = { contactInfo = it },
                        label = { Text("Contact Info") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp),
                        textStyle = TextStyle(color = Color.Black),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            cursorColor = Color.Black,
                            focusedBorderColor = Color.Black,
                            unfocusedBorderColor = Color.LightGray
                        )
                    )

                    OutlinedTextField(
                        value = additionalInfo,
                        onValueChange = { additionalInfo = it },
                        label = { Text("Additional Info") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp),
                        textStyle = TextStyle(color = Color.Black),
                        singleLine = true,
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            cursorColor = Color.Black,
                            focusedBorderColor = Color.Black,
                            unfocusedBorderColor = Color.LightGray
                        )
                    )

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(
                            checked = canWalkHomeAlone,
                            onCheckedChange = { canWalkHomeAlone = it },
                            modifier = Modifier.padding(end = 8.dp)
                        )
                        Text("Can walk home alone")
                    }


                    Spacer(modifier = Modifier.height(16.dp))


                    // Add Button
                    AddButton("Add") {
                        // Add student data to internal storage
                        addStudentToInternalStorage(
                            context = context,
                            name = childName,
                            age = age,
                            contactInfo = contactInfo,
                            parentName = parentName,
                            parentContactInfo = parentContactInfo,
                            address = address,
                            additionalInfo = additionalInfo,
                            canWalkHomeAlone = canWalkHomeAlone
                        )

                        // Clear form fields
                        childName = ""
                        age = 0
                        contactInfo = ""
                        parentName = ""
                        parentContactInfo = ""
                        address = ""
                        additionalInfo = ""
                        canWalkHomeAlone = false
                    }
                }

                // Right side - Parent information
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(16.dp)
                ) {
                    OutlinedTextField(
                        value = parentName,
                        onValueChange = { parentName = it },
                        label = { Text("Parent Name") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp),
                        textStyle = TextStyle(color = Color.Black),
                        singleLine = true,
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            cursorColor = Color.Black,
                            focusedBorderColor = Color.Black,
                            unfocusedBorderColor = Color.LightGray
                        )
                    )

                    OutlinedTextField(
                        value = parentContactInfo,
                        onValueChange = { parentContactInfo = it },
                        label = { Text("Parent Contact Info") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp),
                        textStyle = TextStyle(color = Color.Black),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            cursorColor = Color.Black,
                            focusedBorderColor = Color.Black,
                            unfocusedBorderColor = Color.LightGray
                        )
                    )

                    OutlinedTextField(
                        value = address,
                        onValueChange = { address = it },
                        label = { Text("Address") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp),
                        textStyle = TextStyle(color = Color.Black),
                        singleLine = false,
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            cursorColor = Color.Black,
                            focusedBorderColor = Color.Black,
                            unfocusedBorderColor = Color.LightGray
                        )
                    )
                }
            }
        }


    }
}





private fun addStudentToInternalStorage(
    context: Context,
    name: String,
    age: Int,
    contactInfo: String,
    parentName: String,
    parentContactInfo: String,
    address: String,
    additionalInfo: String,
    canWalkHomeAlone: Boolean
) {
    try {
        val file = File(context.filesDir, "students.json")
        val jsonArray = if (file.exists()) {
            JSONArray(file.readText())
        } else {
            JSONArray()
        }

        // Generate a unique ID
        val uniqueId = generateUniqueId(jsonArray)

        val jsonObject = JSONObject().apply {
            put("ID", uniqueId)
            put("studentName", name)
            put("age", age)
            put("contactInfo", contactInfo)
            put("parentName", parentName)
            put("parentContactInfo", parentContactInfo)
            put("address", address)
            put("additionalInfo", additionalInfo)
            put("canLeaveAlone", canWalkHomeAlone)
        }

        jsonArray.put(jsonObject)

        file.writeText(jsonArray.toString())
    } catch (e: IOException) {
        e.printStackTrace()
    }
}

private fun generateUniqueId(jsonArray: JSONArray): Int {
    var uniqueId: Int
    val usedIds = mutableSetOf<Int>()

    // Collect all existing IDs
    for (i in 0 until jsonArray.length()) {
        val studentObject = jsonArray.getJSONObject(i)
        usedIds.add(studentObject.getInt("ID"))
    }

    // Generate a random ID and check for uniqueness
    do {
        uniqueId = Random.nextInt(1000, 9999)  // Adjust range as needed
    } while (usedIds.contains(uniqueId))

    return uniqueId
}

@Composable
fun AddButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .width(200.dp)
            .height(48.dp),
        shape = RoundedCornerShape(8.dp),
        colors =  ButtonDefaults.buttonColors(containerColor = Color(0xFF1b69b2))
    ) {
        Text(text, fontSize = 16.sp)
    }
}