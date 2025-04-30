package com.example.choronopoets.navigation

sealed class Screen(val route: String) {
    data object CENTURY_SCREEN: Screen("century_screen")
    data object POETS_SCREEN : Screen("poets_screen/{centuryId}") {
        fun createRoute(centuryId: Int) = "poets_screen/$centuryId"
    }
    data object POET_DETAIL_SCREEN : Screen("poet_details/{poetId}") {
        fun createRoute(poetId: Int) = "poet_details/$poetId"
    }
    data object POEM_DETAIL_SCREEN : Screen("poem_details/{poemId}"){
        fun createRoute(poemId: Int) = "poem_details/$poemId"
    }
}