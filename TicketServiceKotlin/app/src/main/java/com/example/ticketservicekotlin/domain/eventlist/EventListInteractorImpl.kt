package com.example.ticketservicekotlin.domain.eventlist

import androidx.lifecycle.LiveData
import com.example.ticketservicekotlin.data.eventlist.EventListRepository
import com.example.ticketservicekotlin.data.source.local.utils.ApiStatus
import com.example.ticketservicekotlin.domain.model.Event
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class EventListInteractorImpl @Inject constructor(private val eventListRepository: EventListRepository) :
    EventListInteractor {
    override suspend fun refreshEventList() {
        eventListRepository.refreshEventList()
    }

    override fun getEvents(): LiveData<List<Event>> {
        return eventListRepository.events
    }

    override fun getApiStatus(): LiveData<ApiStatus> {
        return eventListRepository.status
    }
}