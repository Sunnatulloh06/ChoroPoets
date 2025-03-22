package com.example.choronopoets.navigation

sealed class Screen(val route: String) {
    object CENTURY_SCREEN: Screen("century_screen")
    object POETS_SCREEN : Screen("poets_screen/{centuryId}") {
        fun createRoute(centuryId: Int) = "poets_screen/$centuryId"
    }
    object PoetDetailsScreen : Screen("poet_details/{poetId}") {
        fun createRoute(poetId: Int) = "poet_details/$poetId"
    }
}