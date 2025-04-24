package com.example.booksport

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Define BookSportTheme
@Composable
fun BookSportTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = lightColors(
            primary = Color(0xFF6200EE),
            secondary = Color(0xFF03DAC5)
        ),
        content = content
    )
}

@Composable
fun MainScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(painterResource(id = R.drawable.bg_home_wave))
    ) {
        // Header Section
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Selamat Datang",
                    color = Color.White,
                    fontSize = 16.sp
                )
                Text(
                    text = "Lebron James",
                    color = Color.White,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Image(
                painter = painterResource(id = R.drawable.ic_profile),
                contentDescription = "Profile",
                modifier = Modifier.size(50.dp)
            ) // Fixed: Moved closing parenthesis to correct position
        }

        // Search Bar
        OutlinedTextField(
            value = "",
            onValueChange = {},
            leadingIcon = {
                Icon(
                    Icons.Default.Search,
                    contentDescription = "Search",
                    tint = Color.White
                )
            },
            placeholder = {
                Text("Mau olahraga apa?", color = Color.White)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = Color.White,
                unfocusedBorderColor = Color.Transparent,
                backgroundColor = Color.Transparent
            ),
            shape = MaterialTheme.shapes.medium
        )

        // Banner Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            elevation = 5.dp,
            shape = MaterialTheme.shapes.medium
        ) {
            Image(
                painter = painterResource(id = R.drawable.bg_banner),
                contentDescription = "Banner",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                contentScale = ContentScale.Crop
            )
        }

        // Transport Title
        Text(
            text = "Pilih Olahraga",
            color = Color.Black,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 20.dp, top = 10.dp)
        )

        // Transport Cards
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(bottom = 20.dp)
        ) {
            TransportCard(
                title = "Badminton",
                subtitle = "Lapangan Badminton",
                icon = painterResource(id = R.drawable.ic_plane),
                background = painterResource(id = R.drawable.bg_home_wave)
            )

            TransportCard(
                title = "Futsal",
                subtitle = "Lapangan Futsal",
                icon = painterResource(id = R.drawable.ic_ship),
                background = painterResource(id = R.drawable.bg_home_wave)
            )

            TransportCard(
                title = "Basket",
                subtitle = "Lapangan Basket",
                icon = painterResource(id = R.drawable.ic_train),
                background = painterResource(id = R.drawable.bg_home_wave)
            )
        }
    }
}

@Composable
fun TransportCard(
    title: String,
    subtitle: String,
    icon: Painter,
    background: Painter
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .height(150.dp),
        elevation = 5.dp,
        shape = MaterialTheme.shapes.medium
    ) {
        Box(modifier = Modifier.fillMaxSize()) { // Fixed: Removed extra parenthesis
            Image(
                painter = background,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            Column(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(20.dp)
            ) {
                Text(
                    text = title,
                    color = Color.White,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(30.dp))
                Surface(
                    color = Color.White.copy(alpha = 0.9f),
                    shape = MaterialTheme.shapes.small
                ) {
                    Text(
                        text = subtitle,
                        color = Color(0xFF6200EE), // primary color
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(6.dp)
                    )
                }
            }

            Image(
                painter = icon,
                contentDescription = title,
                modifier = Modifier
                    .size(200.dp)
                    .align(Alignment.CenterEnd)
                    .padding(15.dp),
                colorFilter = ColorFilter.tint(Color.White)
            )
        }
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BookSportTheme {
                Surface(
                    color = MaterialTheme.colors.background,
                    modifier = Modifier.fillMaxSize() // Added modifier for proper sizing
                ) {
                    MainScreen()
                }
            }
        }
    }
}