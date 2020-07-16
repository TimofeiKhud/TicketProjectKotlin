package com.example.ticketservicekotlin.di.eventlist

import com.example.ticketservicekotlin.presentation.eventlist.EventListFragment
import dagger.Subcomponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

// Classes annotated with @ActivityScope will have a unique instance in this Component
@Subcomponent
interface EventListComponent {
    // Factory to create instances of EventListComponent
    @Subcomponent.Factory
    interface Factory {
        fun create(): EventListComponent
    }

    // Classes that can be injected by this Component
    fun inject(activity: EventListFragment)
}