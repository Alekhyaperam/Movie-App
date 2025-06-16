package com.example.movieapp.ui.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.example.movieapp.data.model.WatchContent

@Composable
fun ContentGrid(
    contents: List<WatchContent>,
    onItemClick: (String) -> Unit,
    modifier: Modifier = Modifier
){
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
        items(contents){ content ->
            WatchContentCard(
                content = content,
                onClick = { onItemClick(content.id)}
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewContentGrid() {
    val sampleContents = listOf(
        WatchContent(id = "1", title = "Lio", posterUrl =  "", description = "", releaseDate = "2025-10-22", type = "movie", isMovie = true),
        WatchContent(id = "2", title = "Mufasa", posterUrl = "", description = "",releaseDate = "2025-10-22", type = "tv", isMovie = true)
    )
    ContentGrid(
        contents = sampleContents,
        onItemClick = {},
        modifier = Modifier
    )
}
