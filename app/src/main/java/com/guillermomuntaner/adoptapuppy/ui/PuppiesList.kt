package com.guillermomuntaner.adoptapuppy.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.guillermomuntaner.adoptapuppy.model.Puppy

@Composable
fun PuppiesList(puppies: List<Puppy>, modifier: Modifier = Modifier, onClick: (Puppy) -> Unit) {
    // We save the scrolling position with this state that can also
    // be used to programmatically scroll the list
    val scrollState = rememberLazyListState()
    LazyColumn(modifier = Modifier.fillMaxWidth(), state = scrollState) {
        items(puppies, key = { puppy -> puppy.id}) { puppy ->
            PuppyListItem(puppy = puppy, onClick = { onClick(puppy) })
        }
    }
}