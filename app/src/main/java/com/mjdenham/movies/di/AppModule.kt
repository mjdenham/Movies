package com.mjdenham.movies.di

import com.mjdenham.movies.data.network.TmdbApiClient
import com.mjdenham.movies.domain.GetSimilarMoviesUseCase
import com.mjdenham.movies.domain.MoviesApi
import com.mjdenham.movies.domain.GetTopRatedMoviesUseCase
import com.mjdenham.movies.ui.similar.SimilarViewModel
import com.mjdenham.movies.ui.toprated.TopRatedPagingSource
import com.mjdenham.movies.ui.toprated.TopRatedViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<MoviesApi> {TmdbApiClient()}
    single<GetTopRatedMoviesUseCase> { GetTopRatedMoviesUseCase(get()) }
    single<GetSimilarMoviesUseCase> { GetSimilarMoviesUseCase(get()) }
    factory<TopRatedPagingSource> { TopRatedPagingSource(get())  }
    viewModel<TopRatedViewModel> { TopRatedViewModel(get())}
    viewModel<SimilarViewModel> { SimilarViewModel(get())}
}