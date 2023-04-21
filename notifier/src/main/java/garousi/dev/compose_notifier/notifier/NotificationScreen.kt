package garousi.dev.compose_notifier.notifier

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import java.util.UUID
import kotlin.random.Random
import kotlinx.coroutines.channels.broadcast

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NotificationScreen(
    modifier: Modifier = Modifier
) {

    fun produceNotification(
        title: String,
        description: String,
        duration: Long = 5000
    ): Notification {
        return Notification(
            id = UUID.randomUUID().toString(),
            title = title,
            description = description,
            duration = duration
        ) { scope, notification ->
            with(scope) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
//                        .animateItemPlacement(
//
//                        )
                ) {
                    Text(text = notification.title)
                    Text(text = notification.description)
                }
            }
        }
    }


    val notifications = remember { mutableStateListOf<Notification>() }
    val notifierState = rememberNotifierState()
    ComposeNotifier(
        state = notifierState,
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            LazyColumn(
                modifier = modifier.fillMaxWidth(),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(50, key = { index -> index }) { index ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                            .padding(bottom = 8.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.DarkGray)
                    ) {
                        Box(modifier = Modifier.fillMaxSize()) {
                            Button(onClick = { }, modifier = Modifier.align(Alignment.Center)) {
                                Text(text = "Item $index")
                            }
                        }
                    }
                }
            }
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
                        val number = Random.nextInt(1, 6)
                        val notification = produceNotification(
                            "Title $number", "Description", duration = when (number) {
                                1 -> 2000
                                2 -> 3500
                                3 -> 5000
                                else -> 1000
                            }
                        )
                        notifierState.addNotification(notification)
                        notifications.add(notification)
                    }, modifier = Modifier.weight(0.5f)
                ) {
                    Text(text = "Add")
                }
                TextButton(
                    onClick = {
                        val notification = notifications.firstOrNull()
                        if (notification != null)
                            notifierState.removeNotification(notification)
                    }, modifier = Modifier.weight(0.5f)
                ) {
                    Text(text = "Remove")
                }
            }
        }
    }
}
