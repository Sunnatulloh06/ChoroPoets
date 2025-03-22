package com.example.choronopoets.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CommonListCard(
    imageResId: Int,
    text: String,
    onClick: () -> Unit,
    onColorChange: Boolean
){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(110.dp)
            .padding(8.dp)
            .padding(top = 6.dp)
            .shadow(elevation = 4.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = if (onColorChange) Color.White else Color(0xFF1E1E1E),
            contentColor = if (onColorChange) Color.Black else Color(0xFFFFFFFF)
        )
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ){
            Image(
                painter = painterResource(imageResId),
                contentDescription = text,
                modifier = Modifier
                    .padding(10.dp)
                    .size(65.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Text(
                text = text,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

