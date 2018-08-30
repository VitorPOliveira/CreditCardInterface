package com.study.vipoliveira.creditcard.service

import com.study.vipoliveira.creditcard.model.BankInfo
import com.study.vipoliveira.creditcard.model.Installments
import com.study.vipoliveira.creditcard.model.PaymentMethod
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface PaymentService {
    @GET("v1/payment_methods/")
    fun getPaymentMethods(@Query("public_key") publicKey: String): Single<MutableList<PaymentMethod>>

    @GET("v1/payment_methods/")
    fun getIssuerInformation(@Query("public_key") publicKey: String, @Query("payment_method_id") paymentMethod: String): Single<MutableList<BankInfo>>

    @GET("v1/payment_methods/")
    fun getInstallments(@Query("public_key") publicKey: String, @Query("amount") amount: String, @Query("payment_method_id") paymentMethod: String, @Query("issuer.id") issuerId: String): Single<Installments>
}