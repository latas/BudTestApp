package com.bud.app.core.api

import com.bud.app.core.data.TransactionsData
import com.bud.app.core.data.TransactionsResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface AppService {
    @GET("transactions")
    fun transactions(@Query("page") page: Int, @Query("per_page") resultsPerPage: Int): Single<TransactionsResponse>
}