package com.example.aram.features.course

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.aram.R
import com.example.aram.databinding.ActivityCourseBinding
import com.example.aram.features.session.SessionActivity
import com.example.aram.model.api.ApiManager
import com.example.aram.model.dataClasses.ChipItem
import com.example.aram.model.dataClasses.CourseItem
import com.google.android.material.chip.Chip

class CourseActivity : AppCompatActivity(), courseAdapter.CourseItemClickListener {

    private lateinit var binding: ActivityCourseBinding
    private lateinit var adapter: courseAdapter
    private val courseViewModel: courseViewModel by viewModels { courseViewModelFactory(ApiManager()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCourseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "مدیتیشن"

        // تنظیم RecyclerView
        binding.recyclerCourse.layoutManager = GridLayoutManager(this, 2)

        // مشاهده تغییرات در LiveData
        courseViewModel.categoryLiveData.observe(this, Observer { categoryList ->
            updateChipGroup(categoryList)
        })

        courseViewModel.courseLiveData.observe(this, Observer { courseList ->
            // به‌روزرسانی آداپتر با دوره‌ها
            adapter = courseAdapter(ArrayList(courseList), this@CourseActivity)
            binding.recyclerCourse.adapter = adapter
        })

        // بارگذاری داده‌ها
        courseViewModel.loadCategory()
        val catID = 1
        courseViewModel.loadCourses(catID)
    }

    // به‌روزرسانی ChipGroup با دسته‌بندی‌ها
    private fun updateChipGroup(chipItems: List<ChipItem.ChipItemItem>) {
        binding.chipGroup.removeAllViews()

        val reversedItems = chipItems.reversed()

        for ((index, item) in reversedItems.withIndex()) {
            val chip = Chip(this).apply {
                text = item.titile
                isCheckable = true
                setOnClickListener {
                    // هنگام کلیک روی چیپ، اطلاعات دوره‌های مربوط به این دسته‌بندی را بارگذاری می‌کنیم
                    courseViewModel.loadCourses(item.id)
                }
                chipBackgroundColor = ContextCompat.getColorStateList(this@CourseActivity, R.color.greenLight)
                setTextColor(ContextCompat.getColor(this@CourseActivity, R.color.greenDark))
                chipCornerRadius = 16f
                setChipIconSize(18f)
                setPadding(8, 8, 8, 8)
            }

            if (index == 0) {
                chip.isChecked = true
                courseViewModel.loadCourses(item.id)  // بارگذاری دوره‌ها برای دسته اول
            }

            binding.chipGroup.addView(chip)
        }
    }

    // کلیک روی دوره
    override fun onCourseItemClicked(courseId: Int, title: String, image2: String) {
        openSessionActivity(courseId, title, image2)
    }

    // باز کردن Activity مربوط به جلسات
    private fun openSessionActivity(courseId: Int, title: String, image2: String) {
        val intent = Intent(this, SessionActivity::class.java).apply {
            putExtra("id", courseId)
            putExtra("title", title)
            putExtra("image2", image2)
        }
        Log.v("CourseActivity", "Image URL: $image2, Title: $title")
        startActivity(intent)
    }
}
