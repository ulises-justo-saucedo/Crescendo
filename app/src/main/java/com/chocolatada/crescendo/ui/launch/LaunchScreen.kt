package com.chocolatada.crescendo.ui.launch

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chocolatada.crescendo.R
import com.chocolatada.crescendo.ui.theme.White

@Composable
fun LaunchScreen(onStartNowClick: () -> Unit){
    Image(
        painter = painterResource(id = R.drawable.bc_launch),
        contentDescription = "Background Image",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.FillBounds
    )
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            CustomText(
                text = "Crescendo",
                fontSize = 60.sp,
                topPadding = 40.dp
            )
            CustomText(
                text = "Harmonize your world with the music you love. Everything in just one touch.",
                fontSize = 30.sp,
                topPadding = 0.dp
            )
        }
        Button(
            onClick = onStartNowClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 60.dp),
            colors = ButtonColors(Color.DarkGray, White, Color.Red, White)
        ) {
            CustomText(
                text = "Start now!",
                fontSize = 20.sp,
                topPadding = 0.dp
            )
        }
    }
}

@Composable
fun CustomText(text: String, fontSize: TextUnit, topPadding: Dp){
    Text(
        text = text,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = topPadding),
        color = White,
        fontSize = fontSize,
        fontFamily = FontFamily(Font(R.font.mainfont))
    )
}