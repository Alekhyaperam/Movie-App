package com.example.movieapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.domain.usecase.GetMoviesAndTvShowsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getMoviesAndTvShowsUseCase: GetMoviesAndTvShowsUseCase
) : ViewModel() {
    private val _state = MutableStateFlow<HomeState>(HomeState.Loading)
    val state: StateFlow<HomeState> = _state.asStateFlow()

    private val _contentType = MutableStateFlow(ContentType.MOVIES)
    val contentType: StateFlow<ContentType> = _contentType.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    init {
        loadContent()
    }

    fun setContentType(type: ContentType) {
        _contentType.value = type
        setSearchQuery("") // Clear search when content type changes
    }

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun loadContent() {
        _state.value = HomeState.Loading
        viewModelScope.launch {
            try {
                val (movies, tvShows) = getMoviesAndTvShowsUseCase()
                _state.value = HomeState.Success(
                    movies = movies,
                    tvShows = tvShows
                )
            } catch (e: Exception) {
                _state.value = HomeState.Error(e.message ?: "Unknown error occurred")
            }
        }
    }
}
