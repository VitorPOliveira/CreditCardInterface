package com.study.vipoliveira.creditcard.ui.installments

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
import android.util.Log
import com.study.vipoliveira.creditcard.model.BankInfo
import com.study.vipoliveira.creditcard.model.PayerCosts
import com.study.vipoliveira.creditcard.ui.viewentity.InstallmentsResponse
import com.study.vipoliveira.creditcard.utils.INSTALLMENTS_MESSAGE


class InstallmentsActivity : AppCompatActivity() {
    var viewModelFactory: InstallmentsViewModelFactory? = null
        @Inject set
    private val installmentsList: MutableList<PayerCosts> = mutableListOf()

    private val adapter: InstallmentsAdapter by lazy {
        InstallmentsAdapter(installmentsList){
            val returnIntent = Intent()
            returnIntent.putExtra(INSTALLMENTS_MESSAGE, it)
            setResult(Activity.RESULT_OK, returnIntent)
            finish()
        }
    }

    private var viewModel: InstallmentsViewModel? = null
    private var paymentMethod: PaymentMethod? = null
    private var bank: BankInfo? = null
    private var amount: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)
        configToolbar()
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(InstallmentsViewModel::class.java)
        viewModel!!.installmentsResponse().observe(this,
                Observer { installmentsResponse ->
                    processPayment(installmentsResponse!!)
                }
        )
        paymentMethod = intent.getSerializableExtra("card") as PaymentMethod
        bank = intent.getSerializableExtra("bank") as BankInfo
        amount = intent.getStringExtra("amount")

        configureAdapter()
        viewModel!!.getInstallments(amount!!,paymentMethod?.id!!, bank?.id!!)
    }

    private fun processPayment(installmentsResponse: InstallmentsResponse?) {
        error_msg_txt.visibility = if (installmentsResponse?.error != null) View.VISIBLE else View.GONE
        if (installmentsResponse?.error?.message != null) {
            error_msg_txt.text = installmentsResponse.error.message
        }

        retry_button.visibility = if (installmentsResponse?.status == Status.ERROR) View.VISIBLE else View.GONE
        loading_progress_bar.visibility = if (installmentsResponse?.status == Status.LOADING) View.VISIBLE else View.GONE

        if(installmentsResponse?.status == Status.SUCCESS){
            installmentsList.clear()
            installmentsList.addAll(installmentsResponse.data!!)
            adapter.notifyDataSetChanged()
        }
        retry_button.setOnClickListener { viewModel!!.getInstallments(amount!!,paymentMethod?.id!!, bank?.id!!)}
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
        title = getString(R.string.select_installments)
    }

    override fun onSupportNavigateUp(): Boolean {
        super.onBackPressed()
        return true
    }
}
