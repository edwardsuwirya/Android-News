package com.enigmacamp.simple_news.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.enigmacamp.simple_news.di.ViewModelKey
import com.enigmacamp.simple_news.ui.ViewModelFactory
import com.enigmacamp.simple_news.ui.article.ArticleViewModel
import com.enigmacamp.simple_news.ui.newssource.NewsSourceViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(NewsSourceViewModel::class)
    abstract fun bindNewsSourceViewModel(newsSourceViewModel: NewsSourceViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ArticleViewModel::class)
    abstract fun bindArticleViewModel(articleViewModel: ArticleViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory):
            ViewModelProvider.Factory
}