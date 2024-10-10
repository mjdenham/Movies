package com.mjdenham.movies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.mjdenham.movies.domain.Movie
import com.mjdenham.movies.ui.CustomNavType
import com.mjdenham.movies.ui.similar.SimilarScreen
import com.mjdenham.movies.ui.theme.MoviesTheme
import com.mjdenham.movies.ui.toprated.TopRatedScreen
import kotlinx.serialization.Serializable
import kotlin.reflect.typeOf

/**
 * Values that represent the screens in the app
 */
@Serializable
object TopRatedRoute

@Serializable
data class SimilarMoviesRoute(val movie: Movie)

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            MoviesTheme {
                var screenTitleId by remember { mutableStateOf(R.string.app_name) }

                Scaffold(
                    topBar = {
                        MoviesTopAppBar(screenTitleId)
                    },
                    modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = TopRatedRoute,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        composable<TopRatedRoute> {
                            screenTitleId = R.string.app_name
                            TopRatedScreen({ selectedMovie ->
                                navController.navigate(SimilarMoviesRoute(selectedMovie))
                            })
                        }
                        composable<SimilarMoviesRoute>(
                            typeMap = mapOf(typeOf<Movie>() to CustomNavType.movieType),
                            enterTransition = {
                                return@composable slideIntoContainer(
                                    AnimatedContentTransitionScope.SlideDirection.Start, tween(700)
                                )
                            },
                            popExitTransition = {
                                return@composable slideOutOfContainer(
                                    AnimatedContentTransitionScope.SlideDirection.End, tween(700)
                                )
                            },
                        ) { backStackEntry ->
                            screenTitleId = R.string.similar_movies
                            val similarMoviesRoute: SimilarMoviesRoute = backStackEntry.toRoute()
                            val movie = similarMoviesRoute.movie
                            SimilarScreen(movie)
                        }
                    }
                }
            }
        }
    }

    @Composable
    @OptIn(ExperimentalMaterial3Api::class)
    private fun MoviesTopAppBar(screenTitleId: Int) {
        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary,
            ),
            title = {
                Text(stringResource(id = screenTitleId))
            }
        )
    }
}
