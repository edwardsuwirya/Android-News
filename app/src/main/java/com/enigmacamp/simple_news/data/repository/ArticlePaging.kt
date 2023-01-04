package com.enigmacamp.simple_news.data.repository

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.enigmacamp.simple_news.data.api.NewsApi
import com.enigmacamp.simple_news.data.api.response.Article

class ArticlePaging(private val source: String, private val service: NewsApi) :
    PagingSource<Int, Article>() {
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val pageNumber = params.key ?: 1
        return try {
            val response = service.getTopHeadlineNews(source, pageNumber, 15)
            var nextKey: Int? = null
            if (response.isSuccessful) {
                response.body()?.let {
                    nextKey = if (response.body()!!.articles.isEmpty()) {
                        null
                    } else {
                        pageNumber + 1
                    }
                }
                Log.d("Next key Article", nextKey.toString())
            }
            return LoadResult.Page(
                data = response.body()!!.articles,
                prevKey = null,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            e.message?.let { Log.d("Error Exception", it) }
            LoadResult.Error(e)
        }
    }

}