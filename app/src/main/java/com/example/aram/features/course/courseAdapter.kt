package com.example.aram.features.course

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aram.databinding.ItemCourseBinding
import com.example.aram.model.dataClasses.CourseItem

class courseAdapter(private val data: ArrayList<CourseItem.CourseItemItem>, private val listener: CourseItemClickListener) :
    RecyclerView.Adapter<courseAdapter.CourseViewHolder>() {

    lateinit var binding: ItemCourseBinding

    inner class CourseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        fun bindViews(data: CourseItem.CourseItemItem) {
            binding.courseTitle.text = data.titile
            binding.courseSession.text = "${data.sessions} جلسه"

            Glide.with(itemView.context)
                .load(data.image)
                .into(binding.imageViewCourse)

            // اضافه کردن کلیک به آیتم
            itemView.setOnClickListener {
                listener.onCourseItemClicked(data.id, data.titile, data.image)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        binding = ItemCourseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CourseViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        holder.bindViews(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    interface CourseItemClickListener {
        fun onCourseItemClicked(courseId: Int, title: String, image2: String)
    }
}
