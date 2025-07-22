package com.example.test_1


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.Unspecified
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


/*
Create the page with edit text (with validations)
-> User name
-> Email (valid email)
-> Password (view/hide)*/
@Composable
fun FirstScreen() {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(55.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "login", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.padding(16.dp))
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Username") },
            leadingIcon = { Icon(Icons.Rounded.Person, contentDescription = "") })
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp, horizontal = 20.dp)
        )
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = {
                Text(
                    emailError.ifEmpty { "Email" },
                    color = if (emailError.isNotEmpty()) Red else Unspecified
                )
            },
            leadingIcon = { Icon(Icons.Rounded.Email, contentDescription = "") })
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp, horizontal = 20.dp)
        )
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = {
                Text(
                    passwordError.ifEmpty { "Password" },
                    color = if (passwordError.isNotEmpty()) {
                        Red
                    } else {
                        Unspecified
                    }
                )
            },
            leadingIcon = { Icon(Icons.Rounded.Lock, contentDescription = "") },

            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(), trailingIcon = {
                val image=if (passwordVisible){
                    painterResource(id=R.drawable.visibility_24px)
                }else{
                    painterResource(id=R.drawable.visibility_off_24px)
                }
                Icon(
                    painter = image, contentDescription = "", modifier =Modifier.clickable{passwordVisible=!passwordVisible}
                )
            }


        )


        Spacer(modifier = Modifier.padding(vertical = 4.dp, horizontal = 20.dp))
        Button(
            onClick =
                {
                    emailError = if (email.isBlank()) {
                        "Email is required"
                    } else {
                        ""
                    }
                    passwordError = if (password.isBlank()) {
                        "Password is required"
                    } else {
                        ""
                    }
                    /*if (emailError.isEmpty() && passwordError.isEmpty()) {
                        "Enter Valid information "
                    } else {
                        ""
                    }*/


                }) {
            Text("Login", fontSize = 24.sp)
        }
    }
}