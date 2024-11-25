package com.example.aram.features.session

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.aram.R
import com.example.aram.databinding.ItemSessionBinding
import com.example.aram.model.dataClasses.SessionItem

class SessionAdapter(private val data: ArrayList<SessionItem.SessionItemItem>) :
    RecyclerView.Adapter<SessionAdapter.SessionViewHolder>() {

    inner class SessionViewHolder(private val binding: ItemSessionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bindViews(item: SessionItem.SessionItemItem) {
            binding.sessionName.text = item.session
            binding.sessionTime.text = "${item.time} دقیقه"

            // بررسی وضعیت آزاد یا قفل بودن و تعیین تصویر مناسب
            if (item.free == 1) {
                binding.sessionIcon.setImageResource(R.drawable.ic_check)
            } else {
                binding.sessionIcon.setImageResource(R.drawable.ic_lock)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SessionViewHolder {
        val binding = ItemSessionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SessionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SessionViewHolder, position: Int) {
        holder.bindViews(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
    // متد برای به‌روزرسانی داده‌ها
    fun updateData(newSessions: ArrayList<SessionItem.SessionItemItem>) {
        data.clear() // حذف داده‌های قدیمی
        data.addAll(newSessions) // افزودن داده‌های جدید
        notifyDataSetChanged() // اطلاع به Adapter برای رندر مجدد
    }
}
