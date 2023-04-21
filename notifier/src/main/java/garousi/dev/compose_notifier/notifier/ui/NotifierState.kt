package garousi.dev.compose_notifier.notifier.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateListOf
import java.util.Timer
import kotlin.concurrent.schedule

@Stable
class NotifierState {
    val notificationComposables = mutableStateListOf<NotificationComposable>()
    fun add(notificationComposable: NotificationComposable) = apply {
        notificationComposables.add(0, notificationComposable)
        Timer().schedule(notificationComposable.duration) {
            remove(notificationComposable)
        }
    }

    fun remove(notificationComposable: NotificationComposable) = apply {
        notificationComposables.remove(notificationComposable)
    }
}

@Composable
fun rememberNotifierState(): NotifierState {
    return NotifierState()
}