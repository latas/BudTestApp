package com.bud.app.com.bud.app.domain

import com.bud.app.com.bud.app.core.formatters.AmountFormatter
import com.bud.app.com.bud.app.core.schedulers.BaseSchedulerProvider
import com.bud.app.core.data.Result
import com.bud.app.core.data.TransactionsResponse
import com.bud.app.data.Transaction
import com.bud.app.data.TransactionType
import com.bud.app.data.TransactionsRequestConfiguration
import com.bud.app.data.TransactionsResult
import com.bud.app.repositories.TransactionsRepository
import javax.inject.Inject

class GetTransactionsUseCase @Inject constructor(
    private val transactionsRepository: TransactionsRepository,
    private val schedulers: BaseSchedulerProvider,
    private val amountFormatter: AmountFormatter
) {


    fun execute(config: TransactionsRequestConfiguration): TransactionsResult {
        return transactionsRepository.transactions(config).map {
            when (it) {
                is Result.Success -> Result.Success(
                    mapToTransactions(it.data)
                )
                is Result.Loading -> it
                is Result.Error -> it
            }
        }.onErrorReturn {
            Result.Error(it)
        }.subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
    }

    private fun mapToTransactions(data: TransactionsResponse): List<Transaction> =
        data.data.transactions.flatten().map {
            Transaction(
                it.product.title,
                it.description,
                amountFormatter.format(it.amount),
                it.date,
                it.product.favicon.firstOrNull()?.url ?: String(),
                if (it.amount >= 0) TransactionType.INCOMING else TransactionType.OUTGOING
            )
        }


}


