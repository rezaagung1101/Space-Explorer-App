package com.prodia.test.spaceexplorer.utils

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.prodia.test.spaceexplorer.domain.model.Article

class UserPreference(context: Context) {
    val masterKey = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()
    val sharedPreference = EncryptedSharedPreferences.create(
        context,
        "UserEncryptedPreferences",
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )
    // use the shared preferences and editor as you normally would
    val editor = sharedPreference.edit()

    fun saveArticlePreference(article: Article) {
        editor.putString("title", article.title)
        editor.putBoolean("isFeatured", article.featured)
        editor.apply()
    }

    fun getTitle(): String{
        return sharedPreference.getString("title", "defaultTitle").toString()
    }

    fun removeTitle(){
        editor.remove("uid").apply()
    }

    fun removeAll(){
        editor.clear().apply()
    }
}