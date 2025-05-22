package com.example.newsaggregator.presentation.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.newsaggregator.R
import com.example.newsaggregator.presentation.navigation.NavDestination

sealed interface NavItem {

    val route: NavDestination

    @get:StringRes
    val label: Int

    @get:DrawableRes
    val icon: Int

    data object Home : NavItem {
        override val route: NavDestination
            get() = NavDestination.Home()
        override val label: Int
            get() = R.string.btn_menu_home
        override val icon: Int
            get() = R.drawable.ic_home
    }

    data object Search : NavItem {
        override val route: NavDestination
            get() = NavDestination.Search
        override val label: Int
            get() = R.string.btn_menu_search
        override val icon: Int
            get() = R.drawable.ic_search
    }

    data object Bookmark : NavItem {
        override val route: NavDestination
            get() = NavDestination.Bookmark
        override val label: Int
            get() = R.string.btn_menu_bookmark
        override val icon: Int
            get() = R.drawable.ic_bookmark
    }

    companion object {
        fun getItemsBottomBar() = listOf(Home, Search, Bookmark)
    }
}