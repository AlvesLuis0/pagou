package com.paydev.pagou.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.paydev.pagou.R
import com.paydev.pagou.dialogs.PersonDialog

class ListPeopleActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_list_people)
  }

  fun openAddPersonDialog(view: View?) {
    PersonDialog(this).show()
  }
}