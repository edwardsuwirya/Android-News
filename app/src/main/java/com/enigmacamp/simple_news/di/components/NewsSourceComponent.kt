package com.enigmacamp.simple_news.di.components

import com.enigmacamp.simple_news.data.repository.NewsSourceRepository
import com.enigmacamp.simple_news.di.modules.NewsSourceModule
import com.enigmacamp.simple_news.di.scopes.FeatureScope
import dagger.Component


@FeatureScope
@Component(modules = [NewsSourceModule::class], dependencies = [CoreComponent::class])
interface NewsSourceComponent {
    fun provideNewsSourceRepository(): NewsSourceRepository
}