package com.prodia.test.spaceexplorer.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.prodia.test.spaceexplorer.adapter.ArticleListAdapter
import com.prodia.test.spaceexplorer.databinding.ActivityMainBinding
import com.prodia.test.spaceexplorer.model.api.ApiConfig
import com.prodia.test.spaceexplorer.model.data.Article
import com.prodia.test.spaceexplorer.model.db.ArticleDatabase
import com.prodia.test.spaceexplorer.model.repository.ArticleRepository
import com.prodia.test.spaceexplorer.viewModel.ArticleViewModel
import com.prodia.test.spaceexplorer.viewModel.ArticleViewModelFactory

class MainActivity() : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val articleViewModel: ArticleViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getViewModel()
        setupInformation()
    }

    private fun setupInformation(){
        binding.swipeRefresh.setOnRefreshListener {
            articleViewModel.getListArticles()
            binding.swipeRefresh.isRefreshing = true
            // Use a Handler to post a delayed action
            Handler().postDelayed({
                binding.swipeRefresh.isRefreshing = false
                binding.rvArticles.smoothScrollToPosition(0)
            }, 1000)
            binding.etSearchArticle.text!!.clear()
        }
        articleViewModel.apply {
            getListArticles()
            articles.observe(this@MainActivity){ list ->
                if (list != null) {
                    setupInformation(list)
                    binding.tvEmptyList.alpha = if (list.isEmpty()) 1f else 0f
                }
            }
            filteredArticles.observe(this@MainActivity) { list ->
                if (list != null) {
                    setupInformation(list)
                    binding.tvEmptyList.alpha = if (list.isEmpty()) 1f else 0f
                }
            }
            newsSites.observe(this@MainActivity){ categories ->
                setupSpinner(categories)
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
                    this.setSnackBarValue(false)
                    if (this.articles.value == null || this.articles.value!!.isEmpty()) binding.tvEmptyList.alpha = 1f
                }
            }
        }
        binding.btnRecentSearch.setOnClickListener {
            startActivity(Intent(this, RecentSearchActivity::class.java))
        }
        binding.etSearchArticle.setOnEditorActionListener{ v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                performSearch()
                true
            } else {
                false
            }
        }
    }

    private fun setupInformation(list: List<Article>){
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvArticles.layoutManager = layoutManager
        binding.rvArticles.adapter = ArticleListAdapter(list)
    }

    private fun performSearch() {
        val query = binding.etSearchArticle.text.toString().trim()
        if (query.isNotEmpty()) {
            articleViewModel.searchArticlesByTitle(query)
            hideKeyboard()
        }
    }

    private fun setupSpinner(newsSites: List<String>){
        val categories = mutableListOf("All Categories").apply { addAll(newsSites) }
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, categories)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.btnSpinnerCategory.adapter = adapter

        binding.btnSpinnerCategory.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedCategory = parent.getItemAtPosition(position) as String
                articleViewModel.filterArticles(selectedCategory)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }
    }

    private fun hideKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
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

    private fun showLoading(state: Boolean) {
        binding.progressBar.visibility = if (state) View.VISIBLE else View.GONE
    }
}