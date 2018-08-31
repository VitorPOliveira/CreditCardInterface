package com.study.vipoliveira.creditcard.ui.cards

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.study.vipoliveira.creditcard.repository.CreditCardRepository
import com.study.vipoliveira.creditcard.rx.SchedulersFacade
import com.study.vipoliveira.creditcard.ui.viewentity.PaymentResponse
import io.reactivex.disposables.CompositeDisposable

class CardsViewModel(private val creditCardRepository: CreditCardRepository,
                     private val schedulersFacade: SchedulersFacade,
                     private val disposable: CompositeDisposable): ViewModel() {

    override fun onCleared() {
        disposable.clear()
    }

    private val paymentResponse = MutableLiveData<PaymentResponse>()

    fun paymentResponse(): MutableLiveData<PaymentResponse>{
        return paymentResponse
    }


    fun getPaymentMethod(){
        disposable.add(
            creditCardRepository.getPayment()
                    .subscribeOn(schedulersFacade.io())
                    .observeOn(schedulersFacade.ui())
                    .doOnSubscribe {
                        _ -> paymentResponse.value = PaymentResponse.loading()
                    }
                    .subscribe(
                            {
                                payments -> paymentResponse.value = PaymentResponse.success(payments)
                            },
                            {
                                t -> paymentResponse.value = PaymentResponse.error(t)
                            }
                    )
        )
    }
}