package com.example.composeime.view.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.composeime.model.Key

@Composable
fun KeyboardKey(
    key: Key,
    size: Dp
) = with(key) {
    Box(
        modifier = sizeStrategy(size).fillMaxHeight(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = shownText,
            modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp)
                .border(1.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(5.dp))
                .clickable(interactionSource = interactionSource, indication = null) {}
                .background(MaterialTheme.colorScheme.secondaryContainer)
                .padding(
                    start = 12.dp,
                    end = 12.dp,
                    top = 16.dp,
                    bottom = 16.dp
                ),
            color = MaterialTheme.colorScheme.onSecondaryContainer
        )
    }
}