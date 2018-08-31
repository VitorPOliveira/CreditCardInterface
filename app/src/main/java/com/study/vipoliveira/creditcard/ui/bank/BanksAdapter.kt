package com.study.vipoliveira.creditcard.ui.bank

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.study.vipoliveira.creditcard.R
import com.study.vipoliveira.creditcard.model.BankInfo


class BanksAdapter (val items : MutableList<BankInfo>, private val listener: (BankInfo) -> Unit) : RecyclerView.Adapter<BanksViewHolder>() {
    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: BanksViewHolder, position: Int) {
        holder.bind(items[position], listener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BanksViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_items, parent, false)
        return BanksViewHolder(view)
    }
}