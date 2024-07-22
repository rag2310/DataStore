package com.rago.datastore.viewmodel

data class DataStoreUIState(
    val name: String = "",
    val setName: () -> Unit = {},
    val onChangeName: (String) -> Unit = {},
    val inputName: String = ""
)