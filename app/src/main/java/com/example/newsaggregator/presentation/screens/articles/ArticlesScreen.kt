package com.example.newsaggregator.presentation.screens.articles

import android.util.Log
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.newsaggregator.R
import com.example.newsaggregator.presentation.components.CustomBottomBar
import com.example.newsaggregator.presentation.components.NewsArticleCard
import com.example.newsaggregator.presentation.components.TagsContainer
import com.example.newsaggregator.presentation.navigation.NavDestination
import com.example.newsaggregator.presentation.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticlesScreen(
    navController: NavHostController,
    viewModel: ArticlesViewModel = hiltViewModel<ArticlesViewModel>()
) {
    val state by viewModel.uiState.collectAsState()
    Log.d("AAA", "init ArticlesScreen")


    Scaffold(
        modifier = Modifier
            .background(AppTheme.colorsScheme.background)
            .fillMaxSize(),
        topBar = {
            TopAppBar(title = {
                Text(
                    text = "App Bar", modifier = Modifier
                        .fillMaxSize()
                        .background(AppTheme.colorsScheme.outline)
                )
            })
        },
        bottomBar = {
            CustomBottomBar(
                selectItem = NavDestination.Home,
                navController = navController,
//                modifier = TODO()
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
                    item {
                        LazyRow(
                            modifier = Modifier
                                .fillMaxWidth()
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
                        onClick = { /** TODO() реализовть переход как добавлю навигацию */ },
                        titleArticle = newsItem.title,
                        descriptionArticle = stringResource(
                            R.string.image_article,
                            newsItem.description
                        ),
                        date = newsItem.date,
                        author = newsItem.author,
                        modifier = Modifier.padding(horizontal = AppTheme.size.medium),
                        tag = newsItem.tag,
                        onClickTag = {
                            viewModel.handleEvent(
                                ArticlesEvent.SearchByTag(
                                    newsItem.tag
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