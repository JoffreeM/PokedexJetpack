package com.jop.pokedexjetpack.di

import android.content.Context
import com.jop.database.DBPokemon
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Singleton
    @Provides
    fun provideRoom(
        @ApplicationContext
        context: Context
    ): DBPokemon = DBPokemon.instanceRoom(context)
}