package com.codepath.flixster

import com.google.gson.annotations.SerializedName

data class Person(
    @SerializedName("name")
    val name: String?,
    @SerializedName("profile_path")
    val profilePath: String?,
    @SerializedName("known_for_department")
    val knownForDepartment: String?,
    @SerializedName("popularity")
    val popularity: Double?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("known_for")
    val knownFor: List<KnownFor>?
) : java.io.Serializable {
    val profileImageUrl: String?
        get() = if (profilePath != null) "https://image.tmdb.org/t/p/w500/$profilePath" else null
}

data class KnownFor(
    @SerializedName("title")
    val title: String?,
    @SerializedName("name")
    val tvName: String?,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("overview")
    val overview: String?
) : java.io.Serializable {
    val workTitle: String?
        get() = title ?: tvName

    val posterImageUrl: String?
        get() = if (posterPath != null) "https://image.tmdb.org/t/p/w500/$posterPath" else null
}
