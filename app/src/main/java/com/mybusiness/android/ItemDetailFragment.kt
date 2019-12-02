package com.mybusiness.android

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.mybusiness.di.CommonInjector
import com.mybusiness.model.Contact
import com.mybusiness.presentation.ContactDetail
import com.mybusiness.presentation.ContactDetailPresenter
import kotlinx.android.synthetic.main.activity_item_detail.*
import kotlinx.android.synthetic.main.item_detail.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class ItemDetailFragment : Fragment(), ContactDetail.View , KodeinAware {

    override val kodein: Kodein by closestKodein()
    private val presenter by instance<ContactDetailPresenter>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        presenter.attachView(this)

        arguments?.let {
            if (it.containsKey(CONTACT_ID)) {
                presenter.getContact(it.getString(CONTACT_ID) as String)
            }
        }

        return inflater.inflate(R.layout.item_detail, container, false)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun displayContact(contact: Contact) {
        val toolbarLayout: CollapsingToolbarLayout? = activity?.toolbar_layout
        if (toolbarLayout != null) toolbarLayout.title = contact.fullName

        phonesTextView.text = if (contact.phones.isNotEmpty()) {
            contact.phones.map {
                "${it.type}: ${it.number}"
            }.joinToString (separator = "\n")
        } else "_"

        addressesTextView.text = if (contact.addresses.isNotEmpty()) {
            contact.addresses.map {
                """
                |${it.type}:${it.street}
                |             ${it.postalCode} ${it.country}
                |             ${it.country}
                """.trimMargin()
            }.joinToString (separator = "\n------------------------------------------------------------\n")
        } else "_"
    }

    companion object {
        const val CONTACT_ID = "contact_id"
    }
}
