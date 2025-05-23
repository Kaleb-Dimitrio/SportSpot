package com.example.booksport

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

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
fun MainScreen(
    onSportSelected: (String) -> Unit,
    onProfileClicked: () -> Unit
) {
    val sports = listOf(
        Sport("Badminton", "Lapangan Badminton", R.drawable.ic_badminton),
        Sport("Futsal", "Lapangan Futsal", R.drawable.ic_futsal),
        Sport("Basket", "Lapangan Basket", R.drawable.ic_basketball)
    )

    var searchQuery by remember { mutableStateOf("") }

    val filteredSports = sports.filter { sport ->
        sport.title.contains(searchQuery, ignoreCase = true) ||
                sport.subtitle.contains(searchQuery, ignoreCase = true)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.bg_home_wave),
            contentDescription = "Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            // Profile Header Section
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
                IconButton(
                    onClick = onProfileClicked,
                    modifier = Modifier.size(50.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_profile),
                        contentDescription = "Profile",
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }

            // Search Bar
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
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
                    .padding(horizontal = 16.dp, vertical = 8.dp),
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

            // Sports Title
            Text(
                text = "Pilih Olahraga",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 24.dp, top = 8.dp)
            )

            // Sports Cards
            Column(
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                if (filteredSports.isEmpty()) {
                    Text(
                        text = "Tidak ditemukan olahraga yang sesuai",
                        color = Color.White,
                        modifier = Modifier.padding(16.dp)
                    )
                } else {
                    filteredSports.forEach { sport ->
                        SportCard(
                            title = sport.title,
                            subtitle = sport.subtitle,
                            icon = painterResource(id = sport.iconRes),
                            background = painterResource(id = R.drawable.bg_home_wave),
                            onClick = { onSportSelected(sport.title) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SportCard(
    title: String,
    subtitle: String,
    icon: Painter,
    background: Painter,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .height(150.dp)
            .clickable(onClick = onClick),
        elevation = 8.dp,
        shape = MaterialTheme.shapes.medium
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
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
                        color = Color(0xFF6200EE),
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

@Composable
fun BookSportApp() {
    val navController = rememberNavController()
    val bookings = remember { mutableStateListOf<BookingData>() }

    NavHost(
        navController = navController,
        startDestination = "main"
    ) {
        composable("main") {
            MainScreen(
                onSportSelected = { sportType ->
                    navController.navigate("booking/$sportType")
                },
                onProfileClicked = {
                    navController.navigate("bookings")
                }
            )
        }
        composable("booking/{sportType}") { backStackEntry ->
            val sportType = backStackEntry.arguments?.getString("sportType") ?: ""
            BookingFormScreen(
                sportType = sportType,
                onBack = { navController.popBackStack() },
                onSubmit = { bookingData ->
                    bookings.add(bookingData)
                    navController.popBackStack()
                }
            )
        }
        composable("bookings") {
            BookingHistoryScreen(
                bookings = bookings,
                onBack = { navController.popBackStack() }
            )
        }
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BookSportTheme {
                BookSportApp()
            }
        }
    }
}