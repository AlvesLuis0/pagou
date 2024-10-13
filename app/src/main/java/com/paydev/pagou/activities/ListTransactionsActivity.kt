package com.paydev.pagou.activities

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.paydev.pagou.R
import com.paydev.pagou.dialogs.AreYouSure
import com.paydev.pagou.dialogs.TransactionDialog
import com.paydev.pagou.use_cases.ClearTransactionUseCase
import com.paydev.pagou.use_cases.GetPersonReportUseCase

class ListTransactionsActivity : AppCompatActivity() {
    private lateinit var person: GetPersonReportUseCase.PersonReport

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_transactions)
        refreshInformations()
    }

    private fun refreshInformations() {
        getInformations()
        setInformations()
    }

    private fun getInformations() {
        val id = intent.getLongExtra("id", 0)
        person = GetPersonReportUseCase(this).execute(id)
    }

    private fun setInformations() {
        val tvPersonName: TextView = findViewById(R.id.person_view)
        val tvPersonTotal: TextView = findViewById(R.id.total_view)
        val tvTotalDescription: TextView = findViewById(R.id.total_description_view)
        val totalDescription: String
        if(person.info.total.isNegative()){
            totalDescription = "${person.info.name} está te devendo"
            tvPersonTotal.setTextColor(getColor(R.color.negative_color))
        }
        else if(person.info.total.isPositive()){
            totalDescription = "Você está devendo para ${person.info.name}"
            tvPersonTotal.setTextColor(getColor(R.color.positive_color))
        }
        else{
            totalDescription = "${person.info.name} está quitado(a)"
            tvPersonTotal.setTextColor(getColor(R.color.neutral_color))
        }
        tvPersonName.text = person.info.name
        tvPersonTotal.text = "Saldo: R$ ${person.info.total}"
        tvTotalDescription.text = totalDescription
    }

    fun openAddTransactionDialog(view: View?) {
        TransactionDialog(this, person.info.id)
            { refreshInformations() }
            .show()
    }

    fun openClearTransactionsDialog(view: View?) {
        AreYouSure(this) {
            ClearTransactionUseCase(this).execute(person.info.id)
            refreshInformations()
        }.show()
    }
}