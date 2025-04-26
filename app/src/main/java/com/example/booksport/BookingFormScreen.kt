package com.example.booksport

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import android.R

@Composable
fun BookingFormScreen(
    sportType: String,
    onBack: () -> Unit,
    onSubmit: (BookingData) -> Unit
) {
    // Form state
    var date by remember { mutableStateOf("") }
    var time by remember { mutableStateOf("") }
    var duration by remember { mutableStateOf("1") }
    var name by remember { mutableStateOf("") }

    // Confirmation dialog
    var showConfirmation by remember { mutableStateOf(false) }

    val durationInt = duration.toIntOrNull() ?: 1
    // Form validation
    val isFormValid = date.isNotBlank() &&
            time.isNotBlank() &&
            name.isNotBlank() &&
            duration.isNotBlank()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFd3e5fb))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
                .verticalScroll(rememberScrollState())
        ) {
            // Header with back button
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                IconButton(
                    onClick = onBack,
                    modifier = Modifier.size(48.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_menu_revert),
                        contentDescription = "Back",
                        tint = Color(0xFF72b9fd)
                    )
                }
                Text(
                    text = "Booking $sportType",
                    style = MaterialTheme.typography.h4.copy(
                        color = Color(0xFF72b9fd),
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

            // Form Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = 8.dp,
                shape = RoundedCornerShape(16.dp),
                backgroundColor = Color.White
            ) {
                Column(
                    modifier = Modifier.padding(24.dp)
                ) {
                    // Date Input
                    OutlinedTextField(
                        value = date,
                        onValueChange = { date = it },
                        label = { Text("Tanggal (DD/MM/YYYY)") },
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = { Text("Contoh: 31/12/2023") },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color(0xFF72b9fd),
                            unfocusedBorderColor = Color.LightGray,
                            cursorColor = Color(0xFF72b9fd)
                        ),
                        shape = RoundedCornerShape(8.dp),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Time Input
                    OutlinedTextField(
                        value = time,
                        onValueChange = { time = it },
                        label = { Text("Waktu (HH:MM)") },
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = { Text("Contoh: 14:30") },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color(0xFF72b9fd),
                            unfocusedBorderColor = Color.LightGray,
                            cursorColor = Color(0xFF72b9fd)
                        ),
                        shape = RoundedCornerShape(8.dp),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Duration
                    OutlinedTextField(
                        value = duration,
                        onValueChange = { duration = it },
                        label = { Text("Durasi (jam)") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color(0xFF72b9fd),
                            unfocusedBorderColor = Color.LightGray,
                            cursorColor = Color(0xFF72b9fd)
                        ),
                        shape = RoundedCornerShape(8.dp),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        )
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Name
                    OutlinedTextField(
                        value = name,
                        onValueChange = { name = it },
                        label = { Text("Nama Pemesan") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color(0xFF72b9fd),
                            unfocusedBorderColor = Color.LightGray,
                            cursorColor = Color(0xFF72b9fd)
                        ),
                        shape = RoundedCornerShape(8.dp),
                        singleLine = true
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Submit Button
            Button(
                onClick = {
                    onSubmit(
                        BookingData(
                            sportType = sportType,
                            date = date,
                            time = time,
                            duration = durationInt,
                            name = name
                        )
                    )
                    showConfirmation = true
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                enabled = isFormValid,
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFF72b9fd),
                    contentColor = Color.White,
                    disabledBackgroundColor = Color(0xFF72b9fd).copy(alpha = 0.5f)
                )
            ) {
                Text(
                    text = "Pesan Sekarang",
                    style = MaterialTheme.typography.button.copy(
                        fontSize = 18.sp
                    )
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Back Button
            TextButton(
                onClick = onBack,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Kembali",
                    color = Color(0xFF72b9fd),
                    style = MaterialTheme.typography.button
                )
            }
        }

        // Confirmation Dialog
        if (showConfirmation) {
            AlertDialog(
                onDismissRequest = { showConfirmation = false },
                title = {
                    Text(
                        text = "Booking Berhasil!",
                        style = MaterialTheme.typography.h6.copy(
                            color = Color(0xFF72b9fd)
                        )
                    )
                },
                text = {
                    Text(
                        text = "Lapangan $sportType berhasil dipesan!",
                        style = MaterialTheme.typography.body1
                    )
                },
                confirmButton = {
                    Button(
                        onClick = {
                            showConfirmation = false
                            onBack()
                        },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(0xFF72b9fd),
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("OK")
                    }
                },
                shape = RoundedCornerShape(16.dp),
                backgroundColor = Color.White
            )
        }
    }
}