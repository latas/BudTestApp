package com.bud.app.flows.transactions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.bud.app.com.bud.app.core.extensions.mapSuccess
import com.bud.app.com.bud.app.domain.GetTransactionsUseCase
import com.bud.app.core.data.Result
import com.bud.app.data.Transaction
import com.bud.app.data.TransactionsRequestConfiguration
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class TransactionsViewModel @Inject constructor(private val transactionsUseCase: GetTransactionsUseCase) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    val retryTriggered: PublishSubject<TransactionsRequestConfiguration> =
        PublishSubject.create<TransactionsRequestConfiguration>()

    val viewLoadTriggered: PublishSubject<TransactionsRequestConfiguration> =
        PublishSubject.create<TransactionsRequestConfiguration>()

    private val transactions = MutableLiveData<Result<List<Transaction>>>()

    val showLoader: LiveData<Boolean> = Transformations.map(transactions) {
        it?.isLoading ?: false
    }

    val errorUiWidgetVisible: LiveData<Boolean> = Transformations.map(transactions) {
        it?.isError ?: false
    }


    val transactionItems: LiveData<List<TransactionItemViewModel>> = transactions.mapSuccess {
        it.map { transaction ->
            TransactionItemViewModel(
                transaction
            )
        }
    }


    init {
        viewLoadTriggered.distinct().mergeWith(retryTriggered).switchMap {
            transactionsUseCase.execute(it)
        }.subscribe {
            transactions.value = it
        }.addTo(compositeDisposable)

    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}