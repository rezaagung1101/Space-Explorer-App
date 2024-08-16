package com.prodia.test.spaceexplorer.model.api

import org.junit.Assert.assertNotNull
import org.junit.Test

class ApiConfigTest {

    @Test
    fun `test getApiService returns not null`() {
        val apiService = ApiConfig.getApiService()
        assertNotNull(apiService)
    }

}