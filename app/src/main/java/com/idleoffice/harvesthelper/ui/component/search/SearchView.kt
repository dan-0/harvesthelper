package com.idleoffice.harvesthelper.ui.component.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.idleoffice.harvesthelper.R

@Composable
fun SearchView(state: MutableState<TextFieldValue>) {
    Box(
        modifier = Modifier.padding(15.dp, 15.dp, 15.dp, 0.dp),
    ) {
        Surface(
            elevation = 2.dp,
            shape = RoundedCornerShape(8.dp)
        ) {
            TextField(
                value = state.value,
                onValueChange = { value: TextFieldValue ->
                    state.value = value
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("searchView"),
                textStyle = TextStyle(color = Color.Black, fontSize = 18.sp),
                leadingIcon = { SearchIcon() },
                trailingIcon = { SearchTrailingIcon(state) },
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(
                    cursorColor = MaterialTheme.colors.primary,
                    leadingIconColor = MaterialTheme.colors.primaryVariant,
                    backgroundColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                )
            )
        }
    }
}

@Composable
private fun SearchIcon() {
    Icon(
        Icons.Default.Search,
        contentDescription = stringResource(R.string.search),
        modifier = Modifier
            .padding(15.dp)
            .size(24.dp)
    )
}

@Composable
private fun SearchTrailingIcon(state: MutableState<TextFieldValue>) {
    if (state.value.text.isNotEmpty()) {
        IconButton(
            onClick = {
                state.value =
                    TextFieldValue("")
            }
        ) {
            Icon(
                Icons.Default.Close,
                contentDescription = stringResource(R.string.close),
                modifier = Modifier
                    .padding(15.dp)
                    .size(24.dp),
                tint = MaterialTheme.colors.primaryVariant
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SearchViewPreview() {
    val textState = remember { mutableStateOf(TextFieldValue("")) }
    SearchView(textState)
}