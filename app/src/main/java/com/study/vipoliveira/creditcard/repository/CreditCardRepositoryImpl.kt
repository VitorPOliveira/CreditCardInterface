package com.study.vipoliveira.creditcard.repository

import com.study.vipoliveira.creditcard.model.PaymentMethod
import com.study.vipoliveira.creditcard.service.PaymentService
import io.reactivex.Single

class CreditCardRepositoryImpl
constructor(private val paymentService: PaymentService): CreditCardRepository {
    override fun getPayment(): Single<MutableList<PaymentMethod>> {
        return paymentService.getPaymentMethods()
    }
}

