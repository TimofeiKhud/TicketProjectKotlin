package com.example.ticketservicekotlin.data.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.ticketservicekotlin.data.source.local.dao.EventDao
import com.example.ticketservicekotlin.data.source.local.entity.Converters
import com.example.ticketservicekotlin.data.source.local.entity.DatabaseEvent

/**
 * To manage data items that can be accessed, updated
 * and maintain relationships between them
 */
@Database(entities = [DatabaseEvent::class], version = 1)
@TypeConverters(Converters::class)
abstract class EventsDatabase() : RoomDatabase(){
    companion object {
        const val DB_NAME = "EventsDataBase"
    }
    abstract val eventDao : EventDao
}