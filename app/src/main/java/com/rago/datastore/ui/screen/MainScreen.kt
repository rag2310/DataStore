package com.rago.datastore.ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rago.datastore.state.MenuUIState
import com.rago.datastore.viewmodel.DataStoreUIState
import com.rago.datastore.viewmodel.DataStoreViewModel
import com.rago.datastore.viewmodel.MenuViewModel


@Composable
fun MainScreen() {
    val navHostController = rememberNavController()
    MainScreenContent(navHostController)
}

@Composable
private fun MainScreenContent(navHostController: NavHostController) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        NavHost(
            modifier = Modifier.padding(innerPadding),
            navController = navHostController,
            startDestination = "menu_screen"
        ) {
            composable("menu_screen") {
                val menuViewModel: MenuViewModel = hiltViewModel()
                val menuUIState: MenuUIState by menuViewModel.uiState.collectAsState()

                LaunchedEffect(key1 = Unit) {
                    menuUIState.setOnNavDataStoreScreen {
                        navHostController.navigate("datastore_screen")
                    }
                }
                MenuScreen(menuUIState)
            }

            composable("datastore_screen"){

                val dataStoreViewModel: DataStoreViewModel = hiltViewModel()
                val dataStoreUIState: DataStoreUIState by dataStoreViewModel.uiState.collectAsState()
                DataStoreScreen(dataStoreUIState)
            }
        }
    }
}