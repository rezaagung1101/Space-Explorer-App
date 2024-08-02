package com.prodia.test.spaceexplorer.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.prodia.test.spaceexplorer.databinding.ActivityDetailArticleBinding

class DetailArticleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailArticleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}