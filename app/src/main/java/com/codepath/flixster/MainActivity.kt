package com.codepath.flixster

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Headers
import org.json.JSONException

private const val TAG = "MainActivity"
private const val MOVIES_URL = "https://api.themoviedb.org/3/movie/popular?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed"
private const val TV_SHOWS_URL = "https://api.themoviedb.org/3/tv/popular?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed"

class MainActivity : AppCompatActivity() {
    private val movies = mutableListOf<Media>()
    private val tvShows = mutableListOf<Media>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rvMovies = findViewById<RecyclerView>(R.id.rv_movies)
        val movieAdapter = MediaRecyclerViewAdapter(movies)
        rvMovies.adapter = movieAdapter
        rvMovies.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val rvTvShows = findViewById<RecyclerView>(R.id.rv_tv_shows)
        val tvShowAdapter = MediaRecyclerViewAdapter(tvShows)
        rvTvShows.adapter = tvShowAdapter
        rvTvShows.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val client = AsyncHttpClient()
        
        client.get(MOVIES_URL, object : JsonHttpResponseHandler() {
            override fun onFailure(statusCode: Int, headers: Headers?, response: String?, throwable: Throwable?) {
                Log.e(TAG, "Movies onFailure $statusCode")
            }

            override fun onSuccess(statusCode: Int, headers: Headers?, json: JSON) {
                try {
                    val resultsJson = json.jsonObject.getJSONArray("results").toString()
                    val gson = Gson()
                    val mediaType = object : TypeToken<List<Media>>() {}.type
                    val models: List<Media> = gson.fromJson(resultsJson, mediaType)
                    movies.addAll(models)
                    movieAdapter.notifyDataSetChanged()
                } catch (e: JSONException) {
                    Log.e(TAG, "Movies JSONException $e")
                }
            }
        })

        client.get(TV_SHOWS_URL, object : JsonHttpResponseHandler() {
            override fun onFailure(statusCode: Int, headers: Headers?, response: String?, throwable: Throwable?) {
                Log.e(TAG, "TV Shows onFailure $statusCode")
            }

            override fun onSuccess(statusCode: Int, headers: Headers?, json: JSON) {
                try {
                    val resultsJson = json.jsonObject.getJSONArray("results").toString()
                    val gson = Gson()
                    val mediaType = object : TypeToken<List<Media>>() {}.type
                    val models: List<Media> = gson.fromJson(resultsJson, mediaType)
                    tvShows.addAll(models)
                    tvShowAdapter.notifyDataSetChanged()
                } catch (e: JSONException) {
                    Log.e(TAG, "TV Shows JSONException $e")
                }
            }
        })
    }
}
