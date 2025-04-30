package com.example.choronopoets.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.choronopoets.R
import com.example.choronopoets.components.CommonListCard
import com.example.choronopoets.components.CommonTopBar
import com.example.choronopoets.images.centuryImages
import com.example.choronopoets.navigation.Screen
import com.example.choronopoets.ui.theme.Black
import com.example.choronopoets.ui.theme.White
import com.example.choronopoets.viewModel.PoetryUIEvent
import com.example.choronopoets.viewModel.PoetryViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CenturyScreen(navController: NavController, viewModel: PoetryViewModel) {
    val state by viewModel.state.collectAsState()

    Scaffold(
        topBar = {
            CommonTopBar(
                "Век Поэтов",
                isDarkMode = state.isDarkMode,
                onToggleTheme = {
                    viewModel.processEvent(PoetryUIEvent.ToggleTheme)
                },
                titleFontSize = 36
            )
        },
        containerColor = if (state.isDarkMode) Black else White
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(10.dp)
        ) {
            items(state.centuries) { century ->
                val imageResId = centuryImages[century.century] ?: R.drawable.ic_launcher_background
                CommonListCard(
                    imageResId = imageResId,
                    text = century.century,
                    onClick = {
                        navController.navigate(Screen.POETS_SCREEN.createRoute(centuryId = century.id))
                    },
                    onColorChange = !state.isDarkMode
                )
            }
        }
    }
}
