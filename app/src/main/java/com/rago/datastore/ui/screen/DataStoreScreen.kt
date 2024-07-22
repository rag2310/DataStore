package com.rago.datastore.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rago.datastore.viewmodel.DataStoreUIState

@Composable
fun DataStoreScreen(dataStoreUIState: DataStoreUIState) {
    DataStoreScreenContent(dataStoreUIState)
}

@Composable
private fun DataStoreScreenContent(dataStoreUIState: DataStoreUIState) {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = dataStoreUIState.name.ifEmpty { "Not Name" })
        Spacer(modifier = Modifier.height(10.dp))
        TextField(value = dataStoreUIState.inputName, onValueChange = dataStoreUIState.onChangeName)
        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = dataStoreUIState.setName) {
            Text(text = "Save Name")
        }
    }
}
