package com.example.test_1

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Image
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage


@Composable
fun ThirdScreen(NavigateToThirdScreen: () -> Unit) {
    val context=LocalContext.current
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    var name by remember { mutableStateOf("") }
    var discription by remember { mutableStateOf("") }
    var photopickerlauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { selectedImageUri = it })
    Column(modifier = Modifier.padding(18.dp)) {
        Text(
            "Choose a photo",
            style = TextStyle(fontSize = 34.sp)


        )
        Spacer(modifier = Modifier.size(20.dp))
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                photopickerlauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
            }) {

            Icon(
                Icons.Rounded.Image, contentDescription = ""
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text("Add a photo")
        }
        AsyncImage(
            model = selectedImageUri,
            contentDescription = "Selected Image",
            contentScale = ContentScale.Crop,

            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
                .clip(RoundedCornerShape(8.dp))
        )
        Column() {

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = name,
                onValueChange = { name = it },
                label = { Text("Enter recipe name") })
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(), value = discription,
                onValueChange = { discription = it },
                label = { Text("Enter discription") })
Spacer(modifier= Modifier.padding(8.dp) )
            Button( modifier = Modifier.fillMaxWidth(),
                onClick = {
                    if (selectedImageUri != null && name.isNotEmpty() && discription.isNotEmpty())
                    {
                        Toast.makeText(context,"Saved",Toast.LENGTH_LONG).show()
                        NavigateToThirdScreen

                    }else{
                        Toast.makeText(context,"Filled all Blanks",Toast.LENGTH_LONG).show()
                    }
                }
            )

            {
                Text("Save")
            }
        }
    }
}

/*
@Preview(showBackground = true)
@Composable
fun ThirdScreenPreview() {
    ThirdScreen(NavigateToThirdScreen = {})
}

*/
