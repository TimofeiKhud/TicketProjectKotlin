package com.example.ticketservicekotlin.data.source.remote

import com.example.ticketservicekotlin.data.source.local.entity.DatabaseEvent
import com.example.ticketservicekotlin.domain.model.Event
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * File contains DataTransferObjects. These are responsible for parsing responses from the server
 * or formatting objects to send to the server. These should be converted to domain objects before
 * use them.
 */

/**
 * Data Transfer Object for registration.
 */
@JsonClass(generateAdapter = true)
data class NetworkUserRegistration(
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val phoneNumber: String)

/**
 * Data Transfer Object for Network response on registration.
 */
@JsonClass(generateAdapter = true)
data class NetworkStatus(val status: String)

/**
 * Data Transfer Object for logging in.
 */
@JsonClass(generateAdapter = true)
data class NetworkLoginRequest(
    val email: String,
    val passwod: String)

/**
 * Data Transfer Object for token and roles of user
 * as Network response on logging in.
 */
@JsonClass(generateAdapter = true)
data class NetworkLoginResponse(
    val token: String,
    val roles: List<String>)

/**
 * NetworkEventContainer contains a list of Events.
 *
 * This is to parse first level of network result which looks like
 *
 * {
 *   "events": []
 * }
 */
@JsonClass(generateAdapter = true)
data class NetworkEventContainer(val events: List<NetworkEvent?>)

/**
 * Data Transfer Object of event to be mapped.
 * Event represent an upcoming event for which user can buy tickets.
 */
@JsonClass(generateAdapter = true)
data class NetworkEvent(
    val eventId: String,
    val eventStatus: Int?,
    val eventName: String?,
    val artist: String?,
    val eventStart: Long,
    val eventDurationHours: Int,
    val hall: Int,
    val eventType: Int,
    val description: String?,
    val images: List<String>,
    val priceRanges: Set<NetworkPriceRanges>,
    val managers: Set<String>)

/**
 * Convert Network results (Data Transfer Objects) to domain objects
 */
fun NetworkEventContainer.asDomainModel(): List<Event> {
    return events
        .map {
            it?.let { it ->
                Event (
                    id = it.eventId,
                    eventStatus = it.eventStatus,
                    eventName = it.eventName,
                    artist = it.artist,
                    eventStart = it.eventStart,
                    eventDurationHours = it.eventDurationHours,
                    hall = it.hall,
                    eventType = it.eventType,
                    description = it.description,
                    images = it.images
                    //priceRanges = it.priceRanges,
                    //managers = it.managers
                )
            }
        }.filterNotNull()
}

/**
 * Convert Network results (Data Transfer Objects) to database objects
 */
fun NetworkEventContainer.asDatabaseModel(): Array<DatabaseEvent> {
    return events
        .map {
            it?.let { it ->
                DatabaseEvent(
                    id = it.eventId,
                    eventStatus = it.eventStatus,
                    eventName = it.eventName,
                    artist = it.artist,
                    eventStart = it.eventStart,
                    eventDurationHours = it.eventDurationHours,
                    hall = it.hall,
                    eventType = it.eventType,
                    description = it.description,
                    images = it.images
                    //priceRanges = it.priceRanges,
                    //managers = it.managers
                )
            }
        }.filterNotNull()
        .toTypedArray()
}

/**
 * Data Transfer Object for getting Events in specified date range.
 */
@JsonClass(generateAdapter = true)
data class NetworkDateRange(
    val fromDate: Long,
    val toDate: Long)


/**
 * Data transfer object of rest tickets for event with their prices.
 */
@JsonClass(generateAdapter = true)
data class NetworkEventInfo(
    val eventId: String,
    val maxPrice: Double,
    val minPrice: Double,
    val restTickets: Int)

/**
 * Data transfer object of information about hall by Event.
 */
@JsonClass(generateAdapter = true)
data class NetworkHallStructure(
    val lockedSeats: List<Map<String, List<String>>>,
    val priceRanges: List<NetworkPriceRanges>)

/**
 * Class used by Data Transfer Objects for booking/cancel booking given seats at given event.
 */
//@JsonClass(generateAdapter = true)
//data class NetworkLockedSeats(
//    val seats: Map<String, List<String>>
////val row: String,
////val seats: List<String>
//)

/**
 * Class used by Data Transfer Objects.
 * Contains [NetworkLockedSeats] with their price and color in RGB format
 */
@JsonClass(generateAdapter = true)
data class NetworkPriceRanges(
    @Json(name = "seats")
    val seats: Map<String, List<String>>,
    val price: Double,
    val color: String)

/**
 * Data Transfer Object for booking given seats at given event.
 */
@JsonClass(generateAdapter = true)
data class NetworkEventBooking(
val eventId: String,
val lockedSeats: List<Map<String, List<String>>>)
