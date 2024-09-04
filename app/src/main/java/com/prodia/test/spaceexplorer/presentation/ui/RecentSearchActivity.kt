package com.prodia.test.spaceexplorer.presentation.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.prodia.test.spaceexplorer.R
import com.prodia.test.spaceexplorer.databinding.ActivityRecentSearchBinding
import com.prodia.test.spaceexplorer.presentation.adapter.RecentSearchListAdapter
import com.prodia.test.spaceexplorer.presentation.viewmodel.RecentSearchViewModel
import com.prodia.test.spaceexplorer.utils.Helper
import com.spaceexplorer.domain.model.RecentSearch
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecentSearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecentSearchBinding
    private val recentSearchViewModel: RecentSearchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecentSearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = getString(R.string.recent_search_title)
        recentSearchViewModel.apply{
            recentSearches.observe(this@RecentSearchActivity){ recentSearches ->
                if (recentSearches != null) {
                    setupInformation(recentSearches)
                    binding.tvEmptyList.alpha = if (recentSearches.isEmpty()) 1f else 0f
                    binding.tvPageTitle.alpha = if (recentSearches.isEmpty()) 0f else 1f
                }
            }
        }
        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setupInformation(recentSearches : List<RecentSearch>){
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvArticles.layoutManager = layoutManager
        binding.rvArticles.adapter = RecentSearchListAdapter(recentSearches)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_delete -> {
                recentSearchViewModel.recentSearches.observe(this){
                    if(it!=null && it.isNotEmpty()){
                        Helper.showDialog(
                            this,
                            getString(R.string.delete_alert),
                            getString(R.string.cancel),
                            getString(R.string.delete)
                        ) {
                            recentSearchViewModel.deleteAllRecentSearches()
                            Toast.makeText(
                                this@RecentSearchActivity,
                                resources.getString(R.string.delete_success_message),
                                Toast.LENGTH_SHORT
                            ).show()

                        }
                    }else{
                        Toast.makeText(
                            this@RecentSearchActivity,
                            resources.getString(R.string.empty_recent_search),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

}