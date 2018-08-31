package com.study.vipoliveira.creditcard.ui

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.TextView
import com.study.vipoliveira.creditcard.R
import com.study.vipoliveira.creditcard.model.BankInfo
import com.study.vipoliveira.creditcard.model.PaymentMethod
import com.study.vipoliveira.creditcard.ui.bank.BanksActivity
import com.study.vipoliveira.creditcard.ui.cards.CardsActivity
import com.study.vipoliveira.creditcard.ui.installments.InstallmentsActivity
import com.study.vipoliveira.creditcard.utils.*
import kotlinx.android.synthetic.main.activity_home.*
import android.text.Editable
import android.text.TextWatcher
import com.bumptech.glide.Glide
import com.study.vipoliveira.creditcard.model.PayerCosts


class HomeActivity : AppCompatActivity() {

    private var currentCard: PaymentMethod? = null
    private var currentBank: BankInfo? = null
    private var currentInstallment: PayerCosts? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        init()
    }

    private fun init(){
        card_select.setOnClickListener{if(!amount_text.text.isEmpty())openCardsActivity() else showSnackbar()}
        bank_select.setOnClickListener{if(!amount_text.text.isEmpty())openBanksActivity() else showSnackbar()}
        installments_select.setOnClickListener{if(!amount_text.text.isEmpty())openInstallmentsActivity() else showSnackbar()}
        amount_text.addTextChangedListener(object : TextWatcher {

            var ignoreChange: Boolean = false
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (!ignoreChange) {
                    var string: String = s.toString();
                    string = string.replace(".", "");
                    string = string.replace(" ", "");
                    when {
                        string.length == 1 -> string = ".  $string"
                        string.length == 2 -> string = ".$string"
                        string.length > 2 -> string = string.substring(0, string.length - 2) + "." + string.substring(string.length - 2, string.length)
                    }
                    ignoreChange = true
                    amount_text.setText(string)
                    amount_text.setSelection(amount_text.text.length)
                    ignoreChange = false
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
        reset_button.setOnClickListener{resetData()}
    }

    private fun openCardsActivity(){
        val intent = Intent(this, CardsActivity::class.java)
        startActivityForResult(intent, REQUEST_CARDS)
    }

    private fun openBanksActivity(){
        val intent = Intent(this, BanksActivity::class.java)
        intent.putExtra("card", currentCard)
        startActivityForResult(intent, REQUEST_BANKS)
    }

    private fun openInstallmentsActivity(){
        val intent = Intent(this, InstallmentsActivity::class.java)
        intent.putExtra("card", currentCard)
        intent.putExtra("bank", currentBank)
        intent.putExtra("amount", amount_text.text.toString())
        startActivityForResult(intent, REQUEST_INSTALLMENTS)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            REQUEST_CARDS -> if (resultCode == Activity.RESULT_OK) {
                currentCard = data?.getSerializableExtra(CARD_NAME) as PaymentMethod?
                card_select.text = currentCard?.name
                card_select_image.visibility = View.VISIBLE
                Glide.with(this).load(currentCard?.thumbnail).into(card_select_image)
                bank_select.visibility = View.VISIBLE
                }
            REQUEST_BANKS -> if (resultCode == Activity.RESULT_OK) {
                currentBank = data?.getSerializableExtra(BANK_NAME) as BankInfo?
                bank_select.text = currentBank?.name
                bank_select_image.visibility = View.VISIBLE
                Glide.with(this).load(currentBank?.thumbnail).into(bank_select_image)
                installments_select.visibility = View.VISIBLE
            }
            REQUEST_INSTALLMENTS -> if (resultCode == Activity.RESULT_OK) {
                currentInstallment = data?.getSerializableExtra(INSTALLMENTS_MESSAGE) as PayerCosts?
                installments_select.text = currentInstallment?.installmentsMessage
            }
        }

    }

    private fun resetData(){
        amount_text.setText("")
        card_select.text = getString(R.string.select_card)
        card_select_image.visibility = View.GONE
        bank_select.visibility = View.GONE
        bank_select_image.visibility = View.GONE
        bank_select.text = getString(R.string.select_bank)
        installments_select.visibility = View.GONE
        installments_select.text =getString(R.string.select_installments)
    }


    private fun showSnackbar() {
        val snackbar = Snackbar.make(findViewById(R.id.amount_text), getString(R.string.price_alert), Snackbar.LENGTH_LONG)
        val snackbarView = snackbar.view
        val textView = snackbarView.findViewById(android.support.design.R.id.snackbar_text) as TextView
        textView.setTextColor(ContextCompat.getColor(this, R.color.redColor))
        snackbar.show()
    }
}


