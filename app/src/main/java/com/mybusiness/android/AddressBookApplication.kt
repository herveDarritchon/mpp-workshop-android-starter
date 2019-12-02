package com.mybusiness.android

import android.app.Application
import com.mybusiness.di.CommonInjector
import org.kodein.di.KodeinAware

class AddressBookApplication: Application(), KodeinAware {
    override val kodein = CommonInjector.kodein
}