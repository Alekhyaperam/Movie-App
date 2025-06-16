package com.example.movieapp.data.repository

import com.example.movieapp.data.model.WatchContent

interface WatchRepository {
    suspend fun getMoviesAndTvShows(): Pair<List<WatchContent>, List<WatchContent>>
    suspend fun getContentDetails(
        contentId: String,
        isMovie: Boolean
    ): WatchContent

    suspend fun getRelatedContent(
        contentId: String,
        isMovie: Boolean
    ): List<WatchContent>
}

//defines how our app retrives data  from apis
//coroutines suspend functions are used to make the code asychronus

