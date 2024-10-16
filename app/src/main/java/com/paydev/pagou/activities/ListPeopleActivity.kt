package com.paydev.pagou.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.paydev.pagou.R
import com.paydev.pagou.adapters.PeopleListAdapter
import com.paydev.pagou.dialogs.PersonDialog
import com.paydev.pagou.use_cases.GetUserInfoUseCase

class ListPeopleActivity : AppCompatActivity() {

  lateinit var recyclerview: RecyclerView

  override fun onCreate(savedInstanceState: Bundle?) {

    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_list_people)

    // this creates a vertical layout Manager
    recyclerview = findViewById(R.id.transactions_recycler_view)
    recyclerview.layoutManager = LinearLayoutManager(this)

  }
  fun openAddPersonDialog(view: View?) {
    PersonDialog(this).show()
  }
  override fun onResume() {
    super.onResume()

    val data = GetUserInfoUseCase(this).execute(true)

    // This will pass the ArrayList to our Adapter
    val adapter = PeopleListAdapter(data.people)

    // Setting the Adapter with the recyclerview
    recyclerview.adapter = adapter
  }
}