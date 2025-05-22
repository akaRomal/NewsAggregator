package com.example.newsaggregator.presentation.screens.reader

sealed class ReaderEvent {
    data class UpdateUrl(val url: String?) : ReaderEvent()
    data object UpdateBookmark : ReaderEvent()
}