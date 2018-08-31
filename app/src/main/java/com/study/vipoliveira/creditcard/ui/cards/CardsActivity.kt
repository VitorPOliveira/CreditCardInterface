package com.study.vipoliveira.creditcard.ui.cards

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.study.vipoliveira.creditcard.R
import com.study.vipoliveira.creditcard.model.PaymentMethod

import com.study.vipoliveira.creditcard.ui.viewentity.PaymentResponse
import com.study.vipoliveira.creditcard.ui.viewentity.Status
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_recycler_view.*
import kotlinx.android.synthetic.main.layout_network_state.*
import javax.inject.Inject
import android.app.Activity
import android.content.Intent
import com.study.vipoliveira.creditcard.utils.CARD_NAME


class CardsActivity : AppCompatActivity() {
    var viewModelFactory: CardsViewModelFactory? = null
        @Inject set
    private val cardsList: MutableList<PaymentMethod> = mutableListOf()

    private val adapter: CardsAdapter by lazy {
        CardsAdapter(cardsList){
            val returnIntent = Intent()
            returnIntent.putExtra(CARD_NAME, it)
            setResult(Activity.RESULT_OK, returnIntent)
            finish()
        }
    }

    private var viewModel: CardsViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)
        configToolbar()
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(CardsViewModel::class.java)
        viewModel!!.paymentResponse().observe(this,
                Observer { paymentResponse ->
                    processPayment(paymentResponse!!)
                }
        )
        configureAdapter()
        viewModel!!.getPaymentMethod()
    }

    private fun configToolbar() {
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        title = getString(R.string.select_card)//To change body of created functions use File | Settings | File Templates.
    }

    private fun processPayment(paymentResponse: PaymentResponse?) {
        error_msg_txt.visibility = if (paymentResponse?.error != null) View.VISIBLE else View.GONE
        if (paymentResponse?.error?.message != null) {
            error_msg_txt.text = paymentResponse.error.message
        }

        retry_button.visibility = if (paymentResponse?.status == Status.ERROR) View.VISIBLE else View.GONE
        loading_progress_bar.visibility = if (paymentResponse?.status == Status.LOADING) View.VISIBLE else View.GONE

        if(paymentResponse?.status == Status.SUCCESS){
            cardsList.clear()
            cardsList.addAll(paymentResponse.data!!)
            adapter.notifyDataSetChanged()
        }
        retry_button.setOnClickListener { viewModel!!.getPaymentMethod()}
    }

    private fun configureAdapter(){
        val linearLayoutManager = LinearLayoutManager(this)
        recyclerview.layoutManager = linearLayoutManager
        recyclerview.adapter = adapter
    }

    override fun onSupportNavigateUp(): Boolean {
        super.onBackPressed()
        return true
    }
}
