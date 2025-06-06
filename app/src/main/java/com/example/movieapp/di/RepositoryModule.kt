package com.example.movieapp.di

import com.example.movieapp.data.mapper.WatchMapper
import com.example.movieapp.data.repository.WatchRepository
import com.example.movieapp.data.repository.WatchRepositoryImpl

import org.koin.dsl.module

val repositoryModule= module {
    single{ WatchMapper()} //API response to domain model
    single<WatchRepository>{WatchRepositoryImpl(get(),get())} //injecting watchmode , watchapi
}

//loose coupling injecting interface