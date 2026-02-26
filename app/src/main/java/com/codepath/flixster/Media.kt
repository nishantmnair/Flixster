package com.codepath.flixster

import com.google.gson.annotations.SerializedName

data class Media(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("name")
    val tvName: String?,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("overview")
    val overview: String?,
    @SerializedName("vote_average")
    val voteAverage: Double?,
    @SerializedName("release_date")
    val releaseDate: String?,
    @SerializedName("first_air_date")
    val firstAirDate: String?
) : java.io.Serializable {
    val displayTitle: String?
        get() = title ?: tvName

    val posterImageUrl: String?
        get() = if (posterPath != null) "https://image.tmdb.org/t/p/w500/$posterPath" else null

    val displayDate: String?
        get() = releaseDate ?: firstAirDate
}
