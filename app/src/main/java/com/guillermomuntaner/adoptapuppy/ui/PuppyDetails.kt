package com.guillermomuntaner.adoptapuppy

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
fun PuppyDetails(puppy: Puppy) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .verticalScroll(rememberScrollState())
    ) {
        val image: Painter = painterResource(id = puppy.drawableRes)
        Image(
            painter = image,
            contentDescription = puppy.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier.aspectRatio(1f)
        )
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = puppy.name,
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.primary,
            )
            Text(
                text = """
                     · Race: ${puppy.race}
                     · Age: ${puppy.age}
                     · Gender: ${puppy.gender.name}
                """.trimIndent(),
                style = MaterialTheme.typography.caption,
                color = MaterialTheme.colors.onSurface,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Description:",
                style = MaterialTheme.typography.overline,
                color = MaterialTheme.colors.onSurface,
            )
            Text(
                text = puppy.description,
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onSurface,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SamplePuppyDetails() {
    PuppyDetails(puppy = samplePuppies.first())
}