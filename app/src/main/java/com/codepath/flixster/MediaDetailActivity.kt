package com.codepath.flixster

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

class MediaDetailActivity : AppCompatActivity() {
    private lateinit var mediaPosterImageView: ImageView
    private lateinit var mediaTitleTextView: TextView
    private lateinit var mediaReleaseDateTextView: TextView
    private lateinit var mediaRatingTextView: TextView
    private lateinit var mediaOverviewTextView: TextView
    private lateinit var backButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_media_detail)

        mediaPosterImageView = findViewById(R.id.media_detail_poster)
        mediaTitleTextView = findViewById(R.id.media_detail_title)
        mediaReleaseDateTextView = findViewById(R.id.media_release_date)
        mediaRatingTextView = findViewById(R.id.media_rating)
        mediaOverviewTextView = findViewById(R.id.media_detail_overview)
        backButton = findViewById(R.id.media_back_button)

        backButton.setOnClickListener {
            onBackPressed()
        }

        val media = intent.getSerializableExtra("MEDIA_EXTRA") as Media

        mediaTitleTextView.text = media.displayTitle
        mediaReleaseDateTextView.text = "Released: ${media.displayDate}"
        mediaRatingTextView.text = "Rating: ${media.voteAverage} / 10"
        mediaOverviewTextView.text = media.overview

        Glide.with(this)
            .load(media.posterImageUrl)
            .placeholder(R.drawable.ic_launcher_background)
            .transform(RoundedCorners(40))
            .into(mediaPosterImageView)
    }
}
