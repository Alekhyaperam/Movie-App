//represent a limited number of subclasses. sealed
//enum means it shows correctly which type is movie or tv

package com.example.movieapp.ui.home

import com.example.movieapp.data.model.WatchContent

sealed class HomeState {
    object Loading : HomeState()
    data class Success(
        val movies: List<WatchContent>,
        val tvShows: List<WatchContent>
    ) : HomeState()
    data class Error(val message: String) : HomeState()
}
enum class ContentType {
    MOVIES, TV_SHOWS
}

