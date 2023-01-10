package com.enigmacamp.simple_news.di.modules

import com.enigmacamp.simple_news.data.repository.NewsSourceRepository
import com.enigmacamp.simple_news.data.repository.NewsSourceRepositoryImpl
import com.enigmacamp.simple_news.di.scopes.FeatureScope
import dagger.Binds
import dagger.Module

@Module
abstract class NewsSourceModule {
    @Binds
    @FeatureScope
    abstract fun bindsNewsSourceModule(newsSourceRepositoryImpl: NewsSourceRepositoryImpl): NewsSourceRepository
}