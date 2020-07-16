package com.example.ticketservicekotlin.data.eventlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.ticketservicekotlin.data.source.local.database.EventsDatabase
import com.example.ticketservicekotlin.data.source.local.entity.asDomainModel
import com.example.ticketservicekotlin.data.source.local.utils.ApiStatus
import com.example.ticketservicekotlin.data.source.remote.*
import com.example.ticketservicekotlin.domain.model.Event
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Default implementation of [EventListRepository]. Single entry point for managing events' data.
 */
@ActivityRetainedScoped
class EventListRepositoryImpl @Inject constructor(private val database: EventsDatabase,
                                                  private val retrofitService: RetrofitService) : EventListRepository {

    override val events: LiveData<List<Event>> = Transformations.map(database.eventDao.getEvents()) {
        it.asDomainModel()
    }

    // The internal MutableLiveData String that stores the status of the most recent request
    private val _status = MutableLiveData<ApiStatus>()

    // The external immutable LiveData for the request status String
    override val status: LiveData<ApiStatus>
        get() = _status

    override suspend fun refreshEventList() {
        try {
            _status.value = ApiStatus.LOADING
            //This will run on a thread managed by Retrofit request
        withContext(Dispatchers.IO) {
                //Await for completion of our retrofit request
                val eventList = retrofitService.getCurrentEvents(0, 10).await()
                val eventContainer = NetworkEventContainer(eventList)
                database.eventDao.deleteAllEvents()
                database.eventDao.insertAll(*eventContainer.asDatabaseModel())
        }
            _status.value = ApiStatus.DONE
        }catch (t: Throwable){
            _status.value = ApiStatus.ERROR
        }
    }
}