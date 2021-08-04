package com.phi.httplib

import com.phi.httplib.model.BaseResult
import com.phi.httplib.model.OutAny
import com.phi.httplib.model.InfoTBean
import retrofit2.http.GET

interface ServiceApiTest {

    @GET("?service=Home.getConfig")
    suspend fun testApi(): BaseResult<List<InfoTBean>>

}