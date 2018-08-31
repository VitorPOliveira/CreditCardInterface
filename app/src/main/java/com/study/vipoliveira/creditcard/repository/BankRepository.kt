package com.study.vipoliveira.creditcard.repository

import com.study.vipoliveira.creditcard.model.BankInfo
import io.reactivex.Single

interface BankRepository {
    fun getBankInfo(paymentMethod: String): Single<MutableList<BankInfo>>
}