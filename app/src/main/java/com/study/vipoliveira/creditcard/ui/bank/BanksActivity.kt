package com.study.vipoliveira.creditcard.ui.bank

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.study.vipoliveira.creditcard.R
import com.study.vipoliveira.creditcard.model.PaymentMethod

import com.study.vipoliveira.creditcard.ui.viewentity.Status
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_recycler_view.*
import kotlinx.android.synthetic.main.layout_network_state.*
import javax.inject.Inject
import android.app.Activity
import android.content.Intent
import com.study.vipoliveira.creditcard.model.BankInfo
import com.study.vipoliveira.creditcard.ui.viewentity.BankResponse
import com.study.vipoliveira.creditcard.utils.BANK_NAME


class BanksActivity : AppCompatActivity() {
    var viewModelFactory: BanksViewModelFactory? = null
        @Inject set
    private val bankList: MutableList<BankInfo> = mutableListOf()

    private val adapter: BanksAdapter by lazy {
        BanksAdapter(bankList){
            val returnIntent = Intent()
            returnIntent.putExtra(BANK_NAME, it)
            setResult(Activity.RESULT_OK, returnIntent)
            finish()
        }
    }

    private var viewModel: BanksViewModel? = null
    private var paymentMethod: PaymentMethod? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)
        configToolbar()
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(BanksViewModel::class.java)
        viewModel!!.bankResponse().observe(this,
                Observer { bankResponse ->
                    processPayment(bankResponse!!)
                }
        )
        paymentMethod = intent.getSerializableExtra("card") as PaymentMethod
        configureAdapter()
        viewModel!!.getBankInfo(paymentMethod?.id!!)
    }

    private fun processPayment(bankResponse: BankResponse?) {
        error_msg_txt.visibility = if (bankResponse?.error != null) View.VISIBLE else View.GONE
        if (bankResponse?.error?.message != null) {
            error_msg_txt.text = bankResponse.error.message
        }

        retry_button.visibility = if (bankResponse?.status == Status.ERROR) View.VISIBLE else View.GONE
        loading_progress_bar.visibility = if (bankResponse?.status == Status.LOADING) View.VISIBLE else View.GONE

        if(bankResponse?.status == Status.SUCCESS){
            bankList.clear()
            bankList.addAll(bankResponse.data!!)
            adapter.notifyDataSetChanged()
        }
        retry_button.setOnClickListener { viewModel!!.getBankInfo(paymentMethod?.id!!)}
    }

    private fun configureAdapter(){
        val linearLayoutManager = LinearLayoutManager(this)
        recyclerview.layoutManager = linearLayoutManager
        recyclerview.adapter = adapter
    }
    private fun configToolbar() {
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        title = getString(R.string.select_bank)//To change body of created functions use File | Settings | File Templates.
    }

    override fun onSupportNavigateUp(): Boolean {
        super.onBackPressed()
        return true
    }
}
