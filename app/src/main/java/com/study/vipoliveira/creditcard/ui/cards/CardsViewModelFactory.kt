package com.study.vipoliveira.creditcard.ui.cards

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.study.vipoliveira.creditcard.repository.CreditCardRepository
import com.study.vipoliveira.creditcard.rx.SchedulersFacade
import io.reactivex.disposables.CompositeDisposable

class CardsViewModelFactory (private val creditCardRepository: CreditCardRepository,
                             private val schedulersFacade: SchedulersFacade,
                             private val disposable: CompositeDisposable): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(CardsViewModel::class.java)) {
            return CardsViewModel(creditCardRepository, schedulersFacade, disposable) as T
        }
        throw IllegalArgumentException("Unkown ViewModel class")
    }

}