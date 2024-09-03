package com.prodia.test.spaceexplorer.presentation.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.prodia.test.spaceexplorer.databinding.ActivityArticleWebViewBinding
import com.prodia.test.spaceexplorer.domain.model.Article
import com.prodia.test.spaceexplorer.utils.Constants

class ArticleWebViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityArticleWebViewBinding
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticleWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val article = intent.getParcelableExtra<Article>(Constants.article) as Article
        binding.apply{
            val webSettings: WebSettings = webView.settings

            webView.webViewClient = WebViewClient()
            webSettings.javaScriptEnabled = true
            // set custom User-Agent
            webSettings.userAgentString = "SpaceExplorer-App/1.0 (Prodia-WebView)"
            webView.loadUrl(article.url)

//            val headers = mapOf("SpaceExplorer-App/1.0" to "Prodia")
//            webView.loadUrl(article.url, headers)
        }
    }
}