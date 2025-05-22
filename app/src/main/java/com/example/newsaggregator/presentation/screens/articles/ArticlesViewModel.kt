package com.example.newsaggregator.presentation.screens.articles

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsaggregator.di.MapperPresentation
import com.example.newsaggregator.domain.usecase.GetAllNewsUseCase
import com.example.newsaggregator.domain.usecase.GetAllTagsUseCase
import com.example.newsaggregator.domain.usecase.UpdateNewsUseCase
import com.example.newsaggregator.presentation.mapper.Mapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ArticlesViewModel @Inject constructor(
    private val updateNewsUseCase: UpdateNewsUseCase,
    private val getAllNewsUseCase: GetAllNewsUseCase,
    private val getAllTagsUseCase: GetAllTagsUseCase,
    @MapperPresentation private val mapper: Mapper,
) : ViewModel() {
    private val _uiState = MutableStateFlow(ArticlesState())
    val uiState: StateFlow<ArticlesState> = _uiState

    init {
        updateAllNews()
        getAllTags()
        getAllNews()
    }

    fun handleEvent(event: ArticlesEvent) {
        when (event) {
            is ArticlesEvent.SearchByTag -> searchByTag(event.tag)
            is ArticlesEvent.SearchByQuery -> searchByQuery(event.query)
            ArticlesEvent.SortByTime -> sortByTime()
        }
    }

    private fun getAllNews() {
        viewModelScope.launch(Dispatchers.IO) {
            getAllNewsUseCase.execute()
                .collect { newsList ->
                    val newsItems = mapper.fromNewsArticleToNewsArticleItem(newsList)
                    withContext(Dispatchers.Main) {
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            newsArticleItems = newsItems
                        )
                    }
                }
        }
    }

    private fun getAllTags() {
        viewModelScope.launch(Dispatchers.IO) {
            getAllTagsUseCase.execute()
                .collect { tagsList ->
                    withContext(Dispatchers.Main) {
                        _uiState.value = _uiState.value.copy(
                            tagCloudItems = tagsList
                        )
                    }
                }
        }
    }

    private fun updateAllNews() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value = _uiState.value.copy(isLoading = true)
            updateNewsUseCase.execute(query = null)
        }
    }

    private fun searchByTag(tag: String) {
        // TODO()
    }

    private fun searchByQuery(tag: String) {
        // TODO()
    }

    private fun sortByTime() {
        // TODO()
    }
}