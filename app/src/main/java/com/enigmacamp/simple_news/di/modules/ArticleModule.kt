package com.enigmacamp.simple_news.di.modules

import com.enigmacamp.simple_news.data.repository.ArticleRepository
import com.enigmacamp.simple_news.data.repository.ArticleRepositoryImpl
import com.enigmacamp.simple_news.di.scopes.ArticleScope
import dagger.Binds
import dagger.Module

@Module
abstract class ArticleModule {
    @Binds
    @ArticleScope
    abstract fun bindsArticleModule(articleRepositoryImpl: ArticleRepositoryImpl): ArticleRepository
}