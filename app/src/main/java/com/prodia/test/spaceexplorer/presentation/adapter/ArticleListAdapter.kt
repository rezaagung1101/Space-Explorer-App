package com.prodia.test.spaceexplorer.presentation.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.prodia.test.spaceexplorer.databinding.CardArticleItemBinding
import com.prodia.test.spaceexplorer.domain.model.Article
import com.prodia.test.spaceexplorer.presentation.ui.ArticleWebViewActivity
import com.prodia.test.spaceexplorer.utils.Constants


class ArticleListAdapter(private val listData: List<Article>) :
    RecyclerView.Adapter<ArticleListAdapter.ViewHolder>() {
    class ViewHolder(var binding: CardArticleItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            CardArticleItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        fun bind(data: Article) {
            with(holder.itemView) {
                holder.binding.apply {
                    with(data) {
                        Glide.with(context)
                            .load(image_url)
                            .into(ivArticle)
                        tvArticleTitle.text = title
                        tvArticleDescription.text = summary

                    }
                }
                this.setOnClickListener {
                    val intent = Intent(context, ArticleWebViewActivity::class.java)
                    intent.putExtra(Constants.article, data)
                    context.startActivity(intent)
                }
            }

        }

        bind(listData[position])
    }


    override fun getItemCount() = listData.size

}