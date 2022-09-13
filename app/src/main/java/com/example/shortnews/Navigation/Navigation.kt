package com.example.shortnews.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.shortnews.Screens.mainScreen

@Composable
fun Navigation(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = screens.MainScreen.name ){
        composable(screens.MainScreen.name){
            mainScreen(navController)
        }
    }
}


