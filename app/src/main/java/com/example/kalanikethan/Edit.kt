import androidx.compose.foundation.layout.*
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Edit(
    ID: Int,
    studentName: String,
    age: Int,
    contactInfo: String,
    parentName: String,
    parentContactInfo: String,
    address: String,
    canLeaveAlone: Boolean,
    additionalInfo: String,
    onSave: (Int, String, Int, String, String, String, String, Boolean, String) -> Unit, // Function to handle save action
    onDelete: (Int) -> Unit // Function to handle delete action
) {
    var editedStudentName by remember { mutableStateOf(studentName) }
    var editedAge by remember { mutableStateOf(age) }
    var editedContactInfo by remember { mutableStateOf(contactInfo) }
    var editedParentName by remember { mutableStateOf(parentName) }
    var editedParentContactInfo by remember { mutableStateOf(parentContactInfo) }
    var editedAddress by remember { mutableStateOf(address) }
    var editedCanLeaveAlone by remember { mutableStateOf(canLeaveAlone) }
    var editedAdditionalInfo by remember { mutableStateOf(additionalInfo) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFebefef))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 0.dp, vertical = 24.dp),
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
                    text = "Edit Student",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                shape = RoundedCornerShape(8.dp),
            ) {
                Row(modifier = Modifier.fillMaxSize()) {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(16.dp)
                    ) {
                        OutlinedTextField(
                            value = editedStudentName,
                            onValueChange = { editedStudentName = it },
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
                            value = if (editedAge == 0) "" else editedAge.toString(),
                            onValueChange = { newValue ->
                                editedAge = if (newValue.isNotEmpty()) {
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
                            value = editedContactInfo,
                            onValueChange = { editedContactInfo = it },
                            label = { Text("Contact Info") },
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
                            value = editedAdditionalInfo,
                            onValueChange = { editedAdditionalInfo = it },
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
                                checked = editedCanLeaveAlone,
                                onCheckedChange = { editedCanLeaveAlone = it },
                                modifier = Modifier.padding(end = 8.dp)
                            )
                            Text("Can walk home alone")
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End
                        ) {
                            SaveButton("Save") {
                                onSave(
                                    ID,
                                    editedStudentName,
                                    editedAge,
                                    editedContactInfo,
                                    editedParentName,
                                    editedParentContactInfo,
                                    editedAddress,
                                    editedCanLeaveAlone,
                                    editedAdditionalInfo
                                )
                            }

                            Spacer(modifier = Modifier.width(8.dp))

                            DeleteButton("Delete") {
                                onDelete(ID)
                            }
                        }
                    }

                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(16.dp)
                    ) {
                        OutlinedTextField(
                            value = editedParentName,
                            onValueChange = { editedParentName = it },
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
                            value = editedParentContactInfo,
                            onValueChange = { editedParentContactInfo = it },
                            label = { Text("Parent Contact Info") },
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
                            value = editedAddress,
                            onValueChange = { editedAddress = it },
                            label = { Text("Address") },
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
                    }
                }
            }
        }
    }
}

@Composable
fun SaveButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .width(200.dp)
            .height(48.dp),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1b69b2))
    ) {
        Text(text, fontSize = 16.sp)
    }
}

@Composable
fun DeleteButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .width(200.dp)
            .height(48.dp),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFcc0000))
    ) {
        Text(text, fontSize = 16.sp)
    }
}

// Assume your MainActivity continues below with readStudentsData and writeStudentsData functions


