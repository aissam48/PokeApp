package com.android.pokeapp.ui.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest

@Composable
fun ImageView(url:String){
    Image(
        modifier = Modifier.size(100.dp),
        painter = rememberAsyncImagePainter(
            ImageRequest.Builder(LocalContext.current).data(url).build()
        ),
        contentDescription = "Description of the image",
    )
}