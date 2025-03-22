package com.example.choronopoets.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.choronopoets.PoetryViewModel
import com.example.choronopoets.R
import com.example.choronopoets.components.CommonListCard
import com.example.choronopoets.components.CommonTopBar
import com.example.choronopoets.images.centuryImages
import com.example.choronopoets.navigation.Screen
import com.example.choronopoets.ui.theme.Black
import com.example.choronopoets.ui.theme.White

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CenturyScreen(navController: NavController, viewModel: PoetryViewModel) {
    val isDarkMode by viewModel.backgroundView.collectAsStateWithLifecycle()
    val centuries by viewModel.centuries.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            CommonTopBar(
                "Век Поэтов",
                isDarkMode = isDarkMode,
                onToggleTheme = { viewModel.toggleTheme() },
                titleFontSize = 36
            )
        },
        containerColor = if (isDarkMode) Black else White
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(10.dp)
        ) {
            items(centuries) { century ->
                val imageResId = centuryImages[century.century] ?: R.drawable.ic_launcher_background
                CommonListCard(
                    imageResId = imageResId,
                    text = century.century,
                    onClick = {
                        navController.navigate(Screen.POETS_SCREEN.createRoute(centuryId = century.id))
                    },
                    onColorChange = !isDarkMode
                )
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewCenturyScreen() {
//    CenturyScreen(onCenturyClick = {})
//}
