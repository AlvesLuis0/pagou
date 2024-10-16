package com.paydev.pagou.adapters
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.paydev.pagou.R
import com.paydev.pagou.models.PersonReport

class PeopleListAdapter(private val personList: List <PersonReport>) : RecyclerView.Adapter<PeopleListAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_view_person, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val person = personList.elementAt(position)

        // sets the text to the textview from our itemHolder class
        holder.tvPersonName.text = person.name

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return personList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(personView: View) : RecyclerView.ViewHolder(personView) {
        val tvPersonName: TextView = personView.findViewById(R.id.tv_person_name)
    }
}
