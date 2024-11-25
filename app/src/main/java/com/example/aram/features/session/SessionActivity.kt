package com.example.aram.features.session

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aram.databinding.ActivitySessionBinding
import com.example.aram.model.api.ApiManager
import com.example.aram.model.dataClasses.SessionItem

class SessionActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySessionBinding
    private lateinit var adapter: SessionAdapter

    // مقداردهی ViewModel با استفاده از by viewModels
    private val sessionViewModel: sessionViewModel by viewModels {
        sessionViewModelFactory(ApiManager())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySessionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // تنظیم Toolbar به عنوان ActionBar
        setSupportActionBar(binding.toolbarAsli)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // دریافت مقادیر از Intent
        val courseId = intent.getIntExtra("id", -1)
        val title = intent.getStringExtra("title") ?: "Unknown Title"

        // تنظیم عنوان دوره
        binding.courseTitle.text = title

        // تنظیم RecyclerView
        setupRecyclerView()

        // مشاهده داده‌ها از ViewModel
        observeViewModel()

        // بارگذاری داده‌ها
        if (courseId != -1) {
            sessionViewModel.loadSessions(courseId)
        } else {
            Log.e("SessionActivity", "Invalid courseId")
        }
    }

    private fun setupRecyclerView() {
        adapter = SessionAdapter(ArrayList())
        binding.sessionRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.sessionRecyclerView.adapter = adapter
    }

    private fun observeViewModel() {
        sessionViewModel.sessionLiveData.observe(this) { sessionList ->
            if (sessionList != null) {
                updateRecyclerView(sessionList)
            } else {
                Log.e("SessionActivity", "Session list is null")
            }
        }
    }

    private fun updateRecyclerView(sessionList: List<SessionItem.SessionItemItem>) {
        adapter.updateData(ArrayList(sessionList)) // فرض بر اینکه متد updateData در Adapter تعریف شده است
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return true
    }


}
