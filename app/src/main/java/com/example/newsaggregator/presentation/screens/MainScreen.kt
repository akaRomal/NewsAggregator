package com.example.newsaggregator.presentation.screens

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.newsaggregator.presentation.navigation.NavDestination
import com.example.newsaggregator.presentation.screens.articles.ArticlesScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = NavDestination.Home,
        contentAlignment = Alignment.TopCenter,
    ) {
        composable<NavDestination.Home> {
            ArticlesScreen(navController = navController)
        }
        composable<NavDestination.Article> {
        }
        composable<NavDestination.Bookmark> {
        }
    }
}