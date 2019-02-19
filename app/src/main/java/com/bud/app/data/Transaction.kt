package com.bud.app.data

import com.bud.app.core.data.Result
import io.reactivex.Observable

data class Transaction(
    val description: String,
    val amount: String,
    val date: String,
    val imageUrl: String,
    val type: TransactionType
)

enum class TransactionType {
    INCOMING, OUTGOING
}

typealias TransactionsResult = Observable<Result<List<Transaction>>>