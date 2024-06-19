package com.rago.datastore.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rago.datastore.state.MenuUIState

@Composable
fun MenuScreen(menuUIState: MenuUIState) {
    MenuScreenContent(menuUIState)
}

@Composable
private fun MenuScreenContent(menuUIState: MenuUIState) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Tecnologias",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(vertical = 16.dp)
        )
        LazyColumn(
            Modifier
                .fillMaxSize()
                .weight(1f, true)
        ) {
            item {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = menuUIState.onNavDataStoreScreen
                ) {
                    Text(text = "DataStore")
                }
            }
        }
    }
}
