package com.example.contactsapp

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.getString
import com.example.contactsapp.data.DataSource
import com.example.contactsapp.model.Contact
import com.example.contactsapp.ui.theme.ContactsAppTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ContactsAppTheme {
                val context = LocalContext.current
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(stringResource(id = R.string.app_name))
                            },
                            actions = {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_home),

                                    contentDescription = "Home Contact",
                                    Modifier.padding(16.dp).clickable {
                                        onContactClick(
                                            Contact(
                                                Name = R.string.name_home,
                                                PhoneNumber = R.string.phone_home,
                                                Image = R.drawable.ic_home
                                            ), context = context
                                        )
                                    }
                                )
                            },
                            colors = topAppBarColors(
                                containerColor = Color.White,
                                titleContentColor = Color.Black,
                            ),
                        )
                    },
                ) { innerPadding ->
                    ContactsList(
                        Contacts = DataSource().getContactsData(),
                        modifier = Modifier.padding(innerPadding)
                    )
                }


            }
        }
    }
}

@Composable
fun ContactsList(Contacts: List<Contact>, modifier: Modifier = Modifier) {

    val configuration = LocalConfiguration.current
    val columns = if (configuration.orientation == android.content.res.Configuration.ORIENTATION_LANDSCAPE) {
        5
    } else {
        3
    }

    val context = LocalContext.current

    LazyVerticalGrid(
        columns = GridCells.Fixed(columns), modifier = modifier,
    ) {
        items(Contacts.size) {
            ContactListItem(
                contact = Contacts[it],
                onClick = { contact, context -> onContactClick(contact, context) },
                context = context,
                modifier = modifier
            )
        }

    }


}


@Composable
fun ContactListItem(
    contact: Contact,
    onClick: (Contact, Context) -> Unit,
    context: Context,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = Modifier.fillMaxWidth().border(0.dp, Color.Transparent)
            .background(Color.LightGray)
            .clickable { onClick(contact, context) }
            .wrapContentWidth(Alignment.CenterHorizontally),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(id = contact.Image),
            contentDescription = stringResource(id = contact.Name),
            contentScale = ContentScale.Fit


            )
        Text(text = stringResource(id = contact.Name), textAlign = TextAlign.Center)
        SelectionContainer() {

            Text(
                text = stringResource(id = contact.PhoneNumber),
                textAlign = TextAlign.Center,
                fontSize = 12.sp,
                color = Color.DarkGray
            )
        }
    }
}

fun onContactClick(contact: Contact, context: Context) {
    val intent = Intent(Intent.ACTION_DIAL).apply {
        data = Uri.parse("tel:${getString(context, contact.PhoneNumber)}")
    }
    try {
        context.startActivity(intent)
    } catch (e: Exception) {
        e.printStackTrace()
        Toast.makeText(context, "Unable to open dialer", Toast.LENGTH_SHORT).show()
    }

}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ContactsDesignPreview() {
    ContactsAppTheme {
        ContactsList(DataSource().getContactsData())
    }
}