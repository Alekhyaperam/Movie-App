//to build usecase,viewmodel
//it is di how app provides instances of usecases and viewmodels
//factory-creates a new instance whenever it is required

package com.example.movieapp.di

import com.example.movieapp.WatchApplication
import com.example.movieapp.domain.usecase.GetContentDetailsUseCase
import com.example.movieapp.domain.usecase.GetMoviesAndTvShowsUseCase
import com.example.movieapp.domain.usecase.GetRelatedContentUseCase
import com.example.movieapp.ui.details.DetailsViewModel
import com.example.movieapp.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { WatchApplication() }

    factory { GetContentDetailsUseCase(get()) }
    factory { GetMoviesAndTvShowsUseCase(get()) }
    factory { GetRelatedContentUseCase(get()) }

    viewModel { HomeViewModel(get()) }
    viewModel { DetailsViewModel(get<com.example.movieapp.domain.usecase.GetContentDetailsUseCase>(), get<com.example.movieapp.domain.usecase.GetRelatedContentUseCase>()) }
}