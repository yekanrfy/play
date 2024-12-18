package com.aplikasi.musicplayer

import android.content.Context
import android.content.SharedPreferences

object SharedPrefManager {
    private const val PREF_NAME = "user_prefs"
    private const val KEY_USERNAME = "username"
    private const val KEY_PASSWORD = "password"
    private const val KEY_IS_LOGGED_IN = "is_logged_in"

    // Menyimpan data login pengguna
    fun saveUser(context: Context, username: String, password: String, isLoggedIn: Boolean) {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(KEY_USERNAME, username)
        editor.putString(KEY_PASSWORD, password)
        editor.putBoolean(KEY_IS_LOGGED_IN, isLoggedIn)
        editor.apply()
    }

    // Mendapatkan data pengguna (username dan password)
    fun getUser(context: Context): Pair<String, String> {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val username = sharedPreferences.getString(KEY_USERNAME, "") ?: ""
        val password = sharedPreferences.getString(KEY_PASSWORD, "") ?: ""
        return Pair(username, password)
    }

    // Mengecek apakah pengguna sudah login
    fun isUserLoggedIn(context: Context): Boolean {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false)
    }

    // Mendapatkan username yang sudah login
    fun getUsername(context: Context): String? {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getString(KEY_USERNAME, null)
    }

    // Menghapus data login pengguna (logout)
    fun logout(context: Context) {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }
}
