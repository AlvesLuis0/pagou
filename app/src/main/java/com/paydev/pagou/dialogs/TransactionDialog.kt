package com.paydev.pagou.dialogs

import android.app.AlertDialog.Builder
import android.content.Context
import android.view.LayoutInflater
import android.widget.EditText
import com.paydev.pagou.InputUtils
import com.paydev.pagou.R
import com.paydev.pagou.use_cases.AddTransactionUseCase

class TransactionDialog(context: Context?, private val personId: Long, callback: () -> Unit) : Builder(context) {
  // construindo dialog
  private val dialogView = LayoutInflater
    .from(context)
    .inflate(R.layout.activity_add_transaction, null)

  // inputs
  private val descriptionInput = dialogView
    .findViewById<EditText>(R.id.description_input)
  private val valueInput = dialogView
    .findViewById<EditText>(R.id.value_input)

  // construtor
  init {
    setTitle("Informações do valor")
    setView(dialogView)
    setNeutralButton("abater") { _, _ -> /* TODO: implementar abater */ }
    setNegativeButton("cancelar") { dialog, _ -> dialog.cancel() }
    setPositiveButton("ok") { _, _ ->
      execute()
      callback()
    }
  }

  // ao confirmar
  private fun execute() {
    AddTransactionUseCase(context)
      .execute(
        personId,
        InputUtils.getFloatOrNull(valueInput) ?: 0f,
        InputUtils.getStringOrNull(descriptionInput)
      )
  }
}