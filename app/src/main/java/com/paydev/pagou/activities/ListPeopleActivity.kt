package com.paydev.pagou.activities

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.paydev.pagou.R
import com.paydev.pagou.use_cases.CreatePersonUseCase

class ListPeopleActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_list_people)
  }

  fun onClick(view: View?) {
    val builder = AlertDialog.Builder(this)
    val view = layoutInflater.inflate(R.layout.activity_add_person, null)
    val nameInput = view.findViewById<EditText>(R.id.name_input)
    val contactInput = view.findViewById<EditText>(R.id.contact_input)
    val othersInput = view.findViewById<EditText>(R.id.others_input)

    builder.setView(view)
      .setPositiveButton("OK") { _, _ ->
        val name = nameInput.text.toString()
        val contact = contactInput.text.toString()
        val others = othersInput.text.toString()

        val person = CreatePersonUseCase(this).execute(name, contact, others)

        val intent = Intent(this, ListTransactionsActivity::class.java)
        startActivity(intent)
        // Faça algo com o texto do input
      }
      .setNegativeButton("Cancelar") { dialog, _ ->
        dialog.cancel()
      }
      .setPositiveButton("Aceitar"){ dialog, which ->
        Toast.makeText(this, "Pessoa adicioanda com sucesso", Toast.LENGTH_SHORT).show()

      }

    val alertDialog = builder.create()
    alertDialog.show()

    val positiveButton = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE)

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

    positiveButton.setBackgroundTintList(colorStateList)
  }
}