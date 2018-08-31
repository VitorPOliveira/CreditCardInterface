package com.study.vipoliveira.creditcard.ui.bank

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.study.vipoliveira.creditcard.repository.BankRepository
import com.study.vipoliveira.creditcard.repository.CreditCardRepository
import com.study.vipoliveira.creditcard.rx.SchedulersFacade
import io.reactivex.disposables.CompositeDisposable

class BanksViewModelFactory (private val bankRepository: BankRepository,
                             private val schedulersFacade: SchedulersFacade,
                             private val disposable: CompositeDisposable): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(BanksViewModel::class.java)) {
            return BanksViewModel(bankRepository, schedulersFacade, disposable) as T
        }
        throw IllegalArgumentException("Unkown ViewModel class")
    }

}