package com.example.newsaggregator.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface NavDestination {
    @Serializable
    data class Home(val query: String? = null) : NavDestination

    @Serializable
    data class Reader(val url: String? = null) : NavDestination

    @Serializable
    data object Search : NavDestination

    @Serializable
    data object Bookmark : NavDestination
}