package com.guillermomuntaner.adoptapuppy.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guillermomuntaner.adoptapuppy.model.Puppy
import com.guillermomuntaner.adoptapuppy.model.samplePuppies

@Composable
fun PuppyListItem(puppy: Puppy, onClick: () -> Unit) {
    Box(modifier = Modifier.padding(4.dp)) {
        Card(
            elevation = 4.dp,
            backgroundColor = MaterialTheme.colors.surface
        ) {
            Row(
                modifier = Modifier
                    .clickable(onClick = onClick)
                    .fillMaxWidth()
                    .height(100.dp)
            ) {
                val image: Painter = painterResource(id = puppy.drawableRes)
                Image(
                    painter = image,
                    contentDescription = puppy.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.aspectRatio(1f)
                )
                Column(modifier = Modifier.padding(8.dp)) {
                    Text(
                        text = puppy.name,
                        style = MaterialTheme.typography.h6,
                        color = MaterialTheme.colors.onSurface,
                    )
                    Text(
                        text = "${puppy.age} · ${puppy.race}",
                        style = MaterialTheme.typography.caption,
                        color = MaterialTheme.colors.onSurface,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SamplePuppyListItem() {
    PuppyListItem(puppy = samplePuppies.first(), onClick = {})
}