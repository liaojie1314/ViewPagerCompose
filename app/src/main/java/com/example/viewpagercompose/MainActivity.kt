package com.example.viewpagercompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.viewpagercompose.ui.theme.ViewPagerComposeTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

class MainActivity : ComponentActivity() {
    val items = listOf<ItemData>(
        ItemData(
            R.mipmap.pic1,
            "The prefect solution for your design needs",
            "Your happy passer-by all knows, my distressed there is no place hides."
        ),
        ItemData(
            R.mipmap.pic1,
            "The prefect solution for your design needs",
            "Your happy passer-by all knows, my distressed there is no place hides."
        ),
        ItemData(
            R.mipmap.pic1,
            "The prefect solution for your design needs",
            "Your happy passer-by all knows, my distressed there is no place hides."
        ),
        ItemData(
            R.mipmap.pic1,
            "The prefect solution for your design needs",
            "Your happy passer-by all knows, my distressed there is no place hides."
        ),
        ItemData(
            R.mipmap.pic1,
            "The prefect solution for your design needs",
            "Your happy passer-by all knows, my distressed there is no place hides."
        ),
    )

    @OptIn(ExperimentalPagerApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ViewPagerComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    val state = rememberPagerState(pageCount = items.size)
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        HorizontalPager(state = state) {
                            PagerContent(itemData = items[it])
                        }
                        Indicator(size = items.size, index = state.currentPage)
                        val width = animateDpAsState(
                            targetValue = if (state.currentPage.inc() == items.size) 60.dp else 0.dp,
                            animationSpec = if (state.currentPage.inc() == items.size)
                                spring(dampingRatio = Spring.DampingRatioHighBouncy)
                            else
                                spring(dampingRatio = Spring.DampingRatioMediumBouncy)
                        )
                        Row(
                            modifier = Modifier.height(100.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            FloatingActionButton(
                                onClick = { /*TODO*/ },
                                backgroundColor = Color.Black,
                                modifier = Modifier.size(width.value)
                            ) {
                                Icon(
                                    Icons.Default.ArrowForward,
                                    contentDescription = "",
                                    tint = Color.White
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Indicator(size: Int, index: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        repeat(size) {
            IndicatorHorizontal(isSelected = it == index)
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ViewPagerComposeTheme {
        Greeting("Android")
    }
}