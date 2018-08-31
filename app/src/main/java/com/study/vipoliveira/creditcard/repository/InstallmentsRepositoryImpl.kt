package com.study.vipoliveira.creditcard.repository

import com.study.vipoliveira.creditcard.model.PayerCosts
import com.study.vipoliveira.creditcard.service.PaymentService
import io.reactivex.Single

class InstallmentsRepositoryImpl
constructor(private val paymentService: PaymentService) : InstallmentsRepository{
    override fun getInstallments(amount:String, paymentMethod: String, issuerId: String): Single<MutableList<PayerCosts>> {
        return paymentService.getInstallments(amount, paymentMethod, issuerId).map { it[0].payerCosts }
    }
}