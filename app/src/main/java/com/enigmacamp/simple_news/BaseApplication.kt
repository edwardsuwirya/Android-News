package com.enigmacamp.simple_news


import com.enigmacamp.simple_news.di.components.*
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class BaseApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder()
            .articleComponent(provideArticleComponent())
            .newsSourceComponent(provideNewsSourceComponent())
            .coreComponent(provideCoreComponent())
            .build()
//        TODO()
    }

    private fun provideCoreComponent(): CoreComponent {
        return DaggerCoreComponent.factory().application(this)
    }

    private fun provideArticleComponent(): ArticleComponent {
        return DaggerArticleComponent.builder().coreComponent(provideCoreComponent()).build()
    }

    private fun provideNewsSourceComponent(): NewsSourceComponent {
        return DaggerNewsSourceComponent.builder().coreComponent(provideCoreComponent()).build()
    }

}