package com.example.choronopoets.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommonTopBar(
    title: String,
    titleFontSize: Int,
    onBackClick: (() -> Unit)? = null,
    onToggleTheme: () -> Unit,
    isDarkMode: Boolean
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = if (!isDarkMode) Color(0xFF26546D) else Color(0xFF02202F),
            titleContentColor = Color.White
        ),
        modifier = Modifier.height(120.dp),
        title = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(2.dp, top = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (onBackClick != null) {
                    IconButton(
                        onClick = onBackClick
                    ) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Назад",
                            tint = Color.White,
                            modifier = Modifier
                                .size(34.dp)
                                .padding(end = 8.dp)
                        )
                    }
                }

                Text(
                    text = title,
                    fontSize = titleFontSize.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif,
                    modifier = Modifier
                        .weight(1f)
                )

                IconButton(
                    onClick = onToggleTheme,
                    modifier = Modifier.padding(end = 10.dp)
                ) {
                    Icon(
                        imageVector = if (!isDarkMode) Icons.Default.WbSunny else Icons.Default.DarkMode,
                        contentDescription = "Toggle Theme button"
                    )
                }
            }
        }
    )
}

