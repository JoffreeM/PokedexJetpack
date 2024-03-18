package com.jop.pokedexjetpack.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.jop.pokedexjetpack.AppPokedex

fun urlImageAltered(url: String): String {
    val idPokemon = Regex("""/(\d+)/$""").find(url)?.groupValues?.get(1)
    return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$idPokemon.png"
}

fun urlIdPokemon(url: String): Int {
    val idPokemon = Regex("""/(\d+)/$""").find(url)?.groupValues?.get(1)
    return idPokemon?.toInt() ?: 0
}

fun isNetworkAvailable(): Boolean {
    val connectivityManager =
        AppPokedex.appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val network = connectivityManager.activeNetwork ?: return false
    val networkCapabilities =
        connectivityManager.getNetworkCapabilities(network) ?: return false
    return networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
            || networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
}
