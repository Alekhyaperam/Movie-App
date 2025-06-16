//here we will actually call the api and return data(watchmodeapi.watchmapper)


package com.example.movieapp.data.repository

import com.example.movieapp.data.api.WatchmodeApi
import com.example.movieapp.data.mapper.WatchMapper
import com.example.movieapp.data.model.WatchContent

class WatchRepositoryImpl(
    private val api: WatchmodeApi,
    private val mapper: WatchMapper,
) : WatchRepository {
    override suspend fun getMoviesAndTvShows(): Pair<List<WatchContent>, List<WatchContent>> {
        val moviesResponse = api.getMovies()
        val tvShowsResponse = api.getTvShows()
        val movies = mapper.mapResponseListToWatchContent(moviesResponse.results)
        val tvShows = mapper.mapResponseListToWatchContent(tvShowsResponse.results)
        return Pair(movies, tvShows)
    }

    override suspend fun getContentDetails(contentId: String, isMovie: Boolean): WatchContent {
        return if (isMovie) {
            val movieId = contentId.toIntOrNull() ?: throw IllegalArgumentException("Invalid movie: $contentId")
            mapper.mapResponseToWatchContent(api.getMovieDetails(movieId))
        } else {
            mapper.mapResponseToWatchContent(api.getTvShowDetails(contentId))
        }
    }

    override suspend fun getRelatedContent(contentId: String, isMovie: Boolean): List<WatchContent> {
        return if (isMovie) {
            val movieId = contentId.toIntOrNull() ?: throw IllegalArgumentException("Invalid movie: $contentId")
            mapper.mapResponseListToWatchContent(api.getSimilarMovies(movieId).results)
        } else {
            mapper.mapResponseListToWatchContent(api.getSimilarTvShows(contentId).results)
        }
    }
}

