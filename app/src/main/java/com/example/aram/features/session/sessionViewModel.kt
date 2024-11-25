package com.example.aram.features.session

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aram.model.api.ApiManager
import com.example.aram.model.dataClasses.SessionItem
import kotlinx.coroutines.launch

class sessionViewModel(private val apiManager: ApiManager) : ViewModel() {

    private val _sessionLiveData = MutableLiveData<List<SessionItem.SessionItemItem>>()
    val sessionLiveData: LiveData<List<SessionItem.SessionItemItem>> get() = _sessionLiveData

    fun loadSessions(courseId: Int) {
        viewModelScope.launch {
            try {
                val sessionList = apiManager.getSessionItem(courseId)
                _sessionLiveData.postValue(sessionList)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
