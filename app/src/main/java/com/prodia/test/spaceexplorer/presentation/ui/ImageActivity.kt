package com.prodia.test.spaceexplorer.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.prodia.test.spaceexplorer.R
import com.prodia.test.spaceexplorer.databinding.ActivityImageBinding
import com.prodia.test.spaceexplorer.utils.Constants

class ImageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityImageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityImageBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val imageUrl = intent.getStringExtra(Constants.imageUrl)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = resources.getString(R.string.fully_image_display)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        Glide.with(this)
            .load(imageUrl)
            .into(binding.ivArticle)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onNavigateUp()
    }
}