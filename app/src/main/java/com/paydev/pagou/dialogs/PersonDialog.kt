package com.paydev.pagou.dialogs

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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
    .setPositiveButton("OK") { _, _ -> execute() }
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
        nameInput.text.toString(),
        contactInput.text.toString(),
        othersInput.text.toString()
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

  companion object fun show() {
    // mostrando dialog
    dialog.show()
    // inserindo cor do botão
    val colorStateList = ColorStateList(
      arrayOf(
        intArrayOf(android.R.attr.state_pressed),
        intArrayOf()
      ),
      intArrayOf(
        Color.parseColor("#BDE3D1"),
        Color.parseColor("#BDE3D1")
      )
    )
    dialog
      .getButton(AlertDialog.BUTTON_POSITIVE)
      .setBackgroundTintList(colorStateList)
  }
}