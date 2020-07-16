package com.example.ticketservicekotlin.di.app

import android.app.Application
import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.example.ticketservicekotlin.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesContext(@ApplicationContext context: Context): Context = context

    @Singleton
    @Provides
    fun providesCoroutineScope(): CoroutineScope {
        return CoroutineScope(Dispatchers.Default)
    }

    @Singleton
    @Provides
    fun providesRequestOptions(): RequestOptions{
        return RequestOptions
            .placeholderOf(R.drawable.loading_animation)
            .error(R.drawable.ic_broken_image);
    }

    @Singleton
    @Provides
    fun providesGlideInstance(context: Context, requestOptions: RequestOptions): RequestManager {
        return Glide.with(context)
            .setDefaultRequestOptions(requestOptions);
    }

    @Singleton
    @Provides
    fun providesAppDrawable(context: Context): Drawable?{
        return ContextCompat.getDrawable(context, R.drawable.ic_launcher_foreground);
    }
}