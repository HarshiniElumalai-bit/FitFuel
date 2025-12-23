package com.example.fitfuel.Repository

import com.example.fitfuel.database.Recipedao
import com.example.fitfuel.database.fitnessdao
import com.example.fitfuel.model.Recipe
import com.example.fitfuel.model.fitness

class fitnessRepository (private val fitnessdao: fitnessdao) {
    suspend fun getAllfitness(): List<fitness> {
        return fitnessdao.getAllfitness()

    }
    suspend fun getfitnessById(id: Int): fitness? {
        return fitnessdao.getfitnessById(id)
    }
    suspend fun addfitness(fitness: fitness) {
        fitnessdao.addfitness(fitness)
    }
    suspend fun updatefitness(fitness:fitness) {
        fitnessdao.updatefitness(fitness)
    }
    suspend fun deletefitness(fitness: fitness) {
        fitnessdao.deletefitness(fitness)
    }
}