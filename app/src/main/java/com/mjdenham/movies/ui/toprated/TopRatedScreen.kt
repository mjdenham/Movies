package com.mjdenham.movies.ui.toprated

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.mjdenham.movies.R
import com.mjdenham.movies.domain.TopRatedSummaryDto
import com.mjdenham.movies.ui.theme.MoviesTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun TopRatedScreen(modifier: Modifier = Modifier, viewModel: TopRatedViewModel = koinViewModel()) {
    val lazyPagingMovies = viewModel.getPagedMovies().collectAsLazyPagingItems()

    LazyColumn {
        if (lazyPagingMovies.loadState.refresh == LoadState.Loading) {
            item {
                Text(
                    text = stringResource(R.string.loading_movies),
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.CenterHorizontally)
                )
            }
        }

        items(count = lazyPagingMovies.itemCount) { index ->
            lazyPagingMovies[index]?.let { movieItem ->
                TopRatedMovie(it = movieItem, modifier = modifier)
            }
        }

        if (lazyPagingMovies.loadState.append == LoadState.Loading) {
            item {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.CenterHorizontally)
                )
            }
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
            modifier = modifier
                .padding(15.dp)
                .size(80.dp)
                .clip(CircleShape)
        )
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Text(text = it.name, style = MaterialTheme.typography.titleMedium)
            Text(text = stringResource(id = R.string.vote_percent, it.voteAveragePc), style = MaterialTheme.typography.bodyMedium)
        }
    }
    HorizontalDivider()
}


@Preview(showBackground = true)
@Composable
fun TopRatedPreview() {
    val testMovie = TopRatedSummaryDto.MovieDto(
        1,
        "The Hulk",
        8,
        "https://image.tmdb.org/t/p/w92/gpkM8VeiYyQuEg9qkAoNplktwe4.jpg"
    )

    MoviesTheme {
        TopRatedMovie(it = testMovie, modifier = Modifier)
    }
}