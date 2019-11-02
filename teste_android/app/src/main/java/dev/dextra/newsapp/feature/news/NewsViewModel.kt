package dev.dextra.newsapp.feature.news

import dev.dextra.newsapp.api.model.Source
import dev.dextra.newsapp.api.repository.NewsRepository
import dev.dextra.newsapp.base.BaseViewModel
import okhttp3.Interceptor
import okhttp3.Response


class NewsViewModel(
    private val newsRepository: NewsRepository,
    private val newsActivity: NewsActivity
) : BaseViewModel() {

    private var source: Source? = null

    fun configureSource(source: Source) {
        this.source = source
    }

    fun loadNews(page: Int) {
        newsActivity.showLoading()
        addDisposable(
            newsRepository.getEverything(source?.id, page).subscribe({
                newsActivity.showData(it.articles)
                newsActivity.hideLoading()
            },
                {
                    newsActivity.hideLoading()
                })
        )
    }
}
