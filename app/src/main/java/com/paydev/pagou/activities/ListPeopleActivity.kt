package com.paydev.pagou.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.paydev.pagou.R

class ListPeopleActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_list_people)
  }
  fun onClick(view: View?) {
    val intent = Intent(this, ListTransactionsActivity::class.java)
    startActivity(intent)
  }
}