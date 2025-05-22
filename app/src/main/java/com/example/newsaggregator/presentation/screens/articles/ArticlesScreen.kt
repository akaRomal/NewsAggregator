package com.example.newsaggregator.presentation.screens.articles

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import com.example.newsaggregator.presentation.components.TagsContainer
import com.example.newsaggregator.presentation.navigation.NavDestination
import com.example.newsaggregator.presentation.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun ArticlesScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    query: String? = null,
    viewModel: ArticlesViewModel = hiltViewModel<ArticlesViewModel>()
) {
    val state by viewModel.uiState.collectAsState()
    val topBarColors = TopAppBarDefaults.topAppBarColors().copy(
        containerColor = AppTheme.colorsScheme.secondary,
        navigationIconContentColor = AppTheme.colorsScheme.onSecondary,
        titleContentColor = AppTheme.colorsScheme.onSecondary,
        actionIconContentColor = AppTheme.colorsScheme.onSecondary,
    )
    Scaffold(
        modifier = modifier
            .background(AppTheme.colorsScheme.background)
            .fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.text_top_bar_home),
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
                selectItem = NavDestination.Home(),
                navController = navController,
            )
        }
    ) { paddingValues ->

        ArticlesShimmerEffect(
            isLoading = state.isLoading,
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = AppTheme.size.medium)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                if (state.isUnknownError) {
                    item { Text(text = "Неизвестная ошибка") }
                } else if (state.isTimeoutError) {
                    item { Text(text = "Таймаут соединения") }
                } else if (state.isNetworkError) {
                    item { Text(text = "Нет соеденения с интернетом") }
                }

                if (!state.tagCloudItems.isEmpty()) {
                    stickyHeader {
                        LazyRow(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(AppTheme.colorsScheme.background)
                                .padding(vertical = AppTheme.size.small),
                            horizontalArrangement = Arrangement.spacedBy(AppTheme.size.small)
                        ) {
                            items(state.tagCloudItems) { tagItem ->
                                TagsContainer(
                                    onClick = {
                                        viewModel.handleEvent(
                                            ArticlesEvent.SearchByTag(
                                                tagItem
                                            )
                                        )
                                    },
                                    text = tagItem,
                                    selected = tagItem == state.selectedTag
                                )
                            }
                        }
                    }
                }

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
                        tags = newsItem.tag,
                        onClickTag = {
                            viewModel.handleEvent(
                                ArticlesEvent.SearchByTag(
                                    it
                                )
                            )
                        },
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
}