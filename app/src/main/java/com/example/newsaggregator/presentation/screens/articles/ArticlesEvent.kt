package com.example.newsaggregator.presentation.screens.articles

sealed class ArticlesEvent {

    data class SearchByTag(val tag: String) : ArticlesEvent()

    data class SearchByQuery(val query: String) : ArticlesEvent()

    data object SortByTime: ArticlesEvent()
}