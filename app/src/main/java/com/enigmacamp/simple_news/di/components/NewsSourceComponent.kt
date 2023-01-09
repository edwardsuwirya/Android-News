package com.enigmacamp.simple_news.di.components

import com.enigmacamp.simple_news.data.repository.NewsSourceRepository
import com.enigmacamp.simple_news.di.modules.NewsSourceModule
import com.enigmacamp.simple_news.di.scopes.NewsSourceScope
import dagger.Component


@NewsSourceScope
@Component(modules = [NewsSourceModule::class], dependencies = [CoreComponent::class])
interface NewsSourceComponent {
    fun provideNewsSourceRepository(): NewsSourceRepository
}