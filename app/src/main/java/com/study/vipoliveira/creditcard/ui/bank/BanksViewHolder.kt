package com.study.vipoliveira.creditcard.ui.bank

import android.support.v7.widget.RecyclerView
import android.view.View
import com.bumptech.glide.Glide
import com.study.vipoliveira.creditcard.model.BankInfo
import com.study.vipoliveira.creditcard.model.PaymentMethod
import kotlinx.android.synthetic.main.layout_items.view.*

class BanksViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(item: BankInfo, listener: (BankInfo) -> Unit) = with(itemView) {
        with(item){
            card_name.text = name
            Glide.with(itemView).load(thumbnail).into(card_image)
            card_item.setOnClickListener { listener(item) }
        }
    }
}