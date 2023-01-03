package com.enigmacamp.simple_news.di.app

import android.app.Application
import com.enigmacamp.simple_news.BaseApplication
import com.enigmacamp.simple_news.di.news.NewsModule
import com.enigmacamp.simple_news.di.ui.ActivitiesModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidInjectionModule::class, ActivitiesModule::class, AppModule::class, NewsModule::class]
)
interface AppComponent : AndroidInjector<BaseApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: Application): Builder
        fun build(): AppComponent
    }
}