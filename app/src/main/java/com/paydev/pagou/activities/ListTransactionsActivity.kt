package com.paydev.pagou.activities

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.paydev.pagou.R
import com.paydev.pagou.use_cases.GetPersonReportUseCase

class ListTransactionsActivity : AppCompatActivity() {
    private lateinit var person: GetPersonReportUseCase.PersonReport

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_transactions)
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
        var totalDescription = ""
        if(person.info.total < 0){
            totalDescription = "${person.info.name} está te devendo"
            tvPersonTotal.setTextColor(getColor(R.color.negative_color))
        }
        else if(person.info.total > 0){
            totalDescription = "Você está devendo para ${person.info.name}"
            tvPersonTotal.setTextColor(getColor(R.color.positive_color))
        }
        else{
            totalDescription = "${person.info.name} está quitado(a)"
            tvPersonTotal.setTextColor(getColor(R.color.neutral_color))
        }
        tvPersonName.text = person.info.name
        tvPersonTotal.text = "Saldo: R$ %.2f".format(person.info.total)
        tvTotalDescription.text = totalDescription
    }
}



