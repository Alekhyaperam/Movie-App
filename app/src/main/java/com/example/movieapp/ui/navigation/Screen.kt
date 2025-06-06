package com.example.movieapp.ui.navigation

sealed class Screen(val route:String) {
    data object  Home:Screen("home")
    data object Details:Screen("details/{contentId}/{contentType}"){
        fun createRoute(contentId:String, contentType: String)= "details/$contentId/$contentType"
    }
}

//manage navigation routes
//sealed class means all possiblesubclasses is defined in same file

