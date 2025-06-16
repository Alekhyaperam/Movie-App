package com.example.movieapp.domain.usecase

import com.example.movieapp.data.model.WatchContent
import com.example.movieapp.data.repository.WatchRepository

class GetContentDetailsUseCase(
    private val repository: WatchRepository) { //to fetch the data
    suspend operator fun invoke(contentId:String,isMovie:Boolean): WatchContent {
        return repository.getContentDetails(contentId,isMovie) //actuall data fetching from the  repository
    }

}

//it contains details from id
