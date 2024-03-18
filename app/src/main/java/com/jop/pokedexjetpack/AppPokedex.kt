package com.jop.pokedexjetpack

import android.app.Application
import android.content.Context
import com.jop.pokedexjetpack.utils.ThemePreferences
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AppPokedex : Application(){
    companion object {
        lateinit var appContext: Context
        lateinit var sharedPreferences: ThemePreferences
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        sharedPreferences = ThemePreferences(applicationContext)
    }
}