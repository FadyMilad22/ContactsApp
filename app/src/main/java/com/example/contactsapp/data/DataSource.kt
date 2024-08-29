package com.example.contactsapp.data

import com.example.contactsapp.R
import com.example.contactsapp.model.Contact

class DataSource {

    fun getContactsData(): List<Contact> {

        val contactsList = mutableListOf(
            Contact(
                Name = R.string.name_son,
                PhoneNumber = R.string.phone_son,
                Image = R.drawable.son
            ),
            Contact(
                Name = R.string.name_granny,
                PhoneNumber = R.string.phone_granny,
                Image = R.drawable.granny
            ),
            Contact(
                Name = R.string.name_friend1,
                PhoneNumber = R.string.phone_friend1,
                Image = R.drawable.friend_1
            ),
            Contact(
                Name = R.string.name_auntie,
                PhoneNumber = R.string.phone_auntie,
                Image = R.drawable.auntie
            ),
            Contact(
                Name = R.string.name_brother,
                PhoneNumber = R.string.phone_brother,
                Image = R.drawable.brother
            ),
            Contact(
                Name = R.string.name_daughter,
                PhoneNumber = R.string.phone_daughter,
                Image = R.drawable.daughter
            ),
            Contact(
                Name = R.string.name_friend2,
                PhoneNumber = R.string.phone_friend2,
                Image = R.drawable.friend_2
            ),
            Contact(
                Name = R.string.name_grandfather,
                PhoneNumber = R.string.phone_grandfather,
                Image = R.drawable.grandfather
            ),
            Contact(
                Name = R.string.name_neighbour,
                PhoneNumber = R.string.phone_neighbour,
                Image = R.drawable.neigbour
            ),
            Contact(
                Name = R.string.name_sister,
                PhoneNumber = R.string.phone_sister,
                Image = R.drawable.sister
            ),

            Contact(
                Name = R.string.name_uncle,
                PhoneNumber = R.string.phone_uncle,
                Image = R.drawable.uncle
            )
        )


    return contactsList.toList()}


}