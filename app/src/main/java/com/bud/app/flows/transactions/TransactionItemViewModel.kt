package com.bud.app.flows.transactions

import com.bud.app.R
import com.bud.app.data.Transaction
import com.bud.app.data.TransactionType

data class TransactionItemViewModel(private val transaction: Transaction) {

    val priceColor: Int
        get() = if (transaction.type == TransactionType.INCOMING) R.color.green else R.color.red

    val imageUrl = transaction.imageUrl

    val title = transaction.description

    val price = transaction.amount

    val date = transaction.date
}