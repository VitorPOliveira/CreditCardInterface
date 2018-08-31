package com.study.vipoliveira.creditcard.ui.viewentity

import com.study.vipoliveira.creditcard.model.Installments
import com.study.vipoliveira.creditcard.model.PayerCosts

class InstallmentsResponse private constructor(val status: Status,
                                               val data: MutableList<PayerCosts>?,
                                               val error: Throwable?){
    companion object {
        fun loading(): InstallmentsResponse {
            return InstallmentsResponse(Status.LOADING, null, null)
        }

        fun success(data: MutableList<PayerCosts>): InstallmentsResponse {
            return InstallmentsResponse(Status.SUCCESS, data, null)
        }

        fun error(error: Throwable): InstallmentsResponse {
            return InstallmentsResponse(Status.ERROR, null, error)
        }
    }
}