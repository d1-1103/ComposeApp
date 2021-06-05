package com.urtech.composeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.urtech.composeapp.ui.theme.ComposeAppTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    @ExperimentalFoundationApi
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
//                    Greeting("Android")
//                    Drag()
//                    SwipeableSample()
//                    TransformableSample()
                    search()
                }
            }
        }
    }
}

@Composable
fun search() {
    var searchResult = remember {
        mutableListOf<String>("a", "b", "c", "d", "e")
    }
    Column(modifier = Modifier.padding(10.dp)) {
        TextField(value = "input text", onValueChange = {
            searchResult = search(it)
        })
        LazyColumn {
            items(searchResult) {
                Text(it, modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp))
            }
        }
    }
}

fun search(text: String): MutableList<String> {
    return mutableListOf<String>("a", "b", "c", "d", "e")
}

fun flow() {

    runBlocking {

        repeat(Int.MAX_VALUE) {
            delay(50)
        }
        val flow: Flow<Int> = flow {
            (1..100).forEach {
                delay(1000L)
                emit(it)
            }
        }
        flow.collect { println(it) }

        var x = 0


    }
}

@Composable
fun Greeting(name: String) {
    val count: MutableState<Int> = remember {
        mutableStateOf(0)
    }

    var offset by remember { mutableStateOf(0f) }
    val state = rememberScrollState()
    LaunchedEffect(Unit) { state.animateScrollTo(count.value * 100) }


    Column {
        Row(verticalAlignment = Alignment.Top, modifier = Modifier.padding(5.dp)) {
            Text(text = "x=", modifier = Modifier.padding(top = 10.dp))
            Spacer(modifier = Modifier.width(10.dp))
            Column(modifier = Modifier.padding(5.dp)) {

                Box(contentAlignment = Alignment.BottomEnd) {
                    Button(
                        onClick = { count.value++ }) {
                        Text(text = "click me", fontSize = 15.sp)
                    }
//                    Text(text = "hei")
                }

                Spacer(modifier = Modifier.padding(5.dp))
                Text(
                    text = "Hello ${offset}!", fontSize = 18.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                )

            }
        }
        Row {
            Column(Modifier.verticalScroll(state)) {
                repeat(120) {
                    Text(text = "Text$it", modifier = Modifier.padding(5.dp))
                }
            }
            LazyColumn() {
                items(120) { index ->
                    Text(text = "Item: $index", modifier = Modifier.padding(5.dp))
                }
            }
        }
    }
}

@Composable
fun Drag() {
    Box(modifier = Modifier.fillMaxSize()) {
        var offsetX by remember { mutableStateOf(0f) }
        var offsetY by remember { mutableStateOf(0f) }
        Box(modifier = Modifier
            .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
            .background(Color.Blue)
            .size(50.dp)
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    change.consumeAllChanges()
                    offsetX += dragAmount.x
                    offsetY += dragAmount.y
                }
            }
        )
    }
}

/**
 * 滑动
 */
@ExperimentalMaterialApi
@Composable
fun SwipeableSample() {
    val width = 96.dp
    val squareSize = 48.dp

    val swipeableState = rememberSwipeableState(0)
    val sizePx = with(LocalDensity.current) { squareSize.toPx() }
    val anchors = mapOf(0f to 0, sizePx to 1) // Maps anchor points (in px) to states

    Box(
        modifier = Modifier
            .width(width)
            .swipeable(
                state = swipeableState,
                anchors = anchors,
                thresholds = { _, _ -> FractionalThreshold(0.3f) },
                orientation = Orientation.Horizontal
            )
            .background(Color.LightGray)
    ) {
        Box(
            Modifier
                .offset { IntOffset(swipeableState.offset.value.roundToInt(), 0) }
                .size(squareSize)
                .background(Color.DarkGray)
        )
    }
}

/**
 * 多点触控：平移、缩放、旋转
 */
@ExperimentalFoundationApi
@Composable
fun TransformableSample() {
    // set up all transformation states
    var scale by remember { mutableStateOf(1f) }
    var rotation by remember { mutableStateOf(0f) }
    var offset by remember { mutableStateOf(Offset.Zero) }
    val state = rememberTransformableState { zoomChange, offsetChange, rotationChange ->
        scale *= zoomChange
        rotation += rotationChange
        offset += offsetChange
    }

    Box(
        Modifier
            // apply other transformations like rotation and zoom
            // on the pizza slice emoji
            .graphicsLayer(
                scaleX = scale,
                scaleY = scale,
                rotationZ = rotation,
                translationX = offset.x,
                translationY = offset.y
            )
            // add transformable to listen to multitouch transformation events
            // after offset
            .transformable(state = state)
            .background(Color.White)
            .fillMaxSize()
    ) {
        val list = listOf("aaa", "bbbb", "cc", "dd", "ee", "cbc", "cdc")
        LazyColumn {
            list.forEach { item ->
                stickyHeader {
                    Text(
                        text = "我是头部${item.first()}",
                        Modifier
                            .fillMaxWidth()
                            .align(Alignment.Center)
                            .height(20.dp),
                        color = Color.Red
                    )
                }
                items(3) {
                    Text(
                        text = "index$it",
                        Modifier
                            .fillMaxWidth()
                            .align(Alignment.Center)
                            .height(40.dp),
                        color = Color.Black
                    )
                }
            }

        }
//        Image(
//            painter = painterResource(id = R.drawable.colmc),
//            contentDescription = null,
//            modifier = Modifier
//                .size(30.dp)
//                .clip(CircleShape)
//                .border(
//                    shape = CircleShape,
//                    border = BorderStroke(
//                        2.dp, Brush.linearGradient(
//                            colors = listOf(Color.Red),
//                            start = Offset(0f, 0f),
//                            end = Offset(100f, 100f)
//                        )
//                    )
//                )
//        )
    }
}

@Composable
fun MyApp(child: @Composable () -> Unit) {
    MaterialTheme {
        Surface {
            child()
        }
    }
}

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeAppTheme {
//        Greeting("Android")
//        Drag()
//        SwipeableSample()
//        TransformableSample()
        search()
    }
}