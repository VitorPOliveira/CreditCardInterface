package com.study.vipoliveira.creditcard.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PayerCosts (
        @SerializedName("recommended_message")
        val installmentsMessage: String
) :Serializable