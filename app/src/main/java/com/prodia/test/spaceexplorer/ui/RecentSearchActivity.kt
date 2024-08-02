package com.prodia.test.spaceexplorer.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.prodia.test.spaceexplorer.databinding.ActivityRecentSearchBinding

class RecentSearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecentSearchBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecentSearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}