package com.mybusiness.android.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mybusiness.android.ItemListActivity
import com.mybusiness.android.R
import kotlinx.android.synthetic.main.item_list_content.view.*

class EmptyContact // TODO delete and replace by Contact

class SimpleItemRecyclerViewAdapter(private val parentActivity: ItemListActivity,
                                    private val values: List<EmptyContact>) :
    RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>() {

    private val onClickListener: View.OnClickListener

    init {
        onClickListener = View.OnClickListener { v ->
            // TODO Set the "on click" action for the contact list
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_content, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]

        // TODO Set the content showed in the RecyclerView

        with(holder.itemView) {
            tag = item
            setOnClickListener(onClickListener)
        }
    }

    override fun getItemCount() = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val contentView: TextView = view.content
    }
}