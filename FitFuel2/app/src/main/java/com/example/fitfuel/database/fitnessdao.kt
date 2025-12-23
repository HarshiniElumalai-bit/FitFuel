package com.example.fitfuel.database

import androidx.room.Query
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Update
import com.example.fitfuel.model.Recipe
import androidx.room.Delete
import com.example.fitfuel.model.fitness

@Dao
interface fitnessdao {
    @Query("SELECT * FROM fitness")
    suspend fun getAllfitness(): List<fitness>
    @Query("SELECT * FROM fitness  WHERE id = :id")
    suspend fun getfitnessById(id: Int): fitness?
    @Insert
    suspend fun addfitness(fitness: fitness)
    @Update
    suspend fun updatefitness(fitness: fitness)
    @Delete
    suspend fun deletefitness(fitness: fitness)




}