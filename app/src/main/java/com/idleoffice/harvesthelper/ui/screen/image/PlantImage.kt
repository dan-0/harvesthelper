package com.idleoffice.harvesthelper.ui.screen.image

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter

@Composable
fun PlantImage(
    image: String,
    description: String
) {
    val uri =
        "android.resource://com.idleoffice.harvesthelper/raw/${image.substringBeforeLast(".")}"
    Box(
        modifier = Modifier
            .background(Color.Gray)
            .height(200.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {

        Image(
            painter = rememberImagePainter(uri),
            contentDescription = description,
            contentScale = ContentScale.FillHeight,
            modifier = Modifier.fillMaxHeight()
        )
    }
}