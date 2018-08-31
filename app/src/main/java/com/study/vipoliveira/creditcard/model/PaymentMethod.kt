package com.study.vipoliveira.creditcard.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PaymentMethod (
        val id: String,
        val name: String,

        @SerializedName("payment_type_id")
        val paymentType: String,

        val thumbnail: String
) : Serializable