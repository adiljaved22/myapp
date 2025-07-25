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
            import androidx.compose.foundation.layout.width
            import androidx.compose.foundation.lazy.LazyColumn
            import androidx.compose.material.icons.Icons
            import androidx.compose.material.icons.rounded.Image
            import androidx.compose.material3.Button
            import androidx.compose.material3.Icon
            import androidx.compose.material3.Text
            import androidx.compose.material3.TextField
            import androidx.compose.runtime.Composable
            import androidx.compose.runtime.getValue
            import androidx.compose.runtime.mutableStateOf
            import androidx.compose.runtime.remember
            import androidx.compose.runtime.setValue
            import androidx.compose.ui.Alignment
            import androidx.compose.ui.Modifier
            import androidx.compose.ui.layout.ContentScale
            import androidx.compose.ui.platform.LocalContext
            import androidx.compose.ui.text.font.FontWeight
            import androidx.compose.ui.tooling.preview.Preview
            import androidx.compose.ui.unit.dp
            import androidx.compose.ui.unit.sp
            import coil.compose.AsyncImage


            @Composable
            fun ThirdScreen(NavigateToThirdScreen: () -> Unit) {
                var context= LocalContext.current
                var selectedimageuri by remember { mutableStateOf<List<Uri?>>(emptyList()) }
                var mutiplephotolauncher = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.PickMultipleVisualMedia()
                ) { uris: List<Uri> ->
                    uris.forEach { imageeach ->
                        val going = ImageItem(
                            uri = imageeach, name = "", description = ""
                        )
                        Responsible.imagelist.add(going)

                    }
                }
                LazyColumn {
                    item {
                        Text(
                            "Choose photos",/*  modifier = Modifier.align(Alignment.Start),*/
                            fontSize = 24.sp, fontWeight = FontWeight.Medium
                        )
                        Button(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp), onClick = {
                                mutiplephotolauncher.launch(
                                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                                )
                            }

                        ) {
                            Icon(imageVector = Icons.Rounded.Image, contentDescription = "")
                            Spacer(modifier = Modifier.width(3.dp))
                            Text("Add Photos")

                        }

                        Responsible.imagelist.forEachIndexed { index, item ->
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                AsyncImage(
                                    model = item.uri,
                                    contentDescription = "User Image",
                                    modifier = Modifier
                                        .padding(8.dp)
                                        .width(200.dp)
                                        .height(200.dp),
                                    contentScale = ContentScale.Crop
                                )
                                TextField(value = item.name, onValueChange = { newname ->
                                    Responsible.imagelist[index] = item.copy(name = newname)


                                }, label = { Text("Name") })
                                Spacer(modifier = Modifier.padding(4.dp))
                                TextField(value = item.description, onValueChange = { newDesc ->
                                    Responsible.imagelist[index] = item.copy(description = newDesc)
                                }, label = { Text("Description") })

                            }

                        }
                        Button(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = {
                                val isAllFilled = Responsible.imagelist.all {
                                    it.name.isNotBlank() && it.description.isNotBlank()
                                }
                                if (isAllFilled) {
                                    NavigateToThirdScreen()
                                } else {

                                    Toast.makeText(context, "Please fill name and description for all images", Toast.LENGTH_SHORT).show()
                                }
                            }) {
                            Text("Save")
                        }
                    }

                }



            }


            @Preview(showBackground = true)
            @Composable
            fun ThirdScreenPreview() {
                ThirdScreen(NavigateToThirdScreen = {})
            }