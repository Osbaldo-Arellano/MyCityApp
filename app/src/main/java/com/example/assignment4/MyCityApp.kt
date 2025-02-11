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
import android.util.Log
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.LaunchedEffect
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
    currentScreen: MyCityScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(currentScreen.title)) },
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
fun MyCityApp (
    viewModel: CityViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = MyCityScreen.valueOf(
        backStackEntry?.destination?.route ?: MyCityScreen.CategoryList.name
    )

    val uiState by viewModel.uiState.collectAsState()

    // Logs whenever uiState changes
    // Source: https://stackoverflow.com/questions/78569757/how-launchedeffect-works
    LaunchedEffect(uiState) {
        Log.d("MyCityApp", "Updated UI State: $uiState")
    }

    Scaffold (
        topBar = {
            MyCityAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = MyCityScreen.CategoryList.name,
            modifier = Modifier.padding(innerPadding)
        ){
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
                uiState.selectedPlaceId?.let { it1 ->
                    RecommendationDetailScreen(
                        places = DataSource.placesDetail,
                        selectedPlace = it1,
                        onNextButtonClicked = { viewModel.selectPlace(it) },
                        onBackButtonClicked = { navController.popBackStack() }
                    )
                }
            }

        }

    }
}