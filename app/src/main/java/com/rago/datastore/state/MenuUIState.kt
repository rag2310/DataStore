package com.rago.datastore.state

data class MenuUIState(
    val setOnNavDataStoreScreen: (() -> Unit) -> Unit = {},
    val onNavDataStoreScreen: () -> Unit = {}
)