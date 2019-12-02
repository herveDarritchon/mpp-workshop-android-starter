package com.mybusiness.android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mybusiness.android.utils.SimpleItemRecyclerViewAdapter
import com.mybusiness.di.CommonInjector
import com.mybusiness.model.Contact
import com.mybusiness.presentation.ContactList
import kotlinx.android.synthetic.main.activity_item_list.*
import kotlinx.android.synthetic.main.item_list.*

class ItemListActivity : AppCompatActivity() , ContactList.View{

    private val presenter = CommonInjector.contactListPresenter()
    // ...
    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
    }
    // ...
    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_list)

        setSupportActionBar(toolbar)
        toolbar.title = title
    }
    override fun displayContactList(contactList: List<Contact>) {
        item_list.adapter = SimpleItemRecyclerViewAdapter(this, contactList)
    }
}
