// Navigation.kt
package com.example.booksport

sealed class Screen(val route: String) {
    object MainScreen : Screen("main")
    object BookingForm : Screen("booking/{sportType}") {
        fun createRoute(sportType: String) = "booking/$sportType"
    }
}