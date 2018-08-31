package com.study.vipoliveira.creditcard.ui.installments

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.study.vipoliveira.creditcard.repository.InstallmentsRepository
import com.study.vipoliveira.creditcard.rx.SchedulersFacade
import com.study.vipoliveira.creditcard.ui.viewentity.InstallmentsResponse
import io.reactivex.disposables.CompositeDisposable

class InstallmentsViewModel(private val installmentsRepository: InstallmentsRepository,
                            private val schedulersFacade: SchedulersFacade,
                            private val disposable: CompositeDisposable): ViewModel() {

    override fun onCleared() {
        disposable.clear()
    }

    private val installmentsResponse = MutableLiveData<InstallmentsResponse>()

    fun installmentsResponse(): MutableLiveData<InstallmentsResponse>{
        return installmentsResponse
    }

    fun getInstallments(amount: String, paymentMethod: String, issuerId: String){
        disposable.add(
                installmentsRepository
                        .getInstallments(amount, paymentMethod, issuerId)
                        .subscribeOn(schedulersFacade.io())
                        .observeOn(schedulersFacade.ui())
                        .doOnSubscribe {
                            _ -> installmentsResponse.value = InstallmentsResponse.loading()
                        }
                        .subscribe(
                                {
                                    banks -> installmentsResponse.value = InstallmentsResponse.success(banks)
                                },
                                {
                                    t -> installmentsResponse.value = InstallmentsResponse.error(t)
                                }
                        )
        )
    }
}