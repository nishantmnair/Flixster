package com.codepath.flixster

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {
    private lateinit var personImageView: ImageView
    private lateinit var personNameTextView: TextView
    private lateinit var personPopularityTextView: TextView
    private lateinit var workTitleTextView: TextView
    private lateinit var workPosterImageView: ImageView
    private lateinit var workDescriptionTextView: TextView
    private lateinit var backButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        personImageView = findViewById(R.id.person_detail_image)
        personNameTextView = findViewById(R.id.person_detail_name)
        personPopularityTextView = findViewById(R.id.person_popularity_value)
        workTitleTextView = findViewById(R.id.work_title)
        workPosterImageView = findViewById(R.id.work_poster)
        workDescriptionTextView = findViewById(R.id.work_description)
        backButton = findViewById(R.id.back_button)

        backButton.setOnClickListener {
            finish()
        }

        val person = intent.getSerializableExtra("PERSON_EXTRA") as Person

        personNameTextView.text = person.name
        personPopularityTextView.text = person.popularity.toString()

        // Load Person's headshot
        Glide.with(this)
            .load(person.profileImageUrl)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .into(personImageView)

        // Display the first item in "known_for" as their best-known work
        val bestWork = person.knownFor?.firstOrNull()
        if (bestWork != null) {
            workTitleTextView.text = bestWork.workTitle
            workDescriptionTextView.text = bestWork.overview
            
            Glide.with(this)
                .load(bestWork.posterImageUrl)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(workPosterImageView)
        } else {
            workTitleTextView.text = "No known work listed"
            workDescriptionTextView.text = ""
        }
    }
}
