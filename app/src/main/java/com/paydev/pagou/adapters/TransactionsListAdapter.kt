package com.paydev.pagou.adapters

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.paydev.pagou.R
import com.paydev.pagou.models.TransactionSummary
import com.paydev.pagou.utils.DateUtils

class TransactionsListAdapter(private val data: List<TransactionSummary>) : RecyclerView.Adapter<TransactionsListAdapter.ViewHolder>() {
  class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val tvDescription = view.findViewById<TextView>(R.id.tv_description)
    val tvRegisteredAt = view.findViewById<TextView>(R.id.tv_registered_at)
    val tvValue = view.findViewById<TextView>(R.id.tv_value)
  }

  override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
    val view = LayoutInflater.from(viewGroup.context)
      .inflate(R.layout.recycler_view_transaction, viewGroup, false)
    return ViewHolder(view)
  }

  override fun getItemCount(): Int {
    return data.size
  }

  override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
    val transaction = data.elementAt(position)

    if(transaction.description != null)
      viewHolder.tvDescription.text = transaction.description
    else
      viewHolder.tvDescription.text = "(Sem descrição)"

    viewHolder.tvRegisteredAt.text = "Registrado em ${DateUtils.format(transaction.registeredAt)}"
    viewHolder.tvValue.apply {
      text = "R$ ${transaction.value}"
      val color =
        if (transaction.value.isPositive()) R.color.positive_color
        else R.color.negative_color
      setTextColor(resources.getColor(color, null))
    }
    if(!transaction.isActive)
      listOf(viewHolder.tvDescription, viewHolder.tvRegisteredAt, viewHolder.tvValue)
        .forEach { item -> item.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG }
  }
}