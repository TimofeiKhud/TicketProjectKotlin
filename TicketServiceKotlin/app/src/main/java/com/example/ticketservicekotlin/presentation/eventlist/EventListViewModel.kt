package com.example.ticketservicekotlin.presentation.eventlist

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ticketservicekotlin.domain.eventlist.EventListInteractor
import com.example.ticketservicekotlin.domain.model.Event
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.launch


/**
 * The [ViewModel] that is attached to the [EventListFragment].
 */
// Scopes this ViewModel to components that use @ActivityScope
@ActivityRetainedScoped
class EventListViewModel @ViewModelInject constructor(
    private val eventListInteractor: EventListInteractor
) : ViewModel() {

    //Get EventList LiveData from database
    val events = eventListInteractor.getEvents()

    //immutable LiveData for the events request status String
    val status = eventListInteractor.getApiStatus()

    // Internally, we use a MutableLiveData to handle navigation to the selected Event
    private val _navigateToSelectedEvent = MutableLiveData<Event>()

    // The external immutable LiveData for the navigation Event
    val navigateToSelectedProperty: LiveData<Event>
        get() = _navigateToSelectedEvent

    init {
        viewModelScope.launch {
            eventListInteractor.refreshEventList()
        }
    }

    /**
     * When the Event is clicked, set the [_navigateToSelectedEvent] [MutableLiveData]
     * @param event The [Event] that was clicked on.
     */
    fun displayEventDetails(event: Event) {
        _navigateToSelectedEvent.value = event
    }

    /**
     * After the navigation has taken place, make sure [_navigateToSelectedEvent] is set to null
     */
    fun displayEventDetailsComplete() {
        _navigateToSelectedEvent.value = null
    }
}