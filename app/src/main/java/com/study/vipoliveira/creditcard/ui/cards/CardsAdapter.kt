package com.study.vipoliveira.creditcard.ui.cards

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.study.vipoliveira.creditcard.R
import com.study.vipoliveira.creditcard.model.PaymentMethod


class CardsAdapter (val items : MutableList<PaymentMethod>, private val listener: (PaymentMethod) -> Unit) : RecyclerView.Adapter<CardsViewHolder>() {
    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: CardsViewHolder, position: Int) {
        holder.bind(items[position], listener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_items, parent, false)
        return CardsViewHolder(view)
    }
}