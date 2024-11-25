package com.example.aram.features.course

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.aram.model.api.ApiManager

class courseViewModelFactory(private val apiManager: ApiManager) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return courseViewModel(apiManager) as T
    }
}
