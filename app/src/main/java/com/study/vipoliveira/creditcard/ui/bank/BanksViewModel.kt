package com.study.vipoliveira.creditcard.ui.bank

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.study.vipoliveira.creditcard.repository.BankRepository
import com.study.vipoliveira.creditcard.repository.CreditCardRepository
import com.study.vipoliveira.creditcard.rx.SchedulersFacade
import com.study.vipoliveira.creditcard.ui.viewentity.BankResponse
import io.reactivex.disposables.CompositeDisposable

class BanksViewModel(private val bankRepository: BankRepository,
                     private val schedulersFacade: SchedulersFacade,
                     private val disposable: CompositeDisposable): ViewModel() {

    override fun onCleared() {
        disposable.clear()
    }

    private val bankResponse = MutableLiveData<BankResponse>()

    fun bankResponse(): MutableLiveData<BankResponse>{
        return bankResponse
    }


    fun getBankInfo(paymentMethod: String){
        disposable.add(
                bankRepository
                        .getBankInfo(paymentMethod)
                        .subscribeOn(schedulersFacade.io())
                        .observeOn(schedulersFacade.ui())
                        .doOnSubscribe {
                            _ -> bankResponse.value = BankResponse.loading()
                        }
                        .subscribe(
                                {
                                    banks -> bankResponse.value = BankResponse.success(banks)
                                },
                                {
                                    t -> bankResponse.value = BankResponse.error(t)
                                }
                        )
        )
    }

}