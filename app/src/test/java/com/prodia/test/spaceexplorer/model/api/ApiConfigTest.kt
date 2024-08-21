package com.prodia.test.spaceexplorer.model.api

import com.prodia.test.spaceexplorer.data.source.remote.api.ApiConfig
import org.junit.Assert.assertNotNull
import org.junit.Test

class ApiConfigTest {

    @Test
    fun `test getApiService returns not null`() {
        val apiService = ApiConfig.getApiService()
        assertNotNull(apiService)
    }

}