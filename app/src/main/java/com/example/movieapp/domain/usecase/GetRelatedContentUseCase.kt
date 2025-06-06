package com.example.movieapp.domain.usecase

import com.example.movieapp.data.model.WatchContent
import com.example.movieapp.data.repository.WatchRepository

class GetRelatedContentUseCase(private val repository: WatchRepository) {
    suspend operator fun invoke(contentId: String, isMovie: Boolean): List<WatchContent> {
        return repository.getRelatedContent(contentId, isMovie)
    }
}