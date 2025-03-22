package com.example.choronopoets.presentation

import androidx.compose.foundation.layout.fillMaxSize
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
import com.example.choronopoets.components.CommonListCard
import com.example.choronopoets.components.CommonTopBar
import com.example.choronopoets.navigation.Screen
import com.example.choronopoets.images.poetImages

@Composable
fun PoetScreen(navController: NavController, viewModel: PoetryViewModel, centuryId: Int){
    val poets by viewModel.poets.collectAsStateWithLifecycle()
    val centuries by viewModel.centuries.collectAsStateWithLifecycle()
    val selectedCentury = centuries.find { it.id == centuryId }?.century ?: "Неизвестный век"
    val isDarkMode by viewModel.backgroundView.collectAsStateWithLifecycle()

    LaunchedEffect(centuryId) {
        viewModel.loadPoets(centuryId)
    }

    Scaffold(
        topBar = {
            CommonTopBar(
                title = "Поэты ${selectedCentury}а",
                onBackClick = { navController.popBackStack() },
                onToggleTheme = { viewModel.toggleTheme() },
                isDarkMode = isDarkMode,
                titleFontSize = 36
            )
        },
        containerColor = if (isDarkMode) Color(0xFF121212) else Color(0xFFF5F5F5)
    ){ innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(10.dp)
        ){
            items(poets){ poet ->
                CommonListCard(
                    text = poet.name,
                    imageResId = poetImages[poet.id] ?: R.drawable.ic_launcher_background,
                    onColorChange = !isDarkMode,
                    onClick = {
                        navController.navigate(Screen.PoetDetailsScreen.createRoute(poetId = poet.id))
                    }
                )
            }
        }
    }
}


