package com.study.vipoliveira.creditcard.model

import com.google.gson.annotations.SerializedName

data class PaymentMethod (
        val name: String,

        @SerializedName("payment_type_id")
        val paymentType: String,

        val thumbnail: String
)
