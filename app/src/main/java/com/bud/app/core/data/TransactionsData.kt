package com.bud.app.core.data


data class TransactionsResponse(val data: TransactionsData)

data class TransactionsData(val transactions: List<List<TransactionData>>)

data class TransactionData(
    val amount: Double,
    val date: String,
    val description: String,
    val id: String,
    val product: ProductData
)

data class ProductData(val favicon: List<IconData>, val title: String)

data class IconData(val url: String)