package com.paydev.pagou.dialogs

import android.app.Activity
import android.app.AlertDialog
import android.app.AlertDialog.BUTTON_POSITIVE
import android.app.AlertDialog.Builder
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.TextView
import com.paydev.pagou.utils.InputUtils
import com.paydev.pagou.R
import com.paydev.pagou.inputs.CurrencyInput
import com.paydev.pagou.models.Transaction
import com.paydev.pagou.use_cases.AddTransactionUseCase
import com.paydev.pagou.use_cases.InactivateTransactionUseCase
import com.paydev.pagou.use_cases.UpdateTransactionUseCase
import com.paydev.pagou.utils.StringUtils

class TransactionDialog(context: Context?, private val personId: Long, private val transaction: Transaction? = null, private val callback: () -> Unit) : Builder(context) {
  // construindo dialog
  private val dialogView = LayoutInflater
    .from(context)
    .inflate(R.layout.activity_add_transaction, null)

  // inputs
  private val descriptionInput = dialogView
    .findViewById<EditText>(R.id.description_input)
  private val valueInput = dialogView
    .findViewById<EditText>(R.id.value_input)
  private val errorsList = dialogView
    .findViewById<TextView>(R.id.transaction_errors_list)

  // construtor
  init {
    setView(dialogView)
    setNegativeButton("cancelar") { _, _ -> }
    setPositiveButton("ok") { _, _ -> }
    valueInput.filters = arrayOf(CurrencyInput())
  }

  // ao confirmar
  private fun execute(): Transaction {
    val value = InputUtils.getDoubleOrNull(valueInput) ?: 0.toDouble()
    val description = InputUtils.getStringOrNull(descriptionInput)
    if(transaction != null)
      return UpdateTransactionUseCase(context).execute(transaction.id, personId, description, value)
    return AddTransactionUseCase(context).execute(personId, value, description)
  }

  /*
    o processo de pegar o botão e atribuir um listener é necessário para que possamos controlar
    quando o dialog deve fechar ou se manter aberto
  */
  override fun show(): AlertDialog {
    val dialog = super.show()
    dialog.getButton(BUTTON_POSITIVE).apply {
      setBackgroundColor(Color.rgb(93, 167, 78)) // cor de fundo
      setTextColor(Color.rgb(237, 241, 255)) // cor do texto

      setOnClickListener {
        val transaction = execute()
        if(transaction.errors.isEmpty()) {
          dialog.dismiss()
          callback()
          return@setOnClickListener
        }
        // montando lista de erros
        errorsList.text = StringUtils.buildList(transaction.errors)
      }
    }

    dialog.getButton(AlertDialog.BUTTON_NEGATIVE).apply {
      setBackgroundColor(Color.rgb(224, 48, 27))
      setTextColor(Color.rgb(237, 241, 255))
    }
    dialog.getButton(AlertDialog.BUTTON_NEUTRAL).apply {
      setBackgroundColor(Color.rgb(40, 50, 200))
      setTextColor(Color.rgb(237, 241, 255))
    }
    return dialog
  }

  fun show(id: Long) {
    setNeutralButton("inativar") { _, _ -> AreYouSure(context) {
      InactivateTransactionUseCase(context).execute(id)
      callback()
    }.show() }
    descriptionInput.setText(transaction!!.description)
    valueInput.setText(transaction.value.toString())
    show()
  }
}