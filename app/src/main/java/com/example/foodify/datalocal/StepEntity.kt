package com.example.foodify.datalocal
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "steps")
data class StepEntity(
    @PrimaryKey(autoGenerate = true) val stepId: Int = 0,
    val recipeOwnerId: Int,
    val description: String,
    val stepNumber: Int
)

