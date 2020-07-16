package com.example.ticketservicekotlin.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.ticketservicekotlin.domain.model.Event

/**
 * Event represent an upcoming event for which user can buy tickets.
 */
@Entity
data class DatabaseEvent constructor(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "event_status") val eventStatus: Int?,
    @ColumnInfo(name = "event_name") val eventName: String?,
    val artist: String?,
    @ColumnInfo(name = "event_start") val eventStart: Long,
    @ColumnInfo(name = "event_duration_hours") val eventDurationHours: Int,
    val hall: Int,
    @ColumnInfo(name = "event_type") val eventType: Int,
    val description: String?,
    val images: List<String>
//    @ColumnInfo(name = "price_ranges") val priceRanges: String,
//    val managers: String
)

fun List<DatabaseEvent>.asDomainModel(): List<Event> {
    return map {
        Event (
            id = it.id,
            eventStatus = it.eventStatus,
            eventName = it.eventName,
            artist = it.artist,
            eventStart = it.eventStart,
            eventDurationHours = it.eventDurationHours,
            hall = it.hall,
            eventType = it.eventType,
            description = it.description,
            images = it.images
//            priceRanges = it.priceRanges,
//            managers = it.managers
        )
    }
}