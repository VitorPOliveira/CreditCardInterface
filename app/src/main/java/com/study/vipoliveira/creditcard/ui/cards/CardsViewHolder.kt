package com.study.vipoliveira.creditcard.ui.cards

import android.support.v7.widget.RecyclerView
import android.view.View
import com.bumptech.glide.Glide
import com.study.vipoliveira.creditcard.model.PaymentMethod
import kotlinx.android.synthetic.main.layout_items.view.*

class CardsViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(item: PaymentMethod, listener: (PaymentMethod) -> Unit) = with(itemView) {
        with(item){
            card_name.text = name
            Glide.with(itemView).load(thumbnail).into(card_image)
            card_item.setOnClickListener { listener(item) }
        }
    }
}