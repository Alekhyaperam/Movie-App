//can be use to map the response
//it can be "movie" or "tv_show"

package com.example.movieapp.data.model

data class WatchContent(
    val id: String,
    val title: String,
    val posterUrl: String,
    val description: String,
    val releaseDate: String?,
    val type: String ,
    val isMovie: Boolean
)

