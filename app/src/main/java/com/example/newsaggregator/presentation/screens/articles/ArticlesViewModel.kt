package com.example.newsaggregator.presentation.screens.articles

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsaggregator.di.MapperPresentation
import com.example.newsaggregator.domain.model.Errors
import com.example.newsaggregator.domain.usecase.GetAllTagsUseCase
import com.example.newsaggregator.domain.usecase.GetNewsUseCase
import com.example.newsaggregator.domain.usecase.SearchByTagUseCase
import com.example.newsaggregator.domain.usecase.SortedByDateUseCase
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
    private val getNewsUseCase: GetNewsUseCase,
    private val getAllTagsUseCase: GetAllTagsUseCase,
    private val sortedByDateUseCase: SortedByDateUseCase,
    private val searchByTagUseCase: SearchByTagUseCase,
    @MapperPresentation private val mapper: Mapper,
) : ViewModel() {
    private val _uiState = MutableStateFlow(ArticlesState())
    val uiState: StateFlow<ArticlesState> = _uiState

    init {
        getAllNews()
        getAllTags()
    }

    fun handleEvent(event: ArticlesEvent) {
        when (event) {
            is ArticlesEvent.SearchByTag -> searchByTag(event.tag)
            is ArticlesEvent.SearchByQuery -> searchByQuery(event.query)
            ArticlesEvent.SortByTime -> sortByTime()
            ArticlesEvent.Update -> getAllNews()
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

    private fun getAllNews() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value = _uiState.value.copy(isLoading = true)
            val response = getNewsUseCase.execute()
            val newsItems = mapper.fromNewsArticleToNewsArticleItem(response.newsArticles)
            withContext(Dispatchers.Main) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    isServerError = response.error == Errors.HTTP,
                    isNetworkError = response.error == Errors.NETWORK,
                    isTimeoutError = response.error == Errors.TIMEOUT,
                    isUnknownError = response.error == Errors.UNKNOWN,
                    selectedTag = "",
                    newsArticleItems = newsItems
                )
            }
        }
    }

    private fun searchByTag(tag: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val newsItems = mapper.fromNewsArticleToNewsArticleItem(
                searchByTagUseCase.execute(tag = tag)
            )
            withContext(Dispatchers.Main) {
                _uiState.value = _uiState.value.copy(
                    selectedTag = tag,
                    newsArticleItems = newsItems
                )
            }
        }
    }

    private fun searchByQuery(query: String) {
        // TODO()
    }

    private fun sortByTime() {
        val dateSortUp = !_uiState.value.dateSortAsc

        viewModelScope.launch(Dispatchers.IO) {
            val newsItems = mapper.fromNewsArticleToNewsArticleItem(
                sortedByDateUseCase.execute(sortedAsc = dateSortUp)
            )
            withContext(Dispatchers.Main) {
                _uiState.value = _uiState.value.copy(
                    dateSortAsc = dateSortUp,
                    newsArticleItems = newsItems
                )
            }
        }
    }
}