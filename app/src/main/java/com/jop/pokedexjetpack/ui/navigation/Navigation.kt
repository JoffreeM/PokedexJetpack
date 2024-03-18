package com.jop.pokedexjetpack.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jop.pokedexjetpack.ui.screen.favorite.FavoriteScreen
import com.jop.pokedexjetpack.ui.screen.home.HomeScreen

@Composable
fun Navigation(navController: NavController, onIsDark: (Boolean) -> Unit = {}) {
    NavHost(navController = navController as NavHostController,
        startDestination = Screens.HOME){
        composable(Screens.HOME){
            HomeScreen(navController = navController, onIsDark = onIsDark)
        }
        composable(Screens.FAVORITE){
            FavoriteScreen(navController = navController)
        }
    }
}