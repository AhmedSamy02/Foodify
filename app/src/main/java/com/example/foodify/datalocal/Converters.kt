package com.example.foodify.datalocal


import androidx.room.TypeConverter

class Converters {

    @TypeConverter
    fun fromTagsList(tags: List<String>): String {
        return tags.joinToString(",")
    }

    @TypeConverter
    fun toTagsList(tagsString: String): List<String> {
        return if (tagsString.isEmpty()) emptyList() else tagsString.split(",")
    }
}
