package com.example.movieapp.domain.usecase

import com.example.movieapp.data.model.WatchContent
import com.example.movieapp.data.repository.WatchRepository

class GetMoviesAndTvShowsUseCase(
    private val repository: WatchRepository
) {
    suspend operator fun invoke(): Pair<List<WatchContent>, List<WatchContent>> {
        return repository.getMoviesAndTvShows()
    }
}

//making api calls
//to get movies and tvshows
