package com.study.vipoliveira.creditcard.model

import java.io.Serializable

data class BankInfo (
    val id: String,
    val name: String,
    val thumbnail: String
) : Serializable