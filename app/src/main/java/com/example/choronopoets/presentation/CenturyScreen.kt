package com.example.choronopoets.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.choronopoets.R
import com.example.choronopoets.components.CommonCenturyListCard
import com.example.choronopoets.components.CommonTopBar
import com.example.choronopoets.components.UserNick
import com.example.choronopoets.images.centuryImages
import com.example.choronopoets.navigation.Screen
import com.example.choronopoets.viewModel.PoetryUIEvent
import com.example.choronopoets.viewModel.PoetryViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CenturyScreen(navController: NavController, viewModel: PoetryViewModel) {
    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(6.dp)
    ) {
        CommonTopBar(
            onToggleTheme = { viewModel.processEvent(PoetryUIEvent.ToggleTheme) },
            onSearchClick = {  },
            isDarkMode = state.isDarkMode
        )

        Text(
            text = "Века",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(25.dp))

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            items(state.centuries) { century ->
                val imageResId = centuryImages[century.century]?: R.drawable.ic_launcher_background

                CommonCenturyListCard (
                    imageResId,
                    century.century,
                    onClick = {
                        navController.navigate(Screen.POETS_SCREEN.createRoute(centuryId = century.id))
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(95.dp))

        UserNick(
//          Avatar will be added later
            imageResId = R.drawable.ic_launcher_background,
            text = "Пока останется так",
            isDarkMode = state.isDarkMode
        )
    }
}


//Scaffold(
//topBar = {
//    CommonTopBar(
//        "Век Поэтов",
//        isDarkMode = state.isDarkMode,
//        onToggleTheme = {
//            viewModel.processEvent(PoetryUIEvent.ToggleTheme)
//        },
//        titleFontSize = 36
//    )
//},
//containerColor = if (state.isDarkMode) Black else White
//) { innerPadding ->
//
//}


//LazyColumn(
//modifier = Modifier
//.fillMaxSize()
//.padding(innerPadding)
//.padding(10.dp)
//) {
//    items(state.centuries) { century ->
//        val imageResId = centuryImages[century.century] ?: R.drawable.ic_launcher_background
//        CommonListCard(
//            imageResId = imageResId,
//            text = century.century,
//            onClick = {
//                navController.navigate(Screen.POETS_SCREEN.createRoute(centuryId = century.id))
//            },
//            onColorChange = !state.isDarkMode
//        )
//    }
//}