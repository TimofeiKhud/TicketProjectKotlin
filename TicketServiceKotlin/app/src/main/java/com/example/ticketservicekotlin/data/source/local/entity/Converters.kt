package com.example.ticketservicekotlin.data.source.local.entity

import androidx.room.TypeConverter

/**
 * To provide mappings from arbitrary objects
 * to types Room understands, and vice-versa.
 */
class Converters {
        @TypeConverter
        fun fromStringToList(value: String): List<String>? {
            return value.split(",")
        }

        @TypeConverter
        fun fromListToString(list: List<String>): String {
            return list.joinToString(",")
        }
}

