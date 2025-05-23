package com.example.newsaggregator.presentation.screens.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.newsaggregator.presentation.components.CustomBottomBar
import com.example.newsaggregator.presentation.navigation.NavDestination
import com.example.newsaggregator.presentation.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    navController: NavHostController,
    topBarColors: TopAppBarColors,
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = hiltViewModel<SearchViewModel>()
) {
//    val state by viewModel.uiState.collectAsState()
    Scaffold(
        modifier = modifier
            .background(AppTheme.colorsScheme.background)
            .fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {

                    OutlinedTextField(
                        value = "vvvvvvvvvvvvvvvv",
                        onValueChange = {},
                        singleLine = true,
                    )
                },
                colors = topBarColors
            )
        },
        bottomBar = {
            CustomBottomBar(
                selectItem = NavDestination.Search,
                navController = navController,
            )
        }
    ) { paddingValues ->
    }
}