package com.example.newsaggregator.presentation.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsaggregator.domain.usecase.SearchByQueryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchByQueryUseCase: SearchByQueryUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(SearchState())
    val uiState: StateFlow<SearchState> = _uiState

    fun eventHandle(event: SearchEvent) {
        when (event) {
            is SearchEvent.Update -> search(event.query)
        }
    }

    private fun search(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            searchByQueryUseCase.execute(query = query)
        }
    }
}