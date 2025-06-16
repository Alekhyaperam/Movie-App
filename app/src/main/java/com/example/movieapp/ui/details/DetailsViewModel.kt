//To load the content

package com.example.movieapp.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.model.WatchContent
import com.example.movieapp.domain.usecase.GetContentDetailsUseCase
import com.example.movieapp.domain.usecase.GetRelatedContentUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val getContentDetailsUseCase: GetContentDetailsUseCase,
    private val getRelatedContentUseCase: GetRelatedContentUseCase
):ViewModel() {
    private val _state = MutableStateFlow<DetailsState>(DetailsState.Loading)
    val state: StateFlow<DetailsState> = _state.asStateFlow()

    private val _related = MutableStateFlow<List<WatchContent>>(emptyList())
    val related: StateFlow<List<WatchContent>> = _related.asStateFlow()

    fun loadContent(contentId:String, isMovie:Boolean){
        _state.value=DetailsState.Loading
        _related.value = emptyList()
        viewModelScope.launch {
            try {
                val content = getContentDetailsUseCase(contentId,isMovie)
                _state.value= DetailsState.Success(content)
                // Fetch related content
                val relatedContent = getRelatedContentUseCase(contentId, isMovie)
                _related.value = relatedContent
            }catch (e:Exception){
                _state.value=DetailsState.Error("Failed to load content")
                _related.value = emptyList()
            }
        }
    }
}

