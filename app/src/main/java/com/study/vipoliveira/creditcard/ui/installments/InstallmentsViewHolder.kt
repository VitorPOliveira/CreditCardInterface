package com.study.vipoliveira.creditcard.ui.installments

import android.support.v7.widget.RecyclerView
import android.view.View
import com.study.vipoliveira.creditcard.model.PayerCosts
import kotlinx.android.synthetic.main.layout_installments.view.*

class InstallmentsViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(item: PayerCosts, listener: (PayerCosts) -> Unit) = with(itemView) {
        with(item){
            installments_message.text = installmentsMessage
            card_installment.setOnClickListener { listener(item) }
        }
    }
}