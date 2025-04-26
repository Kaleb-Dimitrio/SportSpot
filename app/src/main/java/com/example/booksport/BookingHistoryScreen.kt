package com.example.booksport

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BookingHistoryScreen(
    bookings: List<BookingData>,
    onBack: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.bg_home_wave),
            contentDescription = "Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                IconButton(onClick = onBack) {
                    Icon(
                        painter = painterResource(android.R.drawable.ic_menu_revert),
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }
                Text(
                    text = "Riwayat Booking",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            if (bookings.isEmpty()) {
                Text(
                    text = "Belum ada riwayat booking",
                    color = Color.White,
                    modifier = Modifier.padding(16.dp)
                )
            } else {
                LazyColumn {
                    items(bookings) { booking ->
                        BookingItem(booking = booking)
                    }
                }
            }
        }
    }
}

@Composable
fun BookingItem(booking: BookingData) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = 4.dp,
        backgroundColor = Color.White.copy(alpha = 0.9f)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = booking.sportType,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color(0xFF6200EE)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text("Tanggal: ${booking.date}")
            Text("Waktu: ${booking.time}")
            Text("Durasi: ${booking.duration} jam")
            Text("Atas nama: ${booking.name}")
        }
    }
}