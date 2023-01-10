package com.enigmacamp.simple_news.di.modules

import com.enigmacamp.simple_news.di.scopes.ArticleScope
import com.enigmacamp.simple_news.di.scopes.NewsSourceScope
import com.enigmacamp.simple_news.ui.MainActivity
import com.enigmacamp.simple_news.ui.article.ArticleFragment
import com.enigmacamp.simple_news.ui.newssource.NewsSourceFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivitiesModule {
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity

    @NewsSourceScope
    @ContributesAndroidInjector
    abstract fun contributeNewsSourceFragment(): NewsSourceFragment

    @ArticleScope
    @ContributesAndroidInjector
    abstract fun contributeArticleFragment(): ArticleFragment
}