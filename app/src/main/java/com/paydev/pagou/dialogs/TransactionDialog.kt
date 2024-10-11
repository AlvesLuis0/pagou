package com.paydev.pagou.dialogs

import android.content.Context
import android.view.LayoutInflater
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.paydev.pagou.InputUtils
import com.paydev.pagou.R
import com.paydev.pagou.use_cases.AddTransactionUseCase

class TransactionDialog(private val context: Context, private val personId: Long, callback: () -> Unit) {
  // construindo dialog
  private val dialogView = LayoutInflater
    .from(context)
    .inflate(R.layout.activity_add_transaction, null)
  private val dialog = AlertDialog
    .Builder(context)
    .setView(dialogView)
    .setTitle("Informações do valor")
    .setNeutralButton("Abater") { dialog , _ -> dialog.cancel() } // TODO: Implementar função de abater mais tarde
    .setNegativeButton("Cancelar") { dialog , _ -> dialog.cancel() }
    .setPositiveButton("Adicionar") { _, _ ->
      execute();
      callback()
    }
    .create()

  // inputs
  private val descriptionInput = dialogView
    .findViewById<EditText>(R.id.description_input)
  private val valueInput = dialogView
    .findViewById<EditText>(R.id.value_input)

  // chama função ao apertar em OK
  private fun execute() {
    AddTransactionUseCase(context)
      .execute(
        personId,
        InputUtils.getFloatOrNull(valueInput) ?: 0f,
        InputUtils.getStringOrNull(descriptionInput)
      )
  }

  fun show() {
    dialog.show()
  }
}