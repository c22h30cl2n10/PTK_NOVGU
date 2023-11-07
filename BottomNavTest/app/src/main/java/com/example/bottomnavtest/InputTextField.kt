package com.example.bottomnavtest

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun InputTextField(
    placeholder: String,
    state: String,
) {
    var value by remember {
        mutableStateOf("")
    }
    BasicTextField(
        value = state,
        onValueChange = { newText ->
            value = newText
        },
        textStyle = TextStyle(
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            color = Color.DarkGray
        ),
        singleLine = true,
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .padding()
                    .fillMaxWidth()
                    .background(
                        color = Color.Transparent,
                        shape = RoundedCornerShape(size = 32.dp)
                    )
                    .border(
                        width = 2.dp,
                        color = Color(0xFF002CA5),
                        shape = RoundedCornerShape(size = 32.dp)
                    )
                    .padding(all = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "",
                    tint = Color.DarkGray
                )
                Spacer(modifier = Modifier.width(width = 8.dp))
                if (value.isEmpty()) {
                    Text(
                        text = placeholder,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.LightGray
                    )
                }
                innerTextField()
            }
        }
    )
}