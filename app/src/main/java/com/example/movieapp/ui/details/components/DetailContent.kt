package com.example.movieapp.ui.details.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.movieapp.data.model.WatchContent
import com.example.movieapp.ui.home.components.ContentGrid
import com.example.movieapp.ui.home.components.WatchContentCard

@Composable
fun DetailContent(
    content: WatchContent,
    related: List<WatchContent> = emptyList(),
    onRelatedItemClick: (id: String, isMovie: Boolean) -> Unit = { _, _ -> },
    modifier: Modifier = Modifier
){
    LazyColumn(
        modifier = modifier.fillMaxSize().padding(16.dp)
    ) {
        item {
            DetailHeader(content = content)
            Spacer(modifier = Modifier.padding(16.dp))
            Column (
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 16.dp)
            ) {
                Text(
                    text = "Overview",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = content.description,
                    style = MaterialTheme.typography.bodyLarge,
                    lineHeight = MaterialTheme.typography.bodyLarge.lineHeight * 1.5
                )
            }
            Spacer(modifier = Modifier.padding(16.dp))
        }
        if (related.isNotEmpty()) {
            item {
                Text(
                    text = "Related",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(bottom = 8.dp, top = 8.dp)
                )
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(related) { item ->
                        WatchContentCard(
                            content = item,
                            onClick = { onRelatedItemClick(item.id, item.isMovie) },
                            modifier = Modifier
                                .width(180.dp)
                        )
                    }
                }
            }
        }
    }
}
