package com.example.nearbyplaces.helper

import android.content.Context

object AuthManager {

    fun isAuthenticated(context: Context): Boolean {
        val authToken = getAuthenticationToken(context)
        return authToken.isNotEmpty()
    }

    fun getAuthenticationToken(context: Context): String {
        val sharedPreferences =
            context.getSharedPreferences(Constants.SHARED_PREF_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getString(Constants.TOKEN_KEY, "") ?: ""
    }

    fun saveAuthenticationToken(context: Context, token: String) {
        val sharedPreferences =
            context.getSharedPreferences(Constants.SHARED_PREF_NAME, Context.MODE_PRIVATE)
        sharedPreferences.edit().putString(Constants.TOKEN_KEY, token).apply()
    }

    fun clearAuthenticationToken(context: Context) {
        val sharedPreferences = context.getSharedPreferences(Constants.SHARED_PREF_NAME, Context.MODE_PRIVATE)
        sharedPreferences.edit().remove(Constants.TOKEN_KEY).apply()
    }

}
