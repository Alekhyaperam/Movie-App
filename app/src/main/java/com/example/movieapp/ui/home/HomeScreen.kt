package com.example.movieapp.ui.home

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
//import androidx.compose.material3.icons.Icons
//import androidx.compose.material3.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.movieapp.ui.home.components.*
import androidx.compose.material3.TextFieldDefaults

import androidx.compose.material3.MaterialTheme



import org.koin.androidx.compose.koinViewModel
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel(),
    onNavigateToDetails: (String, String) -> Unit,
) {
    val state = viewModel.state.collectAsState()
    val contentType = viewModel.contentType.collectAsState()
    val searchQuery = viewModel.searchQuery.collectAsState()

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp) // Horizontal padding for consistent layout
                .padding(top = 8.dp)
                .windowInsetsPadding(WindowInsets.statusBars)
        ) {
            // Search Bar
            TextField(
                value = searchQuery.value,
                onValueChange = { viewModel.setSearchQuery(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                placeholder = {
                    Text("Search ${contentType.value.name.lowercase().replace('_', ' ')}")
                },
                leadingIcon = {
                    Icon(Icons.Default.Search, contentDescription = "Search icon")
                },
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                    focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            )

            //  Spacer between search and buttons
            Spacer(modifier = Modifier.height(4.dp))

            //  Toggle Buttons: Movies / TV Shows
            HomeTopBar(
                selectedType = contentType.value,
                onTypeSelected = {
                    viewModel.setContentType(it)
                    viewModel.setSearchQuery("")
                }
            )

            // Spacer between buttons and content
            Spacer(modifier = Modifier.height(2.dp))

            // Content Display
            AnimatedContent(targetState = state.value) { currentState ->
                when (currentState) {
                    is HomeState.Loading -> ShimmerContent()
                    is HomeState.Success -> {
                        val contents = when (contentType.value) {
                            ContentType.MOVIES -> currentState.movies
                            ContentType.TV_SHOWS -> currentState.tvShows
                        }

                        val filteredContents = if (searchQuery.value.isBlank()) {
                            contents
                        } else {
                            contents.filter {
                                it.title.contains(searchQuery.value, ignoreCase = true)
                            }
                        }

                        ContentGrid(
                            contents = filteredContents,
                            onItemClick = { id ->
                                val type = when (contentType.value) {
                                    ContentType.MOVIES -> "movie"
                                    ContentType.TV_SHOWS -> "tv"
                                }
                                onNavigateToDetails(id, type)
                            },
                        )
                    }

                    is HomeState.Error -> {
                        ErrorContent(
                            message = currentState.message,
                            onRetry = viewModel::loadContent,
                        )
                    }
                }
            }
        }
    }
}
