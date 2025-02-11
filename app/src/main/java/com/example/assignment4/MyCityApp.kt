/*
 * MyCityApp - An Android Jetpack Compose App
 *
 * This application provides recommendations for various categories
 * (e.g., coffee shops, parks, restaurants) within Woodburn, Oregon.
 * Users can navigate through different categories and view details
 * about each recommended place.
 *
 * Features:
 * - Uses Jetpack Compose for UI.
 * - Implements a navigation system with Jetpack Navigation.
 * - Follows the MVVM (Model-View-ViewModel) architecture.
 * - Utilizes Material Design 3 for UI.
 *
 * Developed as part of Assignment 4.
 *
 */


package com.example.assignment4

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import com.example.assignment4.data.DataSource
import com.example.assignment4.ui.CategoryListScreen
import com.example.assignment4.ui.RecommendationDetailScreen
import com.example.assignment4.ui.RecommendationListScreen
import com.example.assignment4.ui.theme.CityViewModel

enum class MyCityScreen(@StringRes val title: Int) {
    CategoryList(title = R.string.my_city),
    RecommendationList(title = R.string.choose_recommendation),
    RecommendationDetail(title = R.string.recommendation_summary)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyCityAppBar(
    title: String,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(title) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}


@Composable
fun MyCityApp(
    viewModel: CityViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()

    // Get the current screen from the route
    // SOURCE: https://stackoverflow.com/questions/66493551/jetpack-compose-navigation-get-route-of-current-destination-as-string
    val currentScreen = MyCityScreen.valueOf(
        backStackEntry?.destination?.route ?: MyCityScreen.CategoryList.name
    )

    val uiState by viewModel.uiState.collectAsState()

    // Get the app bar title
    val appBarTitle = when (currentScreen) {
        MyCityScreen.CategoryList -> stringResource(R.string.my_city)
        MyCityScreen.RecommendationList -> uiState.selectedCategoryId
        MyCityScreen.RecommendationDetail -> uiState.selectedPlaceId
            ?: stringResource(R.string.recommendation_summary)
    }

    Scaffold(
        topBar = {
            MyCityAppBar(
                title = appBarTitle,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = MyCityScreen.CategoryList.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = MyCityScreen.CategoryList.name) {
                CategoryListScreen(
                    categories = DataSource.categories,
                    onNextButtonClicked = {
                        viewModel.selectCategory(it)
                        navController.navigate(MyCityScreen.RecommendationList.name)
                    }
                )
            }

            composable(route = MyCityScreen.RecommendationList.name) {
                RecommendationListScreen(
                    categories = DataSource.places,
                    selectedCategory = uiState.selectedCategoryId,
                    onNextButtonClicked = {
                        viewModel.selectPlace(it)
                        navController.navigate(MyCityScreen.RecommendationDetail.name)
                    },
                    onBackButtonClicked = { navController.popBackStack() }
                )
            }

            composable(route = MyCityScreen.RecommendationDetail.name) {
                uiState.selectedPlaceId?.let { selectedPlace ->
                    RecommendationDetailScreen(
                        places = DataSource.placesDetail,
                        selectedPlace = selectedPlace
                    )
                }
            }
        }
    }
}

