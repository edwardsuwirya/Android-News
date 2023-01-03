package com.enigmacamp.simple_news.di.ui

import com.enigmacamp.simple_news.ui.MainActivity
import com.enigmacamp.simple_news.ui.article.ArticleActivity
import com.enigmacamp.simple_news.ui.newssource.NewsSourceActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivitiesModule {
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributeArticleActivity(): ArticleActivity

    @ContributesAndroidInjector
    abstract fun contributeNewsSourceActivity(): NewsSourceActivity

}