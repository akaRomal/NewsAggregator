package com.example.newsaggregator.presentation.screens

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.newsaggregator.presentation.navigation.NavDestination
import com.example.newsaggregator.presentation.screens.articles.ArticlesScreen
import com.example.newsaggregator.presentation.screens.bookmark.BookmarkScreen
import com.example.newsaggregator.presentation.screens.reader.ReaderScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = NavDestination.Home(),
        contentAlignment = Alignment.TopCenter,
    ) {
        composable<NavDestination.Home> {
            val args = it.toRoute<NavDestination.Home>()
            ArticlesScreen(
                navController = navController,
                query = args.query
            )
        }
        composable<NavDestination.Search> {
        }
        composable<NavDestination.Bookmark> {
            BookmarkScreen(navController = navController)
        }
        composable<NavDestination.Reader> {
            val args = it.toRoute<NavDestination.Reader>()
            ReaderScreen(
                navController = navController,
                urlArticle = args.url
            )
        }
    }
}