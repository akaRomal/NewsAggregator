package com.example.newsaggregator.presentation.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.example.newsaggregator.presentation.model.NavItem
import com.example.newsaggregator.presentation.navigation.NavDestination
import com.example.newsaggregator.presentation.ui.theme.AppTheme

@Composable
fun CustomBottomBar(
    selectItem: NavDestination,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val items = NavItem.getItemsBottomBar()
    val elementColor = AppTheme.colorsScheme.onPrimary
    val selectedIndicatorColor = AppTheme.colorsScheme.onSurfaceSecondary
    val colorsItem = NavigationBarItemDefaults.colors().copy(
        selectedIconColor = elementColor,
        selectedTextColor = elementColor,
        selectedIndicatorColor = selectedIndicatorColor,
        unselectedIconColor = elementColor,
        unselectedTextColor = elementColor,
    )

    NavigationBar(
        modifier = modifier,
        containerColor = AppTheme.colorsScheme.background
    ) {
        items.forEach { screen ->
            NavigationItem(
                screen = screen,
                selectedItem = screen.route == selectItem,
                navController = navController,
                colors = colorsItem,
            )
        }
    }
}

@Composable
private fun RowScope.NavigationItem(
    screen: NavItem,
    selectedItem: Boolean,
    navController: NavHostController,
    colors: NavigationBarItemColors,
) {
    val label = stringResource(id = screen.label)
    NavigationBarItem(
        selected = selectedItem,
        onClick = {
            navController.navigate(screen.route){
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        },
        icon = {
            Icon(
                painter = painterResource(id = screen.icon),
                contentDescription = label,
            )
        },
        label = {
            Text(
                text = label,
                style = AppTheme.typography.bodyMedium
            )
        },
        colors = colors
    )
}