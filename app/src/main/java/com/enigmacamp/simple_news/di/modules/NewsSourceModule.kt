package com.enigmacamp.simple_news.di.modules

import com.enigmacamp.simple_news.data.repository.NewsSourceRepository
import com.enigmacamp.simple_news.data.repository.NewsSourceRepositoryImpl
import com.enigmacamp.simple_news.di.scopes.NewsSourceScope
import dagger.Binds
import dagger.Module

@Module
abstract class NewsSourceModule {
    @NewsSourceScope
    @Binds
    abstract fun bindsNewsSourceModule(newsSourceRepositoryImpl: NewsSourceRepositoryImpl): NewsSourceRepository
}