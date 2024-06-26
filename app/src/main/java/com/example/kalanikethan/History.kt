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



data class HistoryEntry(
    val date: String,
    val day: String,
    val activities: List<Activity>
)

// Function to read JSON data from history.json
fun readHistoryData(context: Context): List<HistoryEntry> {
    val file = File(context.filesDir, "history.json")
    return if (file.exists()) {
        val jsonText = file.readText()
        val listType = object : TypeToken<List<HistoryEntry>>() {}.type
        Gson().fromJson(jsonText, listType)
    } else {
        emptyList()
    }
}

@Composable
fun History(context: Context) {
    val historyEntries = remember { readHistoryData(context) }



    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFebefef)) // Background color for the entire screen
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 0.dp, vertical = 0.dp)
                .verticalScroll(rememberScrollState()), // Enable vertical scrolling
            verticalArrangement = Arrangement.Top,
        ) {
            // Header box
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .clip(RoundedCornerShape(0.dp)) // Rounded corners for the header box
                    .background(color = Color(0xFF1b69b2)) // Blue background color for the box
                    .padding(horizontal = 30.dp), // Horizontal padding
                contentAlignment = Alignment.CenterStart // Align content to the start horizontally
            ) {
                Text(
                    text = "History",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.wrapContentHeight(align = Alignment.CenterVertically),
                    textAlign = TextAlign.Right // Align text to the right
                )
            }

            Spacer(modifier = Modifier.height(10.dp))
            if (historyEntries.isEmpty()) {
                // Do nothing if the historyEntries list is empty
                return
            }
            // Display history entries
            historyEntries.forEach { entry ->
                HistoryBox(entry)
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}

@Composable
fun HistoryBox(entry: HistoryEntry) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 5.dp)
            .background(Color.White)
            .border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(8.dp))
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(
                text = "${entry.day}, ${entry.date}",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            entry.activities.forEach { activity ->
                Text(
                    text = "${activity.timestamp} - ${activity.description}",
                    fontSize = 14.sp,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
            }
        }
    }
}
