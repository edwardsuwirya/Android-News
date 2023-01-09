package com.enigmacamp.simple_news.di.components

import androidx.lifecycle.ViewModelProvider
import com.enigmacamp.simple_news.di.modules.ViewModelModule
import com.enigmacamp.simple_news.di.scopes.FragmentScope
import dagger.Component

@FragmentScope
@Component(
    modules = [ViewModelModule::class],
    dependencies = [ArticleComponent::class, NewsSourceComponent::class]
)
interface ViewModelComponent {
    fun viewModelFactory(): ViewModelProvider.Factory
}