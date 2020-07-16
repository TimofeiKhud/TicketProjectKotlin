package com.example.ticketservicekotlin.di.app

import com.example.ticketservicekotlin.di.eventdetail.EventDetailComponent
import com.example.ticketservicekotlin.di.eventlist.EventListComponent
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

// This module tells AppComponent which are its subcomponents
@InstallIn(ApplicationComponent::class)
@Module(
    subcomponents = [
    EventListComponent::class
    //EventDetailComponent::class
    ]
)
class AppSubcomponents