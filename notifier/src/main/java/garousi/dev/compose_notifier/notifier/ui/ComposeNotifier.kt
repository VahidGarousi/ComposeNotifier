package garousi.dev.compose_notifier.notifier.ui

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import garousi.dev.compose_notifier.notifier.core.NotificationType

/**
 * @param [state]
 * @param [notificationItemScope]
 * @param [content]
 */
@Composable
fun ComposeNotifier(
    modifier: Modifier = Modifier,
    state: NotifierState,
    notificationItemScope: NotificationItemScope? = null,
    content: @Composable BoxWithConstraintsScope.() -> Unit
) {
    val initialNotificationItemScope = remember { notificationItemScope ?: DefaultNotificationItemScope() }

    BoxWithConstraints(
        modifier = modifier.fillMaxSize()
    ) {
        content()
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(
                items = state.notifications,
                key = { item -> item.getItemKey() }
            ) { notification ->
                when (notification.type) {
                    NotificationType.SUCCESS -> initialNotificationItemScope.Success(
                        notification = garousi.dev.compose_notifier.notifier.core.Notification(
                            id = notification.id,
                            title = notification.title,
                            description = notification.description,
                            duration = notification.duration,
                            type = notification.type
                        )
                    )

                    NotificationType.ERROR -> initialNotificationItemScope.Error(
                        notification = garousi.dev.compose_notifier.notifier.core.Notification(
                            id = notification.id,
                            title = notification.title,
                            description = notification.description,
                            duration = notification.duration,
                            type = notification.type
                        )
                    )

                    NotificationType.WARN -> initialNotificationItemScope.Warning(
                        notification = garousi.dev.compose_notifier.notifier.core.Notification(
                            id = notification.id,
                            title = notification.title,
                            description = notification.description,
                            duration = notification.duration,
                            type = notification.type
                        )
                    )

                    NotificationType.INFO -> initialNotificationItemScope.Info(
                        notification = garousi.dev.compose_notifier.notifier.core.Notification(
                            id = notification.id,
                            title = notification.title,
                            description = notification.description,
                            duration = notification.duration,
                            type = notification.type
                        )
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}
