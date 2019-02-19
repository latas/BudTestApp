package com.bud.app.flows.transactions

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bud.app.R
import com.bud.app.core.viewmodel.AppViewModelFactory
import com.bud.app.data.TransactionsRequestConfiguration
import com.bud.app.databinding.FragmentTransactionsBinding
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class TransactionsFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: AppViewModelFactory

    private val transactionsRequestConfiguration = TransactionsRequestConfiguration(1, 1000)

    private val transactionsAdapter by lazy {
        TransactionsAdapter()
    }

    private val viewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(TransactionsViewModel::class.java)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding: FragmentTransactionsBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_transactions, container, false
        )
        binding.let {
            it.viewModel = viewModel
            it.lifecycleOwner = this
        }

        binding.btnRetry.setOnClickListener {
            viewModel.retryTriggered.onNext(transactionsRequestConfiguration)
        }

        setUpRecyclerView(binding.rvTransactions)

        return binding.root
    }


    private fun setUpRecyclerView(rvTransactions: RecyclerView) {
        rvTransactions.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rvTransactions.adapter = transactionsAdapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.transactionItems.observe(viewLifecycleOwner, transactionsAdapter)
        viewModel.viewLoadTriggered.onNext(transactionsRequestConfiguration)
    }
}