package com.example.ticketservicekotlin.di.app

import android.content.Context
import androidx.room.Room
import com.example.ticketservicekotlin.data.source.local.dao.EventDao
import com.example.ticketservicekotlin.data.source.local.database.EventsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object EventDatabaseModule {

    @Provides
    @Singleton
    internal fun provideEventsDatabase(@ApplicationContext context: Context): EventsDatabase {
        return Room.databaseBuilder(
            context,
            EventsDatabase::class.java,
            EventsDatabase.DB_NAME
        ).allowMainThreadQueries().build()
    }

    @Provides
    @Singleton
    internal fun providesEventDao(eventDatabase: EventsDatabase): EventDao {
        return eventDatabase.eventDao
    }
}