package com.study.vipoliveira.creditcard.ui.installments

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.study.vipoliveira.creditcard.R
import com.study.vipoliveira.creditcard.model.PayerCosts
import com.study.vipoliveira.creditcard.model.PaymentMethod


class InstallmentsAdapter (val items : MutableList<PayerCosts>, private val listener: (PayerCosts) -> Unit) : RecyclerView.Adapter<InstallmentsViewHolder>() {
    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: InstallmentsViewHolder, position: Int) {
        holder.bind(items[position], listener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InstallmentsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_installments, parent, false)
        return InstallmentsViewHolder(view)
    }
}