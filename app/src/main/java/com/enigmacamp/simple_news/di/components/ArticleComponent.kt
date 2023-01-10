package com.enigmacamp.simple_news.di.components

import com.enigmacamp.simple_news.data.repository.ArticleRepository
import com.enigmacamp.simple_news.di.modules.ArticleModule
import com.enigmacamp.simple_news.di.scopes.ArticleScope
import dagger.Component


@ArticleScope
@Component(modules = [ArticleModule::class], dependencies = [CoreComponent::class])
interface ArticleComponent {
    fun provideArticleRepository(): ArticleRepository
}