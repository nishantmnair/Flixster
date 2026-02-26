package com.codepath.flixster

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class PeopleRecyclerViewAdapter(
    private val people: List<Person>
) : RecyclerView.Adapter<PeopleRecyclerViewAdapter.PersonViewHolder>() {

    class PersonViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val personName: TextView = view.findViewById(R.id.person_name)
        val personImage: ImageView = view.findViewById(R.id.person_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_person, parent, false)
        return PersonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val person = people[position]
        holder.personName.text = person.name

        Glide.with(holder.itemView)
            .load(person.profileImageUrl)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .centerCrop()
            .into(holder.personImage)

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailActivity::class.java)
            intent.putExtra("PERSON_EXTRA", person)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount() = people.size
}
