package com.example.ticketservicekotlin.presentation.eventdetail

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.ticketservicekotlin.domain.model.Event
import com.example.ticketservicekotlin.util.Constants.EVENT_KEY
import dagger.hilt.android.scopes.ActivityScoped

/**
 *  The [ViewModel] associated with the [EventDetailFragment], containing information about the selected
 *  [Event].
 */
@ActivityScoped
class EventDetailViewModel @ViewModelInject constructor(
    @Assisted savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _selectedEvent = MutableLiveData<Event>()

    //The external LiveData for the selected event
    val selectedEvent: LiveData<Event>
    get() = _selectedEvent

    // Initialize the _selectedEvent MutableLiveData
    init {
        _selectedEvent.value = savedStateHandle.get<Event>(EVENT_KEY)
    }
}