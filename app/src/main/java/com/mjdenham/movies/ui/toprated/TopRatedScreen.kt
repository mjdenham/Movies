package com.mjdenham.movies.ui.toprated

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.mjdenham.movies.domain.TopRatedSummaryDto
import com.mjdenham.movies.ui.theme.MoviesTheme

@Composable
fun TopRatedScreen(modifier: Modifier = Modifier, viewModel: TopRatedViewModel = viewModel()) {
    val movies by viewModel.topRatedState.collectAsState()
    TopRated(movies, modifier)
}

@Composable
private fun TopRated(movies: List<TopRatedSummaryDto.MovieDto>, modifier: Modifier) {
    LazyColumn(modifier = modifier) {
        items(movies) {
            TopRatedMovie(it, modifier)
        }
    }
}

@Composable
private fun TopRatedMovie(it: TopRatedSummaryDto.MovieDto, modifier: Modifier) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        AsyncImage(
            model = it.smallPosterPath,
            contentDescription = "Movie poster",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(15.dp)
                .size(80.dp)
                .clip(CircleShape)
        )
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Text(text = it.name, style = MaterialTheme.typography.titleMedium)
            Text(text = "Score: ${it.voteAverage}", style = MaterialTheme.typography.bodyMedium)
        }
    }
    HorizontalDivider()

}


@Preview(showBackground = true)
@Composable
fun TopRatedPreview() {
    MoviesTheme {
        TopRated(
            listOf(
                TopRatedSummaryDto.MovieDto(
                    1,
                    "The Hulk",
                    8,
                    "https://image.tmdb.org/t/p/w92/gpkM8VeiYyQuEg9qkAoNplktwe4.jpg"
                ),
                TopRatedSummaryDto.MovieDto(
                    1,
                    "Mr Bean's Holiday",
                    9,
                    "https://image.tmdb.org/t/p/w92/gpkM8VeiYyQuEg9qkAoNplktwe4.jpg"
                ),
                TopRatedSummaryDto.MovieDto(
                    1,
                    "The Invisible Man",
                    4,
                    "https://image.tmdb.org/t/p/w92/gpkM8VeiYyQuEg9qkAoNplktwe4.jpg"
                ),

            ),
            Modifier
        )
    }
}