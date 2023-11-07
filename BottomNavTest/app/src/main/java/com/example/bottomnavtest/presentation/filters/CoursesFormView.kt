package com.example.bottomnavtest.presentation.filters


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bottomnavtest.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoursesFormView(
    navController: NavController
) {
    val list = listOf(
        "1 курс",
        "2 курс",
        "3 курс",
        "4 курс"
    )
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    ProvideTextStyle(MaterialTheme.typography.titleMedium) {
                        Text(
                            text = "Курсы",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = colorScheme.primary
                )
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                verticalArrangement = Arrangement.Center
            ) {
                LazyVerticalGrid(
                    modifier = Modifier.padding(bottom = 64.dp),
                    columns = GridCells.Adaptive(256.dp),
                    content = {
                        items(list.size) { index ->
                            Card(
                                border = BorderStroke(2.dp, colorScheme.primary),
                                modifier = Modifier
                                    .padding(vertical = 8.dp, horizontal = 32.dp)
                                    .clip(shape = RectangleShape)
                                    .clickable {
                                        navController.navigate(Screen.Groups.route)
                                    },
                                colors = CardDefaults.cardColors(
                                    containerColor = Color.Transparent
                                )
                            ) {
                                ProvideTextStyle(typography.titleLarge) {
                                    Text(
                                        text = list[index],
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier
                                            .padding(14.dp)
                                            .fillMaxWidth()
                                    )
                                }
                            }
                        }
                    }
                )
            }
        }
    )
}