package garousi.dev.compose_notifier.notifier.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateListOf
import garousi.dev.compose_notifier.notifier.core.NotificationType
import java.util.Timer
import java.util.UUID
import kotlin.concurrent.schedule
import kotlin.random.Random

@Stable
class NotifierState {
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


    fun produce(
        title: String,
        description: String,
        duration: Long = 5000
    ): Notification {
        val number = Random.nextInt(1, 4)
        return Notification(
            id = UUID.randomUUID().toString(),
            title = title,
            description = description,
            duration = duration,
            type = NotificationType.from(number)
        ) { notification ->
            Notification(notification)
        }
    }
}


@Composable
fun rememberNotifierState(): NotifierState {
    return NotifierState()
}