package com.example.nomadedtest.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nomadedtest.R
import com.example.nomadedtest.models.Section
import java.text.SimpleDateFormat

class MainRecyclerAdapter (var sectionList: List<Section>) : RecyclerView.Adapter<MainRecyclerAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.section_row, parent, false)
        return CustomViewHolder(cellForRow)
    }

    override fun getItemCount(): Int {
        return sectionList.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val item = sectionList[position]
        val itemName = item.sectionTitle
        val itemList = item.sectionList
        val pattern = "MMMM YYYY"
        val simpleDateFormat = SimpleDateFormat(pattern)
        val date: String = simpleDateFormat.format(itemName)

        holder.sectionTextView.text = date

        if (itemList.isEmpty()) {
            holder.sectionRecyclerView.isVisible = false
            holder.sectionNoContentTextView.isVisible = true
        } else {
            holder.sectionNoContentTextView.isVisible = false
            val linearLayoutManager: LinearLayoutManager = LinearLayoutManager(holder.itemView.context, LinearLayoutManager.VERTICAL, false)
            linearLayoutManager.initialPrefetchItemCount = itemList.size
            holder.sectionRecyclerView.layoutManager = linearLayoutManager
            holder.sectionRecyclerView.adapter = ChildRecyclerAdapter(itemList)
            holder.sectionRecyclerView.apply { addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL)) }
        }
    }
    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val sectionTextView : TextView = itemView.findViewById(R.id.sectionNameTextview)
        val sectionRecyclerView : RecyclerView = itemView.findViewById(R.id.sectionRecyclerView)
        val sectionNoContentTextView : TextView = itemView.findViewById(R.id.noContentTextView)
    }

}