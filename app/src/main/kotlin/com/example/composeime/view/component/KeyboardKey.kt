package com.example.composeime.view.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeime.model.Key

@Composable
fun RowScope.KeyboardKey(
    key: Key,
    size: Dp
) = with(key) {
    Box(
        modifier = sizeStrategy(size)
            .fillMaxHeight()
            .padding(2.dp)
            .border(0.3.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(8.dp))
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.secondaryContainer),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = shownText,
            modifier = Modifier
                .clickable(interactionSource = interactionSource, indication = null) {},
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}