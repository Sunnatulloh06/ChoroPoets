package com.example.choronopoets.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.choronopoets.presentation.CenturyScreen
import com.example.choronopoets.presentation.PoemDetailScreen
import com.example.choronopoets.viewModel.PoetryViewModel
import com.example.choronopoets.presentation.PoetDetailsScreen
import com.example.choronopoets.presentation.PoetScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun NavigationScreen() {
    val navController = rememberNavController()
    val poetryViewModel: PoetryViewModel = koinViewModel()

    NavHost(navController = navController, startDestination = Screen.CENTURY_SCREEN.route) {
        composable(Screen.CENTURY_SCREEN.route) {
            CenturyScreen(navController = navController, viewModel = poetryViewModel)
        }
        composable(
            route = Screen.POETS_SCREEN.route,
            arguments = listOf(navArgument("centuryId"){ type = NavType.IntType })
        ) { backStackEntry ->
            val centuryId = backStackEntry.arguments?.getInt("centuryId")?: 0
            PoetScreen(navController = navController, viewModel = poetryViewModel, centuryId)
        }
        composable(
            route = Screen.POET_DETAIL_SCREEN.route,
            arguments = listOf(navArgument("poetId"){ type = NavType.IntType })
        ) { backStackEntry ->
            val poetId = backStackEntry.arguments?.getInt("poetId")?: 0
            PoetDetailsScreen(navController = navController, viewModel = poetryViewModel, poetId)
        }
        composable(
            route = Screen.POEM_DETAIL_SCREEN.route,
            arguments = listOf(navArgument("poemId"){ type = NavType.IntType })
        ) { backStackEntry ->
            val poemId = backStackEntry.arguments?.getInt("poemId")?: 0
            PoemDetailScreen(navController = navController, viewModel = poetryViewModel, poemId = poemId)
        }
    }
}
