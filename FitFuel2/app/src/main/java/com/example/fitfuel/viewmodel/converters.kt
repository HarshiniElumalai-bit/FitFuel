// file: app/src/main/java/com/example/fitfuel/database/Converters.kt
package com.example.fitfuel.database

import androidx.room.TypeConverter
import com.example.fitfuel.ResponseModel.AnalyzedInstruction
import com.example.fitfuel.ResponseModel.ExtendedIngredient
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromExtendedIngredientList(list: List<ExtendedIngredient>?): String =
        gson.toJson(list)

    @TypeConverter
    fun toExtendedIngredientList(data: String?): List<ExtendedIngredient> =
        if (data.isNullOrBlank()) emptyList()
        else gson.fromJson(data, object : TypeToken<List<ExtendedIngredient>>() {}.type)

    @TypeConverter
    fun fromAnalyzedInstructionList(list: List<AnalyzedInstruction>?): String =
        gson.toJson(list)

    @TypeConverter
    fun toAnalyzedInstructionList(data: String?): List<AnalyzedInstruction> =
        if (data.isNullOrBlank()) emptyList()
        else gson.fromJson(data, object : TypeToken<List<AnalyzedInstruction>>() {}.type)

    // List<String> <-> String
    @TypeConverter
    fun fromStringList(value: List<String>?): String = value?.joinToString(separator = "||") ?: ""

    @TypeConverter
    fun toStringList(value: String?): List<String> =
        if (value.isNullOrBlank()) emptyList() else value.split("||").map { it.trim() }
}
