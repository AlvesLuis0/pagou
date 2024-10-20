package com.paydev.pagou.activities

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.paydev.pagou.R
import com.paydev.pagou.adapters.TransactionsListAdapter
import com.paydev.pagou.dialogs.AreYouSure
import com.paydev.pagou.dialogs.PersonDialog
import com.paydev.pagou.dialogs.TransactionDialog
import com.paydev.pagou.use_cases.ClearTransactionUseCase
import com.paydev.pagou.use_cases.GetPersonReportUseCase
import com.paydev.pagou.use_cases.SendReportUseCase

class ListTransactionsActivity : AppCompatActivity() {
    private lateinit var person: GetPersonReportUseCase.PersonReportInfo

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
        val transactionsRecyclerView: RecyclerView = findViewById(R.id.transactions_recycler_view)
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
        transactionsRecyclerView.layoutManager = LinearLayoutManager(this)
        transactionsRecyclerView.adapter = TransactionsListAdapter(person.transactions)
        { refreshInformations() }
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

    fun openUpdatePersonDialog(view: View?) {
        PersonDialog(this) {
            refreshInformations()
        }.show(person.info.id)
    }

    fun sendReport(view: View?) {
        SendReportUseCase(this).execute(person.info.id)
    }
}