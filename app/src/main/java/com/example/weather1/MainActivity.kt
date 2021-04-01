package com.example.weather1

import android.annotation.SuppressLint
import android.location.Location
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.location.LocationManagerCompat.isLocationEnabled
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.gson.Gson
import org.json.JSONException
import org.json.JSONObject
import com.google.gson.GsonBuilder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val output = findViewById<TextView>(R.id.output)
        val checkButton = findViewById<Button>(R.id.button)



        //val url = "https://api.openweathermap.org/data/2.5/onecall?lat=" + location?.latitude + "&lon=" + location?.longitude + "&exclude=current,minutely,daily,alerts&appid=dd35a98a474b3c9fcd933f2099ab0ff2"
        val url = "https://api.openweathermap.org/data/2.5/onecall?lat=40&lon=-76&exclude=current,minutely,daily,alerts&appid=dd35a98a474b3c9fcd933f2099ab0ff2"

        val queue = Volley.newRequestQueue(this)

        val fusedLocationClient: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        // Button click uses StringRequest through Volley I think to get the API info **working!
        checkButton.setOnClickListener {
            val stringRequest = StringRequest(
                Request.Method.GET, url,
                { response ->
                    // Display the first 500 characters of the response string.
                    val obj = JSONObject(response)

                    //val gSONbuilder = GsonBuilder().create()
                    //val OpenWeatherData = gSONbuilder.fromJson(obj, OpenWeather::class.java)

                    output.text = obj.getString("timezone")
                },
                Response.ErrorListener { output.text = "That didn't work!" })

            // Add the request to the RequestQueue.
            queue.add(stringRequest)
        }

    }
}

