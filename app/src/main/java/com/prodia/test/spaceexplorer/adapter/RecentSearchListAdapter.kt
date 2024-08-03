package com.prodia.test.spaceexplorer.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.prodia.test.spaceexplorer.databinding.RecentSearchItemBinding
import com.prodia.test.spaceexplorer.model.data.RecentSearch


class RecentSearchListAdapter(private val listData: List<RecentSearch>) :
    RecyclerView.Adapter<RecentSearchListAdapter.ViewHolder>() {
    class ViewHolder(var binding: RecentSearchItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            RecentSearchItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        fun bind(data: RecentSearch) {
            with(holder.itemView) {
                holder.binding.apply {
                    tvRecentSearch.text = data.query
                }
            }

        }

        bind(listData[position])
    }


    override fun getItemCount() = listData.size

}