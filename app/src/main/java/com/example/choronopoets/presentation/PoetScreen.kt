package com.example.choronopoets.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.choronopoets.components.CommonPoetsListCard
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

    val filtered = state.selectedNationality?.let { code ->
        state.poets.filter { it.nationality == code }
    } ?: state.poets

    LaunchedEffect(centuryId) {
        viewModel.processEvent(PoetryUIEvent.LoadPoets(centuryId))
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(6.dp)
    ) {
        CommonTopBar(
            onBackClick = { navController.popBackStack() },
            onToggleTheme = { viewModel.processEvent(PoetryUIEvent.ToggleTheme) },
            onSearchClick = {  },
            onProfileClick = {  },
            isDarkMode = state.isDarkMode
        )

        Text(
            text = "$centuryName Век",
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(26.dp),
            verticalArrangement = Arrangement.spacedBy(22.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            items(filtered) { poet ->
                CommonPoetsListCard(
                    imageResId = poetImages[poet.id]?: 0,
                    text = poet.name,
                    onClick = {
                        navController.navigate(Screen.POET_DETAIL_SCREEN.createRoute(poet.id))
                    }
                )
            }
        }
    }
}



//val centuryName = state.centuries.firstOrNull {
//    it.id == centuryId
//}?.century
//
//LaunchedEffect(centuryId) {
//    viewModel.processEvent(PoetryUIEvent.LoadPoets(centuryId))
//}
//
//val filtered = state.selectedNationality?.let { code ->
//    state.poets.filter { it.nationality == code }
//} ?: state.poets
//
//Scaffold(
//topBar = {
//    CommonTopBar(
//        title = "Поэты ${centuryName}а",
//        onBackClick = { navController.popBackStack() },
//        onToggleTheme = {
//            viewModel.processEvent(PoetryUIEvent.ToggleTheme)
//        },
//        isDarkMode = state.isDarkMode,
//        titleFontSize = 36
//    )
//},
//containerColor = if (state.isDarkMode) Color(0xFF121212) else Color(0xFFF5F5F5)
//){ innerPadding ->
//
//    LazyColumn(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(innerPadding)
//            .padding(10.dp)
//    ){
//        item {
//            CommonNationalityCard(
//                selected = state.selectedNationality,
//                onSelect = { code ->
//                    viewModel.processEvent(PoetryUIEvent.SelectNationality(code))
//                }
//            )
//        }
//
//        items(filtered){ poet ->
//            CommonListCard(
//                text = poet.name,
//                imageResId = poetImages[poet.id] ?: 0,
//                onColorChange = !state.isDarkMode,
//                onClick = {
//                    navController.navigate(Screen.POET_DETAIL_SCREEN.createRoute(poetId = poet.id))
//                }
//            )
//        }
//    }
//}