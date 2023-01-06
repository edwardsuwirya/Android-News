package com.enigmacamp.simple_news.di.ui

import com.enigmacamp.simple_news.ui.MainActivity
import com.enigmacamp.simple_news.ui.article.ArticleFragment
import com.enigmacamp.simple_news.ui.newssource.NewsSourceFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivitiesModule {
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributeNewsSourceFragment(): NewsSourceFragment

    @ContributesAndroidInjector
    abstract fun contributeArticleFragment(): ArticleFragment
}