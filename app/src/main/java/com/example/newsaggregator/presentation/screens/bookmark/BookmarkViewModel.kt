package com.example.newsaggregator.presentation.screens.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsaggregator.di.MapperPresentation
import com.example.newsaggregator.domain.usecase.DeleteBookmarkUseCase
import com.example.newsaggregator.domain.usecase.GetAllBookmarksUseCase
import com.example.newsaggregator.presentation.mapper.Mapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val deleteBookmarkUseCase: DeleteBookmarkUseCase,
    private val getAllBookmarksUseCase: GetAllBookmarksUseCase,
    @MapperPresentation private val mapper: Mapper,
) : ViewModel() {
    private val _uiState = MutableStateFlow(BookmarkState())
    val uiState: StateFlow<BookmarkState> = _uiState

    init {
        getAll()
    }

    fun handleEvent(event: BookmarkEvent) {
        when (event) {
            is BookmarkEvent.Delete -> delete(event.guid)
        }
    }

    private fun delete(guid: String) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteBookmarkUseCase.execute(guid = guid)
        }
    }

    private fun getAll() {
        viewModelScope.launch(Dispatchers.IO) {
            getAllBookmarksUseCase.execute()
                .collect { items ->
                    val bookmarks = mapper.fromBookmarkToBookmarkItem(items)
                    withContext(Dispatchers.Main) {
                        _uiState.value = _uiState.value.copy(
                            newsArticleItems = bookmarks
                        )
                    }

                }
        }
    }
}