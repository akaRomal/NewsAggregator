package com.example.newsaggregator.presentation.screens.bookmark

sealed class BookmarkEvent {
    data class Delete(val guid: String): BookmarkEvent()
}