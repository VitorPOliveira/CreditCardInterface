package com.study.vipoliveira.creditcard.repository

import com.study.vipoliveira.creditcard.model.PayerCosts
import io.reactivex.Single

interface InstallmentsRepository {
    fun getInstallments(amount:String, paymentMethod: String, issuerId: String): Single<MutableList<PayerCosts>>

}