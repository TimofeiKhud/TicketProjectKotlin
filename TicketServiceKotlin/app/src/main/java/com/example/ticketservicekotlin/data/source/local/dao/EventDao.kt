package com.example.ticketservicekotlin.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.ticketservicekotlin.data.source.local.entity.DatabaseEvent

/**
 * Provides access to [DatabaseEvent] database.
 */
@Dao
interface EventDao {

    /**
     * Select all tasks from the events table.
     *
     * @return all events.
     */
    @Query("select * from databaseevent")
    fun getEvents(): LiveData<List<DatabaseEvent>>

    /**
     * Insert events in the database. If event already exists, replace it.
     *
     * @param events events to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg events: DatabaseEvent)

    /**
     * Delete all events.
     */
    @Query("DELETE from databaseevent")
    suspend fun deleteAllEvents()
}