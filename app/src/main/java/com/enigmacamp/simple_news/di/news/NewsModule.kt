package com.enigmacamp.simple_news.di.news

import com.enigmacamp.simple_news.data.repository.NewsRepository
import com.enigmacamp.simple_news.data.repository.NewsRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class NewsModule {
    @Binds
    abstract fun bindsNewsModule(newsRepositoryImpl: NewsRepositoryImpl): NewsRepository
}