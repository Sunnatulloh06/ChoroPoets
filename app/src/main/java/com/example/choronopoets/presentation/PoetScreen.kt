package com.example.choronopoets.presentation

import androidx.compose.foundation.layout.fillMaxSize
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
import com.example.choronopoets.components.CommonListCard
import com.example.choronopoets.components.CommonTopBar
import com.example.choronopoets.images.poetImages
import com.example.choronopoets.navigation.Screen
import com.example.choronopoets.viewModel.PoetryUIEvent
import com.example.choronopoets.viewModel.PoetryViewModel

@Composable
fun PoetScreen(navController: NavController, viewModel: PoetryViewModel, centuryId: Int){
    val state by viewModel.state.collectAsState()
    val centuryName = state.centuries.firstOrNull {
        it.id == centuryId
    }?.century

    LaunchedEffect(centuryId) {
        viewModel.processEvent(PoetryUIEvent.LoadPoets(centuryId))
    }

    Scaffold(
        topBar = {
            CommonTopBar(
                title = "Поэты ${centuryName}а",
                onBackClick = { navController.popBackStack() },
                onToggleTheme = {
                    viewModel.processEvent(PoetryUIEvent.ToggleTheme)
                },
                isDarkMode = state.isDarkMode,
                titleFontSize = 36
            )
        },
        containerColor = if (state.isDarkMode) Color(0xFF121212) else Color(0xFFF5F5F5)
    ){ innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(10.dp)
        ){
            items(state.poets){ poet ->
                CommonListCard(
                    text = poet.name,
                    imageResId = poetImages[poet.id] ?: R.drawable.ic_launcher_background,
                    onColorChange = !state.isDarkMode,
                    onClick = {
                        navController.navigate(Screen.POET_DETAIL_SCREEN.createRoute(poetId = poet.id))
                    }
                )
            }
        }
    }
}
