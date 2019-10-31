package dev.dextra.newsapp.feature.sources

import androidx.lifecycle.MutableLiveData
import dev.dextra.newsapp.api.model.Source
import dev.dextra.newsapp.api.repository.NewsRepository
import dev.dextra.newsapp.base.BaseViewModel
import dev.dextra.newsapp.base.NetworkState
import java.util.*

class SourcesModel(private val newsRepository: NewsRepository) : BaseViewModel() {

    val networkState = MutableLiveData<NetworkState>()
    val sources = MutableLiveData<List<Source>>()

//    fun loadSources() {
//        networkState.postValue(NetworkState.RUNNING)
//        addDisposable(
//            newsRepository.getSources(
//                selectedCountry?.name?.toLowerCase(Locale.getDefault()),
//                selectedCategory?.name?.toLowerCase(Locale.getDefault())
//            ).subscribe({
//                sources.postValue(it.sources)
//                if (it.sources.isEmpty()) {
//                    networkState.postValue(NetworkState.EMPTY)
//                } else {
//                    networkState.postValue(NetworkState.SUCCESS)
//                }
//            }, {
//                networkState.postValue(NetworkState.ERROR)
//            })
//        )
//    }
}