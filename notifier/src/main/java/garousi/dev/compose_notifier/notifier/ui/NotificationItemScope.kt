package garousi.dev.compose_notifier.notifier.ui

import androidx.compose.foundation.layout.LayoutScopeMarker
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable

/**
 * @property [Success]
 * @property [Warning]
 * @property [Info]
 * @property [Error]
 * @sample [DefaultNotificationItemScope]
 */
@LayoutScopeMarker
@Immutable
interface NotificationItemScope {
    @Composable
    fun Success(notification: garousi.dev.compose_notifier.notifier.core.Notification)

    @Composable

    fun Warning(notification: garousi.dev.compose_notifier.notifier.core.Notification)

    @Composable

    fun Info(notification: garousi.dev.compose_notifier.notifier.core.Notification)

    @Composable

    fun Error(notification: garousi.dev.compose_notifier.notifier.core.Notification)
}