package com.mjdenham.movies.domain

class GetSimilarMoviesUseCase(
    private val moviesApi: MoviesApi,
    ) {

    /**
     * Returns movies similar to the given movie.
     * The list of similar movies includes the given movie at the start of the list.
     */
    suspend fun getSimilarMovies(movie: Movie): SimilarMovies {
        val similarMovies = moviesApi.getSimilarMovies(movie.id)
        return similarMovies.copy(
            movies = listOf(movie) + similarMovies.movies
        )
    }
}