package com.jop.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jop.database.dao.PokemonDao
import com.jop.domain.entities.PokemonEntity

@Database(
    entities = [PokemonEntity::class],
    version = 2, exportSchema = false
)
abstract class DBPokemon : RoomDatabase(){
    companion object {
        @JvmStatic
        fun instanceRoom(context: Context): DBPokemon = Room.databaseBuilder(context, DBPokemon::class.java,"DBPokemon")
            .fallbackToDestructiveMigration()
            .build()
    }

    abstract fun PokemonLocale(): PokemonDao

}