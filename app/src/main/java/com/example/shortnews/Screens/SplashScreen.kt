package com.example.shortnews.Screens

import android.graphics.ColorFilter
import android.view.animation.OvershootInterpolator
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.shortnews.Navigation.screens
import com.example.shortnews.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    val scale = remember {
        androidx.compose.animation.core.Animatable(0f)
    }

    LaunchedEffect(Unit) {
        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 800,
                easing = {
                    OvershootInterpolator(3f).getInterpolation(it)
                })
        )
        delay(2000)
        navController.navigate(screens.MainScreen.name){
            popUpTo(screens.SplashScreen.name) {
                inclusive = true
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
            .scale(scale.value)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.news),
                colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(color = Color.Transparent,
                    BlendMode.Multiply),// Replace with your own image resource
                contentDescription = "Splash Screen Image",
                modifier = Modifier
                    .size(150.dp)
                    .padding(bottom = 5.dp),
                contentScale = ContentScale.Fit
            )

            Text(
                text = "ShortNews",
                style = TextStyle(
                    fontWeight = FontWeight.W600,
                    fontSize = 30.sp
                ),
                color = Color.Red
            )
        }
    }
}
