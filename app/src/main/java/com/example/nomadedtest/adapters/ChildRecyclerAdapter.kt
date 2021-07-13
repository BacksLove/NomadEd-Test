package com.example.nomadedtest.adapters

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nomadedtest.R
import com.example.nomadedtest.controllers.EventDetailActivity
import com.example.nomadedtest.models.Events
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat

class ChildRecyclerAdapter (var eventList: List<Events>) : RecyclerView.Adapter<ChildRecyclerAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.section_item, parent, false)

        return CustomViewHolder(cellForRow)
    }

    override fun getItemCount(): Int {
        return eventList.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val item = eventList[position]
        val pattern = "dd/MM"
        val simpleDateFormat = SimpleDateFormat(pattern)
        val date: String = simpleDateFormat.format(item.dateStart)
        val image = holder.itemImageView

        holder.itemTitleTextView.text = item.title
        holder.itemDateTextView.text = date
        holder.itemExcerptTextView.text = item.excerpt

        if (item.medias.isNullOrEmpty()) {
            println("Pas de photo")
        } else {
            Picasso.get().load(item.medias?.get(0)?.url).into(image)
        }

        holder.itemView.setOnClickListener{
            val activity = holder.itemView.context as Activity
            val intent = Intent(activity, EventDetailActivity::class.java)
            intent.putExtra("EventPassed", item)
            holder.itemView.context.startActivity(intent)
        }

    }

    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val itemImageView: ImageView = itemView.findViewById(R.id.itemImageView)
        val itemDateTextView: TextView = itemView.findViewById(R.id.itemDateTextView)
        val itemTitleTextView: TextView = itemView.findViewById(R.id.itemTitleTextView)
        val itemExcerptTextView: TextView = itemView.findViewById(R.id.itemExcerptTextView)

    }

}
