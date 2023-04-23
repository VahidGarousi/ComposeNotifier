package garousi.dev.compose_notifier.notifier.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import garousi.dev.compose_notifier.notifier.ui.model.Notification
import java.util.Timer
import kotlin.concurrent.schedule
import kotlinx.coroutines.CoroutineScope

@Stable
class NotifierState(
    val coroutineScope: CoroutineScope
) {
    val notifications = mutableStateListOf<Notification>()
    fun add(notification: Notification) = apply {
        notifications.add(0, notification)
        Timer().schedule(notification.duration) {
            remove(notification)
        }
    }

    fun remove(notification: Notification) = apply {
        notifications.remove(notification)
    }
}


@Composable
fun rememberNotifierState(
    shouldShowToastOnAction: Boolean = false,
    coroutineScope: CoroutineScope = rememberCoroutineScope()
): NotifierState {
    return remember(shouldShowToastOnAction, coroutineScope) {
        NotifierState(coroutineScope = coroutineScope)
    }
}