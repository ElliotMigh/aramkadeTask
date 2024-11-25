package com.example.aram.features.course

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aram.model.api.ApiManager
import com.example.aram.model.dataClasses.ChipItem
import com.example.aram.model.dataClasses.CourseItem
import kotlinx.coroutines.launch

class courseViewModel(private val apiManager: ApiManager) : ViewModel() {

    // LiveData برای دسته‌بندی‌ها (ChipItems)
    private val _categoryLiveData = MutableLiveData<List<ChipItem.ChipItemItem>>()
    val categoryLiveData: LiveData<List<ChipItem.ChipItemItem>> get() = _categoryLiveData

    private val _courseLiveData = MutableLiveData<List<CourseItem.CourseItemItem>>()
    val courseLiveData: LiveData<List<CourseItem.CourseItemItem>> get() = _courseLiveData

    // متد بارگذاری دسته‌بندی‌ها
    fun loadCategory() {
        viewModelScope.launch {
            try {
                val categoryList = apiManager.getCategory()
                _categoryLiveData.postValue(categoryList)
            } catch (e: Exception) {
                // مدیریت خطا
            }
        }
    }

    // متد بارگذاری دوره‌ها
    fun loadCourses(catId: Int) {
        viewModelScope.launch {
            try {
                val courseList = apiManager.getCourse(catId)
                _courseLiveData.postValue(courseList)
            } catch (e: Exception) {
                // مدیریت خطا
            }
        }
    }
}
