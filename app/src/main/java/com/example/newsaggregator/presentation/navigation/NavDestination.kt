package com.example.newsaggregator.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface NavDestination {
    @Serializable
    data object Home: NavDestination
    @Serializable
    data object Article: NavDestination
    @Serializable
    data object Bookmark: NavDestination
}
