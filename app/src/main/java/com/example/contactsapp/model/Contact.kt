package com.example.contactsapp.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Contact(
    @StringRes val Name : Int,
    @StringRes val PhoneNumber : Int,
    @DrawableRes val Image : Int
)
