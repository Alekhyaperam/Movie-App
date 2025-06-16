//we will get the status msg

package com.example.movieapp.ui.details

sealed class DetailsState{
    data object Loading: DetailsState()
    data class Success(val content: com.example.movieapp.data.model.WatchContent):DetailsState()
    data class Error(val message: String):DetailsState()
}

