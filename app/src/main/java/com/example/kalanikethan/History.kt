package com.example.kalanikethan

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
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
import androidx.compose.material3.Text
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.text.SimpleDateFormat
import java.util.*
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.ui.res.painterResource


// Function to read JSON data from history.json
fun readHistoryData(context: Context): List<History> {
    val file = File(context.filesDir, "history.json")
    return if (file.exists()) {
        val jsonText = file.readText()
        val listType = object : TypeToken<List<History>>() {}.type
        Gson().fromJson(jsonText, listType)
    } else {
        emptyList()
    }
}


// Comparator to sort History entries by date and time
val historyComparator = Comparator<History> { h1, h2 ->
    val dateFormat = SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.getDefault())
    val dateTime1 = dateFormat.parse("${h1.date} ${h1.Signintime}") ?: Date(0)
    val dateTime2 = dateFormat.parse("${h2.date} ${h2.Signintime}") ?: Date(0)
    dateTime2.compareTo(dateTime1) // Descending order (most recent first)
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun History(context: Context) {
    val historyEntries = remember { readHistoryData(context).sortedWith(historyComparator) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFebefef))
            .padding(horizontal = 0.dp, vertical = 0.dp)
    ) {
        // Box at the top for "History"
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
                text = "History",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(horizontal = 0.dp)
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Displaying history entries with dropdowns
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(vertical = 0.dp, horizontal = 10.dp)
        ) {
            historyEntries.groupBy { it.day }.forEach { (day, entries) ->
                // Dropdown for each date
                var expanded by remember { mutableStateOf(false) }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.clickable { expanded = !expanded }
                    ) {
                        Icon(
                            imageVector = if (expanded) Icons.Default.KeyboardArrowDown else Icons.Default.KeyboardArrowRight,
                            contentDescription = "Dropdown icon",
                            modifier = Modifier.padding(horizontal = 8.dp)
                        )
                        Text(
                            text = day,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                    }

                    // Animated dropdown content
                    AnimatedVisibility(
                        visible = expanded,
                        enter = slideInVertically(
                            initialOffsetY = { -it },
                            animationSpec = tween(durationMillis = 300)
                        )
                    ) {
                        Column(
                            modifier = Modifier.padding(start = 16.dp)
                        ) {
                            entries.forEach { entry ->
                                HistoryBox(entry)
                            }
                        }
                    }
                }
            }
        }
    }
}







@Composable
fun HistoryBox(entry: History) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight() // Adjust height based on content
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .background(Color.White)
            .border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(8.dp))
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Name Column
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "Name:",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                    Text(
                        text = entry.Name ?: "Unknown",
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }

                // Sign in Column
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "Sign in:",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                    Text(
                        text = entry.Signintime ?: "N/A",
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }

                // Sign out Column
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "Sign out:",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                    Text(
                        text = entry.Signouttime ?: "N/A",
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }

                // Changes made Column
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "Changes Made:",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                    entry.changesmade.forEach { change ->
                        Text(
                            text = "${change.timestamp} - ${change.description}",
                            fontSize = 14.sp,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )
                    }
                }
            }
        }
    }
}




