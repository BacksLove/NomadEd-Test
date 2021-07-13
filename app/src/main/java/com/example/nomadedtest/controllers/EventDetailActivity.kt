package com.example.nomadedtest.controllers

import android.os.Bundle
import android.text.Html
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.nomadedtest.R
import com.example.nomadedtest.models.Events
import java.text.SimpleDateFormat

class EventDetailActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.event_details)

        val event = intent.extras?.get("EventPassed") as Events

        val titleTextView: TextView = findViewById(R.id.eventTitleTextView)
        val contentTextView: TextView = findViewById(R.id.eventContentTextView)
        val addressTextView: TextView = findViewById(R.id.eventAdresseTextView)
        val dateTextView: TextView = findViewById(R.id.eventDateTextView)
        val eventImageView: ImageView = findViewById(R.id.eventImageView)

        val pattern = "dd MMMM YYYY"
        val simpleDateFormat = SimpleDateFormat(pattern)
        val date: String = simpleDateFormat.format(event.dateStart)

        titleTextView.text = event.title
        contentTextView.text = Html.fromHtml(event.content)
        addressTextView.text = "${event.address?.streetAddress} ${event.address?.zipCode} ${event.address?.countryName}"
        dateTextView.text = date

    }

}