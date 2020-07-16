package com.example.ticketservicekotlin.domain.model

import android.os.Parcelable
import com.example.ticketservicekotlin.util.smartTruncate
import kotlinx.android.parcel.Parcelize
import java.text.SimpleDateFormat
import java.util.*

/**
 * Domain objects are plain Kotlin data classes that represent the things in the app. These are the
 * objects that should be displayed on screen, or manipulated by the app.
 *
 * @see database for objects that are mapped to the database
 * @see network for objects that parse or prepare network calls
 */

/**
 * Event represent an upcoming event for which user can buy tickets.
 */
@Parcelize
data class Event (

    // Unique identifier of the event.
    val id: String,

    // Status of current event: 0 - cancelled; 1 - waiting; 2 - finished.
    val eventStatus: Int?,

    // Title of event.
    val eventName: String?,

    // Artist name.
    val artist: String?,

    // Time stamp of event start.
    val eventStart: Long,

    // Duration hours of event.
    val eventDurationHours: Int,

    // Unique identifier of the hall.
    val hall: Int,

    // Type of the event.
    val eventType: Int,

    // Event description.
    val description: String?,

    //List of links to images.
    val images: List<String>

    //Set of price ranges.
    //val priceRanges: String,

    //Set of given event managers.
    //val managers: String
) : Parcelable {
    /**
     * Short description is used for displaying truncated descriptions in the UI
     */
    val shortDescription: String?
        get() = description?.smartTruncate(100)

    val dateFormat: String
    get() {
        val dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.US);
        return dateFormat.format(eventStart)
    }

    val startTime: String
    get() {
        val dateFormat = SimpleDateFormat("HH : mm", Locale.US);
        return dateFormat.format(eventStart)
    }
}
