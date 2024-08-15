package com.mjdenham.movies.domain

class MoviesUseCase(private val moviesApi: MoviesApi) {

    suspend fun getTopRatedMovies(page: Int): TopRatedMoviesDto = moviesApi.getTopRatedMovies(page)

    /**
     * Returns movies similar to the given movie.
     * The list of similar movies includes the given movie at the start of the list.
     */
    suspend fun getSimilarMovies(movie: MovieDto): SimilarMoviesDto {
        val similarMovies = moviesApi.getSimilarMovies(movie.id)
        return similarMovies.copy(
            movies = listOf(movie) + similarMovies.movies
        )
    }
}