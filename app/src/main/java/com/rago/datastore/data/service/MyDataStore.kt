package com.rago.datastore.data.service

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MyDataStore @Inject constructor(private val context: Context) {
    private val name = stringPreferencesKey("name_key")

    val Context.datastore: DataStore<Preferences> by preferencesDataStore("datastore")


    fun getName(): Flow<String> = context.datastore.data.map { preferences ->
        preferences[name] ?: ""
    }

    suspend fun setName(name: String) {
        context.datastore.edit { settings ->
            settings[this.name] = name
        }
    }

}