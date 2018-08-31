package com.study.vipoliveira.creditcard.ui.viewentity

import com.study.vipoliveira.creditcard.model.PaymentMethod

class PaymentResponse private constructor(val status: Status,
                                          val data: MutableList<PaymentMethod>?,
                                          val error: Throwable?){
    companion object {
        fun loading(): PaymentResponse {
            return PaymentResponse(Status.LOADING, null, null)
        }

        fun success(data: MutableList<PaymentMethod>): PaymentResponse {
            return PaymentResponse(Status.SUCCESS, data, null)
        }

        fun error(error: Throwable): PaymentResponse {
            return PaymentResponse(Status.ERROR, null, error)
        }
    }
}