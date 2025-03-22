package com.example.choronopoets.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.choronopoets.PoetryViewModel
import com.example.choronopoets.R
import com.example.choronopoets.components.CommonInfoPage1
import com.example.choronopoets.components.CommonInfoPage2
import com.example.choronopoets.components.CommonTopBar
import com.example.choronopoets.images.poetImages

@Composable
fun PoetDetailsScreen(navController: NavController, viewModel: PoetryViewModel, poetId: Int) {
    val poet by viewModel.selectedPoet.collectAsStateWithLifecycle()
    val poems by viewModel.poems.collectAsStateWithLifecycle()
    val isDarkMode by viewModel.backgroundView.collectAsStateWithLifecycle()

    LaunchedEffect(poetId) {
        viewModel.loadPoetDetails(poetId) // Загружаем конкретного поэта
        viewModel.loadPoems(poetId)       // Загружаем его стихи
    }

    Scaffold(
        topBar = {
            CommonTopBar(
                title = poet?.name ?: "Неизвестный поэт",
                onBackClick = { navController.popBackStack() },
                onToggleTheme = { viewModel.toggleTheme() },
                isDarkMode = isDarkMode,
                titleFontSize = 32
            )
        },
        containerColor = if (isDarkMode) Color(0xFF121212) else Color(0xFFF5F5F5)
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .padding(start = 4.dp, end = 4.dp)
        ) {
            item {
                poet?.let {
                    CommonInfoPage1(
                        imageResId = poetImages[it.id] ?: R.drawable.ic_launcher_background,
                        bio = it.bio,
                        isDarkMode = isDarkMode
                    )
                }
            }

            items(poems) { poem ->
                CommonInfoPage2(
                    poems = poem.title,
                    content = poem.content,
                    isDarkMode = isDarkMode
                )
            }
        }
    }
}
