package com.example.shortnews.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.shortnews.Screens.MainScreen
import com.example.shortnews.Screens.SplashScreen


@Composable
fun Navigation(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = screens.SplashScreen.name ){
        composable(screens.MainScreen.name){
            MainScreen(navController)
        }

        composable(screens.SplashScreen.name, ){
            SplashScreen(navController = navController)
        }
    }
}


