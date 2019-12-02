package com.mybusiness.android.utils

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mybusiness.android.ItemDetailActivity
import com.mybusiness.android.ItemDetailFragment
import com.mybusiness.android.ItemListActivity
import com.mybusiness.android.R
import com.mybusiness.model.Contact
import kotlinx.android.synthetic.main.item_list_content.view.*

class SimpleItemRecyclerViewAdapter(private val parentActivity: ItemListActivity,
                                    private val values: List<Contact>) :
    RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>() {

    private val onClickListener: View.OnClickListener

    init {
        onClickListener = View.OnClickListener { v ->
            val contact = v.tag as Contact
            val intent = Intent(v.context, ItemDetailActivity::class.java).apply {
                putExtra(ItemDetailFragment.CONTACT_ID, contact.id)
            }
            v.context.startActivity(intent)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_content, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contact = values[position]

        holder.contentView.text = contact.fullName

        with(holder.itemView) {
            tag = contact
            setOnClickListener(onClickListener)
        }
    }

    override fun getItemCount() = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val contentView: TextView = view.content
    }
}