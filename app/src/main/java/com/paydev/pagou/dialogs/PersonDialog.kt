package com.paydev.pagou.dialogs

import android.app.AlertDialog
import android.app.AlertDialog.Builder
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.TextView
import com.paydev.pagou.utils.InputUtils
import com.paydev.pagou.R
import com.paydev.pagou.activities.ListTransactionsActivity
import com.paydev.pagou.models.Person
import com.paydev.pagou.use_cases.CreatePersonUseCase
import com.paydev.pagou.use_cases.GetPersonReportUseCase
import com.paydev.pagou.use_cases.UpdatePersonUseCase
import com.paydev.pagou.utils.StringUtils

class PersonDialog(private val context: Context, private val callback: (() -> Unit)? = null): Builder(context) {
  // construindo dialog
  private val dialogView = LayoutInflater
    .from(context)
    .inflate(R.layout.activity_add_person, null)
  private var person: GetPersonReportUseCase.PersonReport? = null

  // inputs
  private val nameInput = dialogView
    .findViewById<EditText>(R.id.name_input)
  private val contactInput = dialogView
    .findViewById<EditText>(R.id.contact_input)
  private val othersInput = dialogView
    .findViewById<EditText>(R.id.others_input)
  private val errorsList = dialogView
    .findViewById<TextView>(R.id.person_errors_list)

  // construtor
  init {
    setView(dialogView)
    setNegativeButton("cancelar") { _, _ -> }
    setPositiveButton("ok") { _, _ -> }
  }

  // chama função ao apertar em OK
  private fun execute(): Person {
    val name = InputUtils.getString(nameInput)
    val contact = InputUtils.getString(contactInput)
    val others = InputUtils.getStringOrNull(othersInput)
    if(person != null)
      return UpdatePersonUseCase(context).execute(person!!.info.id, name, contact, others)
    return CreatePersonUseCase(context).execute(name, contact, others)
  }

  private fun redirectToNextScreen(personId: Long) {
    if(person != null) return
    val intent = Intent(context, ListTransactionsActivity::class.java)
    intent.putExtra("id", personId)
    context.startActivity(intent)
  }

  override fun show(): AlertDialog {
    val dialog = super.show()
    dialog.show()

    // inserindo cor dos botões
    dialog.getButton(AlertDialog.BUTTON_POSITIVE).apply {
      setBackgroundColor(Color.rgb(93, 167, 78)) // cor de fundo
      setTextColor(Color.rgb(237, 241, 255)) // cor do texto

      setOnClickListener {
        val person = execute()
        if(person.errors.isEmpty()) {
          dialog.dismiss()
          redirectToNextScreen(person.id)
          callback?.let { it() }
          return@setOnClickListener
        }
        // montando lista de erros
        errorsList.text = StringUtils.buildList(person.errors)
      }
    }

    dialog.getButton(AlertDialog.BUTTON_NEGATIVE).apply {
      setBackgroundColor(Color.rgb(224, 48, 27)) // cor de fundo
      setTextColor(Color.rgb(237, 241, 255)) // cor do texto
    }

    return dialog
  }

  fun show(id: Long) {
    person = GetPersonReportUseCase(context).execute(id)
    nameInput.setText(person!!.info.name)
    contactInput.setText(person!!.info.contact)
    othersInput.setText(person!!.info.others)
    show()
  }
}
