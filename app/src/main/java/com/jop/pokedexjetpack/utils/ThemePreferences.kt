package com.jop.pokedexjetpack.utils

import android.content.Context
import android.content.SharedPreferences

class ThemePreferences(context: Context) {
    private val SHARED_NAME = "Pokedex"
    private val THEME = "theme"

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE)
    var isDarkTheme: Boolean
        get() = sharedPreferences.getBoolean(THEME, false)
        set(value) = sharedPreferences.edit().putBoolean(THEME, value).apply()
}