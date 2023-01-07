package com.enigmacamp.simple_news.di.components

import android.app.Application
import com.bumptech.glide.RequestManager
import com.enigmacamp.simple_news.data.api.NewsApi
import com.enigmacamp.simple_news.di.modules.CoreModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [CoreModule::class])
interface CoreComponent {
    fun newsApi(): NewsApi
    fun glideRequestManager(): RequestManager

    @Component.Factory
    interface Factory {
        fun application(@BindsInstance app: Application): CoreComponent
    }
}