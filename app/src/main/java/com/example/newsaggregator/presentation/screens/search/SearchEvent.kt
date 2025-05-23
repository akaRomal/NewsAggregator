package com.example.newsaggregator.presentation.screens.search

sealed class SearchEvent {
    data class Update(val query: String): SearchEvent()
}