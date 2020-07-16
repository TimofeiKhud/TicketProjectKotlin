package com.example.ticketservicekotlin.domain.eventlist

import androidx.lifecycle.LiveData
import com.example.ticketservicekotlin.data.source.local.utils.ApiStatus
import com.example.ticketservicekotlin.domain.model.Event


interface EventListInteractor {
    suspend fun refreshEventList()
    fun getEvents(): LiveData<List<Event>>
    fun getApiStatus(): LiveData<ApiStatus>
}