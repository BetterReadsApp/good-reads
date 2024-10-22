package uba.fi.goodreads.data.auth.repositories

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

private val Context.dataStore by preferencesDataStore(name = "user_preferences")

class UserSessionLocalDataSource @Inject constructor(
    private val context: Context
) {

    companion object {
        private val ACCESS_TOKEN_KEY = stringPreferencesKey("access_token")
    }

    private var cachedAccessToken: String = ""

    val isUserLogged = context.dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            val accessToken = preferences[ACCESS_TOKEN_KEY] ?: ""
            if (cachedAccessToken.isNotBlank()) {
                println("isUserLogged bool: true")
                return@map true
            }
            println("isUserLogged bool: ${accessToken.isNotBlank()}, token was: $accessToken")
            accessToken.isNotBlank()
        }

    suspend fun saveAccessToken(accessToken: String) {
        context.dataStore.edit { preferences ->
            println("saving access token: $accessToken")
            cachedAccessToken = accessToken
            preferences[ACCESS_TOKEN_KEY] = accessToken
        }
    }

    // Clear access token from preferences
    suspend fun clearAccessToken() {
        cachedAccessToken = ""
        context.dataStore.edit { preferences ->
            preferences.remove(ACCESS_TOKEN_KEY)
        }
        println("deleted access token")
    }

    // Get the access token
    suspend fun getAccessToken(): String {
        if (cachedAccessToken.isNotBlank()) {
            println("From cache")
            return cachedAccessToken
        }

        val accessTokenFromDataStore = context.dataStore.data.first()[ACCESS_TOKEN_KEY] ?: ""
        cachedAccessToken = accessTokenFromDataStore
        return cachedAccessToken
    }
}