package com.example.fitfuel.database
import androidx.room.Query
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Update
import com.example.fitfuel.model.Recipe
import androidx.room.Delete

@Dao
interface Recipedao {
    @Query("SELECT * FROM recipes")
    suspend fun getAllRecipes(): List<Recipe>
    @Query("SELECT * FROM recipes WHERE id = :id")
    suspend fun getRecipeById(id: Int): Recipe?
    @Insert
    suspend fun addRecipe(recipe: Recipe)
    @Update
    suspend fun updateRecipe(recipe: Recipe)
    @Delete
    suspend fun deleteRecipe(recipe: Recipe)
    @Query("DELETE FROM recipes")
    suspend fun deleteAllRecipes()


}

