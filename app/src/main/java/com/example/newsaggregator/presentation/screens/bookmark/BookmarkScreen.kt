package com.example.newsaggregator.presentation.screens.bookmark

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.example.newsaggregator.R
import com.example.newsaggregator.presentation.components.CustomBottomBar
import com.example.newsaggregator.presentation.components.NewsArticleCard
import com.example.newsaggregator.presentation.navigation.NavDestination
import com.example.newsaggregator.presentation.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookmarkScreen(
    navController: NavHostController,
    topBarColors: TopAppBarColors,
    modifier: Modifier = Modifier,
    viewModel: BookmarkViewModel = hiltViewModel<BookmarkViewModel>()
) {
    val state by viewModel.uiState.collectAsState()
    Scaffold(
        modifier = modifier
            .background(AppTheme.colorsScheme.background)
            .fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.text_top_bar_bookmark),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        style = AppTheme.typography.headlineLarge
                    )
                },
                colors = topBarColors
            )
        },
        bottomBar = {
            CustomBottomBar(
                selectItem = NavDestination.Bookmark,
                navController = navController,
            )
        }
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            items(state.newsArticleItems) { newsItem ->
                NewsArticleCard(
                    onClick = {
                        navController.navigate(
                            NavDestination.Reader(url = newsItem.articleUrl),
                        ) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    titleArticle = newsItem.title,
                    descriptionArticle = newsItem.description,
                    date = newsItem.date,
                    author = newsItem.author,
                    modifier = Modifier.padding(horizontal = AppTheme.size.medium),
                    onClickBookmark = {
                        viewModel.handleEvent(BookmarkEvent.Delete(newsItem.articleUrl))
                    },
                    bookmarked = true,
                    imageUrl = newsItem.imageUrl,
                    imageDescription = stringResource(
                        R.string.image_article,
                        newsItem.title
                    ),
                )
            }
        }
    }
}