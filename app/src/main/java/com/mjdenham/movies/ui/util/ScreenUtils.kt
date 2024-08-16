package com.mjdenham.movies.ui.util

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mjdenham.movies.R
import com.mjdenham.movies.ui.theme.Typography

@Composable
fun LoadingIndicator() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        CircularProgressIndicator(modifier = Modifier.size(30.dp))
    }
}

@Composable
fun NetworkErrorMessage() {
    Text(
        text = stringResource(R.string.network_error),
        style = Typography.bodyLarge,
        modifier = Modifier.padding(15.dp)
    )
}