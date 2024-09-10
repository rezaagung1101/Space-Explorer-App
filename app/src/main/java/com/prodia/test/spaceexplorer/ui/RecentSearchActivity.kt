package com.prodia.test.spaceexplorer.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.prodia.test.spaceexplorer.R
import com.prodia.test.spaceexplorer.adapter.RecentSearchListAdapter
import com.prodia.test.spaceexplorer.databinding.ActivityRecentSearchBinding
import com.prodia.test.spaceexplorer.model.api.ApiConfig
import com.prodia.test.spaceexplorer.model.data.RecentSearch
import com.prodia.test.spaceexplorer.model.db.ArticleDatabase
import com.prodia.test.spaceexplorer.model.repository.ArticleRepository
import com.prodia.test.spaceexplorer.utils.Helper
import com.prodia.test.spaceexplorer.viewModel.ArticleViewModel
import com.prodia.test.spaceexplorer.viewModel.ArticleViewModelFactory

class RecentSearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecentSearchBinding
    private val articleViewModel: ArticleViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecentSearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = getString(R.string.recent_search_title)
        getViewModel()
        articleViewModel.apply{
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

    private fun getViewModel(): ArticleViewModel {
        val database = ArticleDatabase.getDatabase(this)
        val repository = ArticleRepository(ApiConfig.getApiService(this), database.articleDao())
        val viewModel: ArticleViewModel by viewModels {
            ArticleViewModelFactory(
                repository
            )
        }
        return viewModel
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_delete -> {
                articleViewModel.recentSearches.observe(this){
                    if(it!=null && it.isNotEmpty()){
                        Helper.showDialog(
                            this,
                            getString(R.string.delete_alert),
                            getString(R.string.cancel),
                            getString(R.string.delete)
                        ) {
                            articleViewModel.deleteAllRecentSearches()
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