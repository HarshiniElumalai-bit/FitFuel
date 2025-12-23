package com.example.fitfuel.database

import androidx.room.Database
import android.content.Context
import com.example.fitfuel.model.Recipe

import androidx.room.RoomDatabase
import androidx.room.Room
import androidx.room.TypeConverters
import com.example.fitfuel.model.fitness


@Database(entities = [Recipe::class, fitness::class],version = 6)
@TypeConverters(Converters::class)
abstract class RecipeDatabase : RoomDatabase() {
    abstract fun recipeDao(): Recipedao
    abstract fun fitnessDao(): fitnessdao

    companion object{
        @Volatile
        private var INSTANCE: RecipeDatabase? = null
        fun getDatabase(context: Context): RecipeDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                                context.applicationContext,
                                RecipeDatabase::class.java,
                                "recipe_database"
                            ).fallbackToDestructiveMigration(true)
                    .build()
                INSTANCE = instance
                instance
            }


        }

    }
}
