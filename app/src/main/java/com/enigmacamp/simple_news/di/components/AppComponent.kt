package com.enigmacamp.simple_news.di.components

import com.enigmacamp.simple_news.BaseApplication
import com.enigmacamp.simple_news.di.scopes.ActivityScope
import com.enigmacamp.simple_news.di.modules.ActivitiesModule
import com.enigmacamp.simple_news.di.modules.ViewModelModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector

@ActivityScope
@Component(
    modules = [AndroidInjectionModule::class, ActivitiesModule::class, ViewModelModule::class],
    dependencies = [CoreComponent::class, ArticleComponent::class, NewsSourceComponent::class]
)
interface AppComponent : AndroidInjector<BaseApplication>