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
private const val NOW_PLAYING_URL = "https://api.themoviedb.org/3/movie/now_playing?&api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed"

class MainActivity : AppCompatActivity() {
    private val movies = mutableListOf<Movie>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rvMovies = findViewById<RecyclerView>(R.id.recycler_view)
        val movieAdapter = MoviesRecyclerViewAdapter(movies)
        rvMovies.adapter = movieAdapter
        rvMovies.layoutManager = LinearLayoutManager(this)

        val client = AsyncHttpClient()
        client.get(NOW_PLAYING_URL, object : JsonHttpResponseHandler() {
            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String?,
                throwable: Throwable?
            ) {
                Log.e(TAG, "onFailure $statusCode")
            }

            override fun onSuccess(statusCode: Int, headers: Headers?, json: JSON) {
                Log.i(TAG, "onSuccess: JSON data $json")
                try {
                    val resultsJson = json.jsonObject.getJSONArray("results").toString()
                    val gson = Gson()
                    val movieType = object : TypeToken<List<Movie>>() {}.type
                    val models: List<Movie> = gson.fromJson(resultsJson, movieType)
                    movies.addAll(models)
                    movieAdapter.notifyDataSetChanged()
                    Log.i(TAG, "Movie list size ${movies.size}")
                } catch (e: JSONException) {
                    Log.e(TAG, "Encountered exception $e")
                }
            }
        })
    }
}
