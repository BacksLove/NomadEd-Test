package com.example.nomadedtest.controllers

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.nomadedtest.adapters.MainRecyclerAdapter
import com.example.nomadedtest.R
import com.example.nomadedtest.constants.Constants
import com.example.nomadedtest.models.Events
import com.example.nomadedtest.models.Section
import com.google.gson.GsonBuilder
import okhttp3.*
import java.io.IOException
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {

    lateinit var mainRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainRecyclerView = findViewById(R.id.mainRecyclerView)
        fetchJson()
    }

    // Fonction de récupération des données via l'API

    fun fetchJson() {
        val url = Constants.URL
        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()

        client.newCall(request).enqueue(object: Callback {
            override fun onFailure(call: Call, e: IOException) {
                println("Echec")
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                val gson = GsonBuilder().create()

                val myData = gson.fromJson(body, Array<Events>::class.java)
                val sortedArray = sortArray(myData)

                runOnUiThread {
                    mainRecyclerView.adapter = MainRecyclerAdapter(sortedArray)
                }
            }
        })
    }

    // Fonction de tri pour former les sections par date

    fun sortArray(arrayToSort: Array<Events>) : List<Section> {
        var finalList: ArrayList<Section> = ArrayList()
        var sectionList: ArrayList<Events> = ArrayList()
        var startMonth: Int = 0
        var startYear: Int = 0
        var dateSaved: Date

        arrayToSort.sortByDescending { it.dateStart }
        startMonth = arrayToSort.get(0).dateStart.month
        startYear = arrayToSort.get(0).dateStart.year
        dateSaved = arrayToSort.get(0).dateStart

        arrayToSort.forEach {
            if ((it.dateStart.month) == startMonth && (it.dateStart.year ) == startYear) {
                sectionList.add(it)
            } else {
                finalList.add(Section(dateSaved, sectionList))
                dateSaved = it.dateStart
                startMonth = it.dateStart.month
                startYear = it.dateStart.year
                sectionList = ArrayList()
            }
        }

        return finalList
    }
}