package com.bud.app.repositories

import com.bud.app.core.api.AppService
import com.bud.app.core.extensions.toResult
import com.bud.app.core.data.Result
import com.bud.app.core.data.TransactionsResponse
import com.bud.app.core.extensions.withLoading
import com.bud.app.data.TransactionsRequestConfiguration
import com.bud.app.di.scopes.PerApplication
import io.reactivex.Observable
import javax.inject.Inject

@PerApplication
class TransactionsRepository @Inject constructor(private val service: AppService) {

    fun transactions(config: TransactionsRequestConfiguration): Observable<Result<TransactionsResponse>> =
        service.transactions(config.page, config.resultsPerPage).toResult().withLoading()

}