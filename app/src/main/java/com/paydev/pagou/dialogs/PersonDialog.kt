package com.paydev.pagou.dialogs

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.paydev.pagou.utils.InputUtils
import com.paydev.pagou.R
import com.paydev.pagou.activities.ListTransactionsActivity
import com.paydev.pagou.use_cases.CreatePersonUseCase

class PersonDialog(private val context: Context) {
  // construindo dialog
  private val dialogView = LayoutInflater
    .from(context)
    .inflate(R.layout.activity_add_person, null)
  private val dialog = AlertDialog
    .Builder(context)
    .setView(dialogView)
    .setNegativeButton("Cancelar") { dialog , _ -> dialog.cancel() }
    .setPositiveButton("Adicionar") { _, _ -> execute() }
    .create()

  // inputs
  private val nameInput = dialogView
    .findViewById<EditText>(R.id.name_input)
  private val contactInput = dialogView
    .findViewById<EditText>(R.id.contact_input)
  private val othersInput = dialogView
    .findViewById<EditText>(R.id.others_input)

  // chama função ao apertar em OK
  private fun execute() {
    // criando pessoa
    val person = CreatePersonUseCase(context)
      .execute(
        InputUtils.getString(nameInput),
        InputUtils.getString(contactInput),
        InputUtils.getStringOrNull(othersInput)
      )
    // mostra mensagem de sucesso
    Toast
      .makeText(context, "Pessoa adicionada com sucesso", Toast.LENGTH_SHORT)
      .show()
    // redireciona para próxima tela
    val intent = Intent(context, ListTransactionsActivity::class.java)
    intent.putExtra("id", person.id)
    context.startActivity(intent)
  }

  fun show() {
    // mostrando dialog
    dialog.show()

    // inserindo cor dos botões
    dialog.getButton(AlertDialog.BUTTON_POSITIVE).apply {
      // setBackgroundColor(Color.rgb(189, 227, 209)) // cor de fundo
      setBackgroundColor(Color.rgb(93, 167, 78)) // cor de fundo
      setTextColor(Color.rgb(237, 241, 255)) // cor do texto
    }

    dialog.getButton(AlertDialog.BUTTON_NEGATIVE).apply {
      setBackgroundColor(Color.rgb(224, 48, 27)) // cor de fundo
      setTextColor(Color.rgb(237, 241, 255)) // cor do texto
    }
  }
}
