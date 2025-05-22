package com.example.newsaggregator.presentation.screens.reader

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsaggregator.domain.usecase.AddBookmarkUseCase
import com.example.newsaggregator.domain.usecase.DeleteBookmarkUseCase
import com.example.newsaggregator.domain.usecase.IsBookmarkedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ReaderViewModel @Inject constructor(
    private val isBookmarkedUseCase: IsBookmarkedUseCase,
    private val deleteBookmarkUseCase: DeleteBookmarkUseCase,
    private val addBookmarkUseCase: AddBookmarkUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(ReaderState())
    val uiState: StateFlow<ReaderState> = _uiState

    fun handleEvent(event: ReaderEvent) {
        when (event) {
            is ReaderEvent.UpdateUrl -> {
                event.url?.let { newUrl ->
                    if (_uiState.value.url != newUrl) {
                        _uiState.value = _uiState.value.copy(url = newUrl)
                    }
                    isBookmarked(guid = newUrl)
                }
            }

            ReaderEvent.UpdateBookmark -> updateBookmark()
        }
    }

    private fun updateBookmark() {
        _uiState.value.url?.let { guid ->
            viewModelScope.launch(Dispatchers.IO) {
                if (_uiState.value.isSaved) {
                    deleteBookmarkUseCase.execute(guid = guid)
                } else {
                    addBookmarkUseCase.execute(guid = guid)
                }
            }
        }
    }

    private fun isBookmarked(guid: String) {
        viewModelScope.launch(Dispatchers.IO) {
            isBookmarkedUseCase.execute(guid = guid)
                .collect {
                    withContext(Dispatchers.Main) {
                        _uiState.value = _uiState.value.copy(isSaved = it)
                    }
                }
        }
    }
}