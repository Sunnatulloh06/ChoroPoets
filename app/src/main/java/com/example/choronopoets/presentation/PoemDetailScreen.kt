package com.example.choronopoets.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.choronopoets.components.CommonTopBar
import com.example.choronopoets.viewModel.PoetryUIEvent
import com.example.choronopoets.viewModel.PoetryViewModel

@Composable
fun PoemDetailScreen(navController: NavController, viewModel: PoetryViewModel, poemId: Int) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val poem = state.selectedPoem
    val scrollSate = rememberScrollState()

    LaunchedEffect(poemId) {
        viewModel.processEvent(PoetryUIEvent.LoadPoemById(poemId))
    }

//    Scaffold(
//        topBar = {
//            CommonTopBar(
//                title = poem?.title ?: "Неизвестный стих",
//                onBackClick = { navController.popBackStack() },
//                onToggleTheme = {
//                    viewModel.processEvent(PoetryUIEvent.ToggleTheme)
//                },
//                isDarkMode = state.isDarkMode,
//                titleFontSize = 32
//            )
//        },
//        containerColor = if (state.isDarkMode) Color(0xFF121212) else Color(0xFFF5F5F5)
//    ) { innerPadding ->
//        poem?.let { poem ->
//            Column(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .verticalScroll(scrollSate)
//                    .padding(innerPadding)
//                    .padding(16.dp),
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                Text(
//                    text = poem.title,
//                    fontSize = 24.sp,
//                    fontWeight = FontWeight.SemiBold,
//                    textAlign = TextAlign.Center,
//                    modifier = Modifier.padding(vertical = 8.dp),
//                    color = if (state.isDarkMode) Color.White else Color.Black
//                )
//                Spacer(modifier = Modifier.height(12.dp))
//                Text(
//                    text = poem.content,
//                    fontSize = 20.sp,
//                    fontWeight = FontWeight.Medium,
//                    fontFamily = FontFamily.SansSerif,
//                    textAlign = TextAlign.Start,
//                    modifier = Modifier.fillMaxSize(),
//                    color = if (state.isDarkMode) Color.White else Color.Black
//                )
//            }
//        }
//    }
}

