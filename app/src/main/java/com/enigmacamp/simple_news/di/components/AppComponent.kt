package com.enigmacamp.simple_news.di.components

import com.enigmacamp.simple_news.BaseApplication
import com.enigmacamp.simple_news.di.scopes.ActivityScope
import com.enigmacamp.simple_news.di.modules.ActivitiesModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector

@ActivityScope
@Component(
    modules = [AndroidInjectionModule::class, ActivitiesModule::class],
    dependencies = [CoreComponent::class, ViewModelComponent::class]
)
interface AppComponent : AndroidInjector<BaseApplication>