//represents api response only main one represents here

package com.example.movieapp.data.model.response

data class WatchContentListResponse(
    val page:String,
    val results:List<WatchContentResponse>,
    val total_pages:Int,
    val total_result:Int
)

