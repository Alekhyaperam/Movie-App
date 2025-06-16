package com.example.movieapp.data.api

import com.example.movieapp.data.model.response.WatchContentListResponse
import com.example.movieapp.data.model.response.WatchContentResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WatchmodeApi {
    @GET("movie/popular")
    suspend fun getMovies(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ):WatchContentListResponse

    @GET("tv/popular")
    suspend fun getTvShows(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ):WatchContentListResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("language") language: String = "en-US"
    ):WatchContentResponse

    @GET("tv/{tv_id}")
    suspend fun getTvShowDetails(
        @Path("tv_id") tvId: String,
        @Query("language") language: String = "en-US"
    ):WatchContentResponse

    @GET("movie/{movie_id}/similar")
    suspend fun getSimilarMovies(
        @Path("movie_id") movieId: Int,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): WatchContentListResponse

    @GET("tv/{tv_id}/similar")
    suspend fun getSimilarTvShows(
        @Path("tv_id") tvId: String,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): WatchContentListResponse

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
    }
}

