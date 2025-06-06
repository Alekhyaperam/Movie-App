package com.example.movieapp.data.model.response

data class WatchContentListResponse(
    val page:String,
    val results:List<WatchContentResponse>,
    val total_pages:Int,
    val total_result:Int
)

//represents api response