package com.study.vipoliveira.creditcard.ui.viewentity

import com.study.vipoliveira.creditcard.model.BankInfo

class BankResponse private constructor(val status: Status,
                                       val data: MutableList<BankInfo>?,
                                       val error: Throwable?){
    companion object {
        fun loading(): BankResponse {
            return BankResponse(Status.LOADING, null, null)
        }

        fun success(data: MutableList<BankInfo>): BankResponse {
            return BankResponse(Status.SUCCESS, data, null)
        }

        fun error(error: Throwable): BankResponse {
            return BankResponse(Status.ERROR, null, error)
        }
    }
}