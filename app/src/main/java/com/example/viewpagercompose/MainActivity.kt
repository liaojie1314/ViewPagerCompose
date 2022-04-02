package com.example.viewpagercompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.viewpagercompose.ui.theme.ViewPagerComposeTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import java.util.*
import kotlin.concurrent.schedule

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
                    val loading = remember {
                        mutableStateOf(true)
                    }
                    val pagerstate = rememberPagerState(pageCount = items.size)

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Timer().schedule(1000){
                            loading.value=false
                        }
                        HorizontalPager(
                            state = pagerstate,
                            modifier = Modifier
                                .padding(12.dp)
                                .weight(1f)
                        ) {
                            PagerContent(itemData = items[it])
                        }
                        CustomCircularProgress(indicatorValue =if (loading.value)360 else pagerstate.currentPage.inc() * (360 / items.size))
                    }
                }
                /* Vertical
                {
                    val state = rememberPagerState(pageCount = items.size)
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            VerticalPager(state = state, modifier = Modifier.weight(1f)) {
                                PagerContent(itemData = items[it])
                            }
                            VerticalIndicator(size = items.size, index = state.currentPage)
                            Spacer(modifier = Modifier.width(12.dp))
                        }
                    }
                }
                 */
                /* Horizontal
                    {
                        val state = rememberPagerState(pageCount = items.size)
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            HorizontalPager(state = state) {
                                PagerContent(itemData = items[it])
                            }
                            HorizontalIndicator(size = items.size, index = state.currentPage)
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
                    } */
            }
        }
    }
}

@Composable
fun HorizontalIndicator(size: Int, index: Int) {
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
fun VerticalIndicator(size: Int, index: Int) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        repeat(size) {
            IndicatorVertical(isSelected = it == index)
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