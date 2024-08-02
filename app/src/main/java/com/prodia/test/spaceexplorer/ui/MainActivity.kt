package com.prodia.test.spaceexplorer.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.prodia.test.spaceexplorer.R
import com.prodia.test.spaceexplorer.adapter.ArticleListAdapter
import com.prodia.test.spaceexplorer.databinding.ActivityMainBinding
import com.prodia.test.spaceexplorer.model.api.ApiConfig
import com.prodia.test.spaceexplorer.model.data.Article
import com.prodia.test.spaceexplorer.model.db.ArticleDatabase
import com.prodia.test.spaceexplorer.model.repository.ArticleRepository
import com.prodia.test.spaceexplorer.viewModel.ArticleViewModel
import com.prodia.test.spaceexplorer.viewModel.ArticleViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val articleViewModel: ArticleViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getViewModel()
        articleViewModel.apply {
            getListArticles()
            articles.observe(this@MainActivity){ list ->
                if (list!=null){
                    setupInformation(list)
                } else{
                    binding.tvEmptyList.alpha = 1f
                }
            }
            isLoading.observe(this@MainActivity){
                showLoading(it)
            }
            showNoInternetSnackbar.observe(this@MainActivity) { showSnackbar ->
                if (showSnackbar) {
                    Snackbar.make(
                        binding.root,
                        "No internet connection",
                        Snackbar.LENGTH_LONG
                    ).show()
                    // Reset the value to prevent showing the Snackbar repeatedly
                    this.setSnackBarValue(false)
                }
            }
        }
    }


    private fun setupInformation(list: List<Article>){
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvArticles.layoutManager = layoutManager
        binding.rvArticles.adapter = ArticleListAdapter(list)
    }


    private fun getViewModel(): ArticleViewModel {
        val database = ArticleDatabase.getDatabase(this)
        val repository = ArticleRepository(ApiConfig.getApiService(), database.articleDao())
        val viewModel: ArticleViewModel by viewModels {
            ArticleViewModelFactory(
                repository
            )
        }
        return viewModel
    }

    private fun showLoading(state: Boolean) {
        binding.progressBar.visibility = if (state) View.VISIBLE else View.GONE
    }
}