package com.codepath.flixster

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

class MediaRecyclerViewAdapter(
    private val mediaList: List<Media>
) : RecyclerView.Adapter<MediaRecyclerViewAdapter.MediaViewHolder>() {

    class MediaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val mediaTitle: TextView = view.findViewById(R.id.media_title)
        val mediaPoster: ImageView = view.findViewById(R.id.media_poster)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MediaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_media, parent, false)
        return MediaViewHolder(view)
    }

    override fun onBindViewHolder(holder: MediaViewHolder, position: Int) {
        val media = mediaList[position]
        holder.mediaTitle.text = media.displayTitle

        Glide.with(holder.itemView)
            .load(media.posterImageUrl)
            .placeholder(R.drawable.ic_launcher_background)
            .transform(RoundedCorners(40))
            .into(holder.mediaPoster)

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, MediaDetailActivity::class.java)
            intent.putExtra("MEDIA_EXTRA", media)
            
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                holder.itemView.context as Activity,
                holder.mediaPoster,
                "media_poster_transition"
            )
            
            holder.itemView.context.startActivity(intent, options.toBundle())
        }
    }

    override fun getItemCount() = mediaList.size
}
