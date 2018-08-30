package com.study.vipoliveira.creditcard.model

import com.google.gson.annotations.SerializedName

data class Installments (
        @SerializedName("payer_costs")
        val payerCosts: MutableList<PayerCosts>
)