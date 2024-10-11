package com.paydev.pagou.dialogs

import android.app.AlertDialog
import android.app.AlertDialog.BUTTON_POSITIVE
import android.app.AlertDialog.Builder
import android.content.Context
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.TextView
import com.paydev.pagou.utils.InputUtils
import com.paydev.pagou.R
import com.paydev.pagou.models.Transaction
import com.paydev.pagou.use_cases.AddTransactionUseCase
import com.paydev.pagou.utils.StringUtils

class TransactionDialog(context: Context?, private val personId: Long, private val callback: () -> Unit) : Builder(context) {
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
    .findViewById<TextView>(R.id.errors_list)

  // construtor
  init {
    setTitle("Informações do valor")
    setView(dialogView)
    setNeutralButton("abater") { _, _ -> }
    setNegativeButton("cancelar") { _, _ -> }
    setPositiveButton("ok") { _, _ -> }
  }

  // ao confirmar
  private fun execute(): Transaction {
    return AddTransactionUseCase(context)
      .execute(
        personId,
        InputUtils.getFloatOrNull(valueInput) ?: 0f,
        InputUtils.getStringOrNull(descriptionInput)
      )
  }

  /*
    o processo de pegar o botão e atribuir um listener é necessário para que possamos controlar
    quando o dialog deve fechar ou se manter aberto
  */
  override fun show(): AlertDialog {
    val dialog = super.show()
    dialog
      .getButton(BUTTON_POSITIVE)
      .setOnClickListener {
        val transaction = execute()
        if(transaction.errors.isEmpty()) {
          dialog.dismiss()
          callback()
          return@setOnClickListener
        }
        // montando lista de erros
        errorsList.text = StringUtils.buildList(transaction.errors)
      }
    return dialog
  }
}