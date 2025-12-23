package com.example.fitfuel.model
import androidx.room.DatabaseView
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.fitfuel.ResponseModel.AnalyzedInstruction
import com.example.fitfuel.ResponseModel.ExtendedIngredient

@Entity(tableName = "recipes")

data class Recipe(
    @PrimaryKey
    val id: Int,
    val name: String,
    val ingredients: List<ExtendedIngredient>,
    val instructions: List<AnalyzedInstruction>,
    val youtubeLink: String,

    )



@Entity(tableName = "fitness")
data class fitness(
    @PrimaryKey
    val id:Int,
    val name: String,
    val ingredients: List<ExtendedIngredient>,
    val instructions: List<AnalyzedInstruction>,
    val youtubeLink: String,
    val calories: Int,
    val protein: Int,
    val carbs: Int,
    val fat: Int,
)


