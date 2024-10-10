package com.mjdenham.movies.ui.toprated

import androidx.paging.PagingSource
import com.mjdenham.movies.domain.Movie
import com.mjdenham.movies.domain.MoviesApi
import com.mjdenham.movies.domain.GetTopRatedMoviesUseCase
import com.mjdenham.movies.domain.SimilarMovies
import com.mjdenham.movies.domain.TopRatedMovies
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class TopRatedPagingSourceTest {

    private val testMovie = Movie(1, "Spiderman", "Brief overview of the movie", 9, "smallPosterPath", "posterPath")

    private val moviesApiStub = object : MoviesApi {
        override suspend fun getTopRatedMovies(page: Int): TopRatedMovies {
            return TopRatedMovies(
                1,
                1,
                listOf(testMovie)
            )
        }

        override suspend fun getSimilarMovies(movieId: Int): SimilarMovies {
            throw NotImplementedError()
        }
    }

    private val loadParamsRefresh = PagingSource.LoadParams.Refresh(
        key = 0,
        loadSize = 1,
        placeholdersEnabled = false
    )

    private val loadParamsAppend = PagingSource.LoadParams.Append(
        key = 1,
        loadSize = 1,
        placeholdersEnabled = false
    )

    private lateinit var pagingSource: TopRatedPagingSource

    @Before
    fun setup() {
        pagingSource = TopRatedPagingSource(GetTopRatedMoviesUseCase(moviesApiStub))
    }

    @Test
    fun refreshShouldLoadData() = runTest {
        val loadResult: PagingSource.LoadResult<Int, Movie> = pagingSource.load(loadParamsRefresh)
        val pageResult = loadResult as PagingSource.LoadResult.Page<Int, Movie>
        assertEquals(1, pageResult.nextKey)
        assertEquals(1, pageResult.data.size)
        assertEquals(testMovie, pageResult.data[0])
    }

    @Test
    fun appendShouldStopAtEnd() = runTest {
        val loadResult: PagingSource.LoadResult<Int, Movie> = pagingSource.load(loadParamsAppend)
        val pageResult = loadResult as PagingSource.LoadResult.Page<Int, Movie>
        assertEquals(null, pageResult.nextKey)
        assertEquals(1, pageResult.data.size)
        assertEquals(testMovie, pageResult.data[0])
    }
}