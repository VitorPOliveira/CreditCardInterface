package com.study.vipoliveira.creditcard.repository

import com.study.vipoliveira.creditcard.model.BankInfo
import com.study.vipoliveira.creditcard.service.PaymentService
import io.reactivex.Single

class BankRepositoryImpl
constructor(private val paymentService: PaymentService): BankRepository {
    override fun getBankInfo(paymentMethod: String): Single<MutableList<BankInfo>> {
        return paymentService.getIssuerInformation(paymentMethod)
    }
}