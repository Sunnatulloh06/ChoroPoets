package com.example.choronopoets.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.choronopoets.R
import com.example.choronopoets.components.CommonInfoPage1
import com.example.choronopoets.components.CommonPoemsListCard
import com.example.choronopoets.components.CommonTopBar
import com.example.choronopoets.images.poetImages
import com.example.choronopoets.navigation.Screen
import com.example.choronopoets.viewModel.PoetryUIEvent
import com.example.choronopoets.viewModel.PoetryViewModel

@Composable
fun PoetDetailsScreen(navController: NavController, viewModel: PoetryViewModel, poetId: Int) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(poetId) {
        viewModel.processEvent(PoetryUIEvent.LoadPoetDetails(poetId))
        viewModel.processEvent(PoetryUIEvent.LoadPoems(poetId))
    }

    Scaffold(
        topBar = {
            CommonTopBar(
                title = state.selectedPoet?.name ?: "Неизвестный поэт",
                onBackClick = { navController.popBackStack() },
                onToggleTheme = {
                    viewModel.processEvent(PoetryUIEvent.ToggleTheme)
                },
                isDarkMode = state.isDarkMode,
                titleFontSize = 32
            )
        },
        containerColor = if (state.isDarkMode) Color(0xFF121212) else Color(0xFFF5F5F5)
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .padding(start = 4.dp, end = 4.dp)
        ) {
            item {
                state.selectedPoet?.let {
                    CommonInfoPage1(
                        imageResId = poetImages[it.id] ?: R.drawable.ic_launcher_background,
                        bio = it.bio,
                        isDarkMode = state.isDarkMode
                    )
                }
            }

            items(state.poems) { poem ->
                CommonPoemsListCard(
                    title = poem.title,
                    onClick = {
                        navController.navigate(Screen.POEM_DETAIL_SCREEN.createRoute(poemId = poem.id))
                    },
                    onColorChange = !state.isDarkMode
                )
            }
        }
    }
}
