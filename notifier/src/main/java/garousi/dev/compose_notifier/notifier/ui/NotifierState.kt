package garousi.dev.compose_notifier.notifier.ui

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import garousi.dev.compose_notifier.notifier.core.NotificationData
import garousi.dev.compose_notifier.notifier.core.NotificationType
import garousi.dev.compose_notifier.notifier.ui.model.Notification
import java.util.Timer
import java.util.UUID
import kotlin.concurrent.schedule
import kotlin.random.Random
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Stable
class NotifierState(
    val shouldShowToastOnAction: Boolean,
    val snackbarHost: SnackbarHostState,
    val coroutineScope: CoroutineScope,
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


    fun produce(
        title: String,
        description: String,
        duration: Long = 5000
    ): Notification {
        val number = Random.nextInt(1, 4)
        val id = UUID.randomUUID().toString()
        val type = NotificationType.from(number)
        val actions = listOf("Copy", "Delete", "Undo")
        return Notification(
            id = id,
            title = title,
            description = description,
            duration = duration,
            type = type,
            actionLabel = actions.shuffled()[Random.nextInt(0, 2)]
        ) { notification ->
            NotificationData(
                id = notification.id,
                title = notification.title,
                description = notification.description,
                duration = notification.duration,
                type = notification.type
            )
        }
    }

    fun showSnackbar(title: String, actionLabel: String? = null) {
        coroutineScope.launch {
            snackbarHost.currentSnackbarData?.dismiss()
            snackbarHost.showSnackbar(
                message = title,
                actionLabel = actionLabel,
                withDismissAction = true
            )
        }
    }
}


@Composable
fun rememberNotifierState(
    shouldShowToastOnAction: Boolean = false,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    snackbarHost: SnackbarHostState = remember { SnackbarHostState() }
): NotifierState {
    return remember(shouldShowToastOnAction, snackbarHost, coroutineScope) {
        NotifierState(
            shouldShowToastOnAction = shouldShowToastOnAction,
            snackbarHost = snackbarHost,
            coroutineScope = coroutineScope
        )
    }
}