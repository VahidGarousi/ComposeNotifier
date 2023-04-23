package garousi.dev.compose_notifier.ui.main

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import garousi.dev.compose_notifier.notifier.ui.ComposeNotifier
import garousi.dev.compose_notifier.notifier.ui.Notification
import garousi.dev.compose_notifier.notifier.ui.rememberNotifierState
import garousi.dev.compose_notifier.ui.notification.SampleScreen
import garousi.dev.compose_notifier.ui.theme.NotificationManagerTheme
import kotlin.random.Random
import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

//@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val coroutineScope = rememberCoroutineScope()
            val notifierState = rememberNotifierState(
                shouldShowToastOnAction = true,
                coroutineScope = coroutineScope
            )
            val snackbarHostState = remember { SnackbarHostState() }
            NotificationManagerTheme(true) {
                Scaffold(
                    snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
                ) { padding ->
                    var ticks by remember { mutableStateOf(0) }
                    LaunchedEffect(Unit) {
                        while (true) {
                            val number = Random.nextInt(1, 6)
                            val notification = FakeDataProducer.produce(
                                title = "Title $ticks", description = "Description", duration = when (number) {
                                    1 -> 10000
                                    2 -> 12500
                                    3 -> 5000
                                    else -> 8000
                                }
                            )
                            notifierState.add(notification)
                            delay(1.seconds)
                            ticks++
                        }
                    }
                    ComposeNotifier(
                        state = notifierState,
                        notificationContent = { notification ->
                            Notification(
                                notification = notification,
                                onNotificationClicked = {
                                    coroutineScope.launch {
                                        Toast.makeText(this@MainActivity, it.title, Toast.LENGTH_SHORT).show()
                                    }
                                },
                                actionLabel = "actionLabel",
                                onActionClicked = {
                                    coroutineScope.launch {
                                        Toast.makeText(this@MainActivity, "", Toast.LENGTH_SHORT).show()
                                    }
                                },
                                modifier = Modifier.padding(vertical = 2.dp)
                            )
                        }
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(padding)
                        ) {
                            SampleScreen()
                            Row(
                                modifier = Modifier
                                    .padding(12.dp)
                                    .fillMaxWidth()
                                    .background(Color.Gray, RoundedCornerShape(24.dp))
                                    .align(Alignment.BottomCenter),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Button(
                                    onClick = {

                                    }, modifier = Modifier.weight(0.5f)
                                ) {
                                    Text(text = "Add")
                                }
                                TextButton(
                                    onClick = {

                                    }, modifier = Modifier.weight(0.5f)
                                ) {
                                    Text(text = "Remove")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!", modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NotificationManagerTheme {
        Greeting("Android")
    }
}