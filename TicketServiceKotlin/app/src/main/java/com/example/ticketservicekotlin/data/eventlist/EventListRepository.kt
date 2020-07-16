package com.example.ticketservicekotlin.data.eventlist

import androidx.lifecycle.LiveData
import com.example.ticketservicekotlin.data.source.local.utils.ApiStatus
import com.example.ticketservicekotlin.domain.model.Event

/**
 * Interface to the eventList data layer.
 */
interface EventListRepository {
    val status: LiveData<ApiStatus>
    val events: LiveData<List<Event>>

    suspend fun refreshEventList()
}