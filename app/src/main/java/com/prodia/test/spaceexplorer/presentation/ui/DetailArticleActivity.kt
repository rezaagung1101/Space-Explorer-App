package com.prodia.test.spaceexplorer.presentation.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.prodia.test.spaceexplorer.R
import com.prodia.test.spaceexplorer.databinding.ActivityDetailArticleBinding
import com.prodia.test.spaceexplorer.utils.Constants
import com.prodia.test.spaceexplorer.utils.Helper
import com.spaceexplorer.domain.model.Article

class DetailArticleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailArticleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val article = intent.getParcelableExtra<Article>(Constants.article) as Article
        setupInformation(article)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = getString(R.string.detail_article)
    }

    private fun setupInformation(article: Article) {
        binding.apply {
            Glide.with(this@DetailArticleActivity)
                .load(article.image_url)
                .into(ivArticle)
            tvNewsSite.text = "©  " + article.news_site
            tvArticleTitle.text = article.title
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            tvPublishedTime.text = Helper.formatPublishedAt(article.published_at)
//            tvSummary.text = Helper.extractSummary(article.summary)
            tvSummary.text = article.summary
            btnBack.setOnClickListener {
                onBackPressed()
            }
            ivArticle.setOnClickListener {
                val intent = Intent(this@DetailArticleActivity, ImageActivity::class.java)
                intent.putExtra(Constants.imageUrl, article.image_url)
                startActivity(intent)
            }
        }
    }

}