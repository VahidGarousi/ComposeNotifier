package garousi.dev.compose_notifier.notifier

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Modifier
import java.util.Timer
import kotlin.concurrent.schedule

@Stable
class NotifierState {
    val notifications = mutableStateListOf<Notification>()
    fun addNotification(notification: Notification) = apply {
        notifications.add(0, notification)
        Timer().schedule(notification.duration) {
            removeNotification(notification)
        }
    }

    fun removeNotification(notification: Notification) = apply {
        notifications.remove(notification)
    }
}

@Composable
fun rememberNotifierState(): NotifierState {
    return NotifierState()
}


@Composable
fun ComposeNotifier(
    modifier: Modifier = Modifier,
    state: NotifierState,
    content: @Composable BoxWithConstraintsScope.() -> Unit
) {
    BoxWithConstraints(
        modifier = modifier.fillMaxSize()
    ) {
        content()
        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(
                items = state.notifications,
                key = { item -> item.getItemKey() }
            ) { notification ->
                notification.content(this, notification)
            }
        }
    }
}
