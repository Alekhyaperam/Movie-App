package com.example.movieapp.data.repository

import com.example.movieapp.data.api.WatchmodeApi
import com.example.movieapp.data.mapper.WatchMapper
import com.example.movieapp.data.model.WatchContent
import io.reactivex.rxjava3.core.Single

class WatchRepositoryImpl(
    private val api: WatchmodeApi,
    private val mapper: WatchMapper,
) : WatchRepository {
    override fun getMoviesAndTvShows(): Single<Pair<List<WatchContent>, List<WatchContent>>> {
        return Single.zip(
            api.getMovies()
                .map { mapper.mapResponseListToWatchContent(it.results) }, //extract list of movies data
            api.getTvShows()
                .map { mapper.mapResponseListToWatchContent(it.results) }
        ) { movies, tvShows ->
            Pair(movies, tvShows)
        }
    }

    override suspend fun getContentDetails(contentId: String, isMovie: Boolean): WatchContent {
        return if (isMovie) {
            val movieId = contentId.toIntOrNull() ?: throw IllegalArgumentException("Invalid movie ID: $contentId")
            mapper.mapResponseToWatchContent(api.getMovieDetails(movieId))
        } else {
            mapper.mapResponseToWatchContent(api.getTvShowDetails(contentId))
        }
    }

    override suspend fun getRelatedContent(contentId: String, isMovie: Boolean): List<WatchContent> {
        return if (isMovie) {
            val movieId = contentId.toIntOrNull() ?: throw IllegalArgumentException("Invalid movie ID: $contentId")
            mapper.mapResponseListToWatchContent(api.getSimilarMovies(movieId).results)
        } else {
            mapper.mapResponseListToWatchContent(api.getSimilarTvShows(contentId).results)
        }
    }

}

//responsible for feteching data from watchmodeapi
