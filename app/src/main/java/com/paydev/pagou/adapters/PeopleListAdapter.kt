package com.paydev.pagou.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.paydev.pagou.R
import com.paydev.pagou.activities.ListTransactionsActivity
import com.paydev.pagou.models.PersonReport
import com.paydev.pagou.utils.DateUtils

class PeopleListAdapter(private val personList: List <PersonReport>) : RecyclerView.Adapter<PeopleListAdapter.ViewHolder>() {

    // Criando nova classe ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Criando uma nova view
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_view_person, parent, false)

        return ViewHolder(view)
    }

    // Mesclar os itens da lista com o View
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        val person = personList.elementAt(position)
        // Atribuir o texto para o textview a partir da classe itemHolder
        viewHolder.tvPersonName.text = person.name

        if(person.lastPayment != null) {
            viewHolder.tvLastPayment.text = "Último pagamento\nem ${DateUtils.format(person.lastPayment!!)}"
        }
        else {
            viewHolder.tvLastPayment.text = "(Nenhum pagamento feito)"
        }
        viewHolder.tvTotal.apply {

            text = "R$ ${person.total}"
            val color =
                if (person.total.isPositive()) R.color.positive_color
                else if (person.total.isNegative()) R.color.negative_color
                else R.color.neutral_color
            setTextColor(resources.getColor(color, null))

            if (person.total.isZero()) {
                viewHolder.tvLastPayment.text = "Está quitado"
            }
        }

        // ao clicar no card, abre a tela de transações
        viewHolder.itemView.apply {
            setOnClickListener {
                val intent = Intent(context, ListTransactionsActivity::class.java)
                intent.putExtra("id", person.id)
                context.startActivity(intent)
            }
        }

    }
    // retornar a quantidade de itens na lista
    override fun getItemCount(): Int {
        return personList.size
    }

    // mesclando os itens da lista com o ID
    class ViewHolder(personView: View) : RecyclerView.ViewHolder(personView) {
        val tvPersonName: TextView = personView.findViewById(R.id.tv_person_name)
        val tvLastPayment: TextView = personView.findViewById(R.id.tv_last_payment)
        val tvTotal: TextView = personView.findViewById(R.id.tv_total)
    }
}
