package com.example.aram.features.session

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.aram.model.api.ApiManager

class sessionViewModelFactory(private val apiManager: ApiManager) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return sessionViewModel(apiManager) as T
    }
}
