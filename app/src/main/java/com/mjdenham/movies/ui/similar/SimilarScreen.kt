package com.mjdenham.movies.ui.similar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mjdenham.movies.domain.MovieDto
import com.mjdenham.movies.ui.theme.MoviesTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun SimilarScreen(movie: MovieDto, modifier: Modifier = Modifier, viewModel: SimilarViewModel = koinViewModel()) {
    val savedMovieId by rememberSaveable { mutableStateOf(movie.id) }
    LaunchedEffect(savedMovieId) {
        viewModel.loadSimilarMovies(movie)
    }

    val movies by viewModel.similarState.collectAsState()
    Column(
        modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        SimilarMovies(movies = movies, modifier = modifier)
    }
}

@Composable
private fun SimilarMovies(movies: List<MovieDto>, modifier: Modifier) {
    BoxWithConstraints(
        modifier = Modifier.fillMaxSize()
    ) {
        val boxWithConstraintsScope = this
        LazyRow(modifier = modifier.fillMaxWidth()) {
            items(movies) {
                SimilarMovie(
                    it,
                    boxWithConstraintsScope.maxWidth * 0.9f, boxWithConstraintsScope.maxHeight,
                    modifier
                )
            }
        }
    }
}
@Composable
private fun SimilarMovie(movie: MovieDto, cardWidth: Dp, cardHeight: Dp, modifier: Modifier) {
    Card(
        modifier = modifier
            .width(cardWidth)
            .height(cardHeight)
            .padding(15.dp)
    ) {
        Column(
            modifier = modifier.verticalScroll(rememberScrollState())
        ) {
            movie.largePosterPath?.let {
                AsyncImage(
                    model = movie.largePosterPath,
                    contentDescription = "Movie poster",
                    contentScale = ContentScale.FillWidth,
                    modifier = modifier
                        .align(Alignment.CenterHorizontally)
                        .fillMaxWidth()
                )
            }
            Text(
                text = movie.name,
                style = MaterialTheme.typography.titleMedium,
                modifier = modifier.padding(20.dp)
            )
            Text(
                text = movie.overview,
                maxLines = 5,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodyMedium,
                modifier = modifier.padding(20.dp, 0.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SimilarMoviePreview() {
    MoviesTheme {
        SimilarMovie(
            MovieDto(
                1,
                "The Hulk",
                LONG_OVERVIEW,
                8,
                "https://image.tmdb.org/t/p/w92/gpkM8VeiYyQuEg9qkAoNplktwe4.jpg",
                "https://image.tmdb.org/t/p/original/gpkM8VeiYyQuEg9qkAoNplktwe4.jpg"
            ),
            200.dp, 400.dp,
            Modifier
        )
    }
}
@Preview(showBackground = true)
@Composable
fun SimilarMoviesPreview() {
    MoviesTheme {
        SimilarMovies(
            listOf(
                MovieDto(
                    1,
                    "The Hulk",
                    LONG_OVERVIEW,
                    8,
                    "https://image.tmdb.org/t/p/w92/gpkM8VeiYyQuEg9qkAoNplktwe4.jpg",
                    "https://image.tmdb.org/t/p/original/gpkM8VeiYyQuEg9qkAoNplktwe4.jpg"
                ),
                MovieDto(
                    1,
                    "Mr Bean's Holiday",
                    "Overview of Mr Bean's Holiday",
                    9,
                    "https://image.tmdb.org/t/p/w92/gpkM8VeiYyQuEg9qkAoNplktwe4.jpg",
                    "https://image.tmdb.org/t/p/original/gpkM8VeiYyQuEg9qkAoNplktwe4.jpg"
                ),
                MovieDto(
                    1,
                    "The Invisible Man",
                    "Overview of The Invisible Man",
                    4,
                    "https://image.tmdb.org/t/p/w92/gpkM8VeiYyQuEg9qkAoNplktwe4.jpg",
                    "https://image.tmdb.org/t/p/original/gpkM8VeiYyQuEg9qkAoNplktwe4.jpg"
                ),

                ),
            Modifier
        )
    }
}

private const val LONG_OVERVIEW = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."