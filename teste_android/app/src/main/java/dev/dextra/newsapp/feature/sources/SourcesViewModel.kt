package dev.dextra.newsapp.feature.sources

import androidx.lifecycle.MutableLiveData
import dev.dextra.newsapp.api.model.Source
import dev.dextra.newsapp.api.model.enums.Category
import dev.dextra.newsapp.api.model.enums.Country
import dev.dextra.newsapp.api.repository.NewsRepository
import dev.dextra.newsapp.base.BaseViewModel
import dev.dextra.newsapp.base.NetworkState
import java.util.*

class SourcesViewModel(private val newsRepository: NewsRepository) : BaseViewModel() {

    val sources = MutableLiveData<List<Source>>()
    val networkState = MutableLiveData<NetworkState>()

    private var selectedCountry: Country? = null
    private var selectedCategory: Category? = Category.GENERAL

    fun loadSources() {
        networkState.postValue(NetworkState.RUNNING)
        addDisposable(
            newsRepository.getSources(
                selectedCountry?.name?.toLowerCase(Locale.getDefault()),
                selectedCategory?.name?.toLowerCase(Locale.getDefault())
            ).subscribe({
                sources.postValue(it.sources)
                if (it.sources.isEmpty()) {
                    networkState.postValue(NetworkState.EMPTY)
                } else {
                    networkState.postValue(NetworkState.SUCCESS)
                }
            }, {
                networkState.postValue(NetworkState.ERROR)
            })
        )
    }

    fun changeCountry(country: Country?) {
        if (Country.ALL.equals(country)) selectedCountry = null
        else selectedCountry = country
        loadSources()
    }

    fun changeCategory(category: Category) {
        if (Category.ALL.equals(category)) selectedCategory = Category.GENERAL
        else selectedCategory = category
        loadSources()
    }
}
