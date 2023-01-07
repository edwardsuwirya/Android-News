package com.enigmacamp.simple_news.di.components

import com.enigmacamp.simple_news.data.api.NewsApi
import com.enigmacamp.simple_news.di.modules.CoreModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [CoreModule::class])
interface CoreComponent {
    fun newsApi(): NewsApi
}