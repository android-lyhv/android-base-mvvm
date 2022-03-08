package com.lyhv.mvvm.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.lyhv.mvvm.core.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject internal constructor(appRepository: AppRepository) :
    ViewModel() {
    val newsObservable =
        appRepository.fetchNews("science").asLiveData(viewModelScope.coroutineContext)
}