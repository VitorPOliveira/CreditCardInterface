package com.study.vipoliveira.creditcard.ui.installments

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.study.vipoliveira.creditcard.repository.CreditCardRepository
import com.study.vipoliveira.creditcard.repository.InstallmentsRepository
import com.study.vipoliveira.creditcard.rx.SchedulersFacade
import io.reactivex.disposables.CompositeDisposable

class InstallmentsViewModelFactory (private val installmentsRepository: InstallmentsRepository,
                                    private val schedulersFacade: SchedulersFacade,
                                    private val disposable: CompositeDisposable): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(InstallmentsViewModel::class.java)) {
            return InstallmentsViewModel(installmentsRepository, schedulersFacade, disposable) as T
        }
        throw IllegalArgumentException("Unkown ViewModel class")
    }

}