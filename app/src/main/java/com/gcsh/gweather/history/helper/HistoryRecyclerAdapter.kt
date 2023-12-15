package com.gcsh.gweather.history.helper

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gcsh.gweather.common.WeatherItem
import com.gcsh.gweather.databinding.HistoryItemLayoutBinding

class HistoryRecyclerAdapter(private val weatherList: List<WeatherItem>) : RecyclerView.Adapter<HistoryRecyclerAdapter.HistoryViewHolder>() {
    class HistoryViewHolder(
        private val binding: HistoryItemLayoutBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun from(
                parent: ViewGroup
            ): HistoryViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = HistoryItemLayoutBinding.inflate(layoutInflater, parent, false)

                return HistoryViewHolder(binding)
            }
        }

        fun setDataBinding(data: WeatherItem) {
            binding.content = data
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val holder: RecyclerView.ViewHolder
        holder = HistoryViewHolder.from(parent)
        return holder
    }

    override fun getItemCount(): Int {
        return weatherList.size
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val list = weatherList[position]
        holder.setDataBinding(list)
    }
}