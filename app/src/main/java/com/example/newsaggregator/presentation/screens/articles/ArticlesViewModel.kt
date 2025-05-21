package com.example.newsaggregator.presentation.screens.articles

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsaggregator.presentation.screens.articles.model.NewsArticleItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticlesViewModel @Inject constructor() : ViewModel() {
    private val _uaState = MutableStateFlow(ArticlesState())
    val uiState: StateFlow<ArticlesState> = _uaState

    init {
        _uaState.value = _uaState.value.copy(isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            delay(300)
            testInit()
            _uaState.value = _uaState.value.copy(isLoading = false)
        }
    }

    fun handleEvent(event: ArticlesEvent) {
        when (event) {
            is ArticlesEvent.SearchByTag -> searchByTag(event.tag)
            is ArticlesEvent.SearchByQuery -> searchByQuery(event.query)
            ArticlesEvent.SortByTime -> sortByTime()
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


    fun testInit() {
        _uaState.value = _uaState.value.copy(
            tagCloudItems = List(10) { "тег новости $it" },
            selectedTag = "тег новости 7",
            newsArticleItems = List(20) {
                NewsArticleItem(
                    articleId = null,
                    title = "Murder of Colombian model sparks outrage over rising femicides",
                    description = "María José Estupiñán, 22, was killed by a suspect who arrived at her house disguised as a delivery man, police say",
                    imageUrl = "https://i.guim.co.uk/img/media/839822cf0fb7b374ee976ea976d93ebc72e10232/1585_0_4854_3885/master/4854.jpg?width=140&quality=85&auto=format&fit=max&s=6472eb5f18540edf23dc4c6a84b84330",
                    articleUrl = "",
                    tag = "тег $it",
                    author = "Троцкий конь",
                    date = "18.05.2025",
                )
            }
        )
    }
}