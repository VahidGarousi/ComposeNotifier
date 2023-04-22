package garousi.dev.compose_notifier.notifier.ui

import androidx.compose.foundation.layout.LayoutScopeMarker
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import garousi.dev.compose_notifier.notifier.core.NotificationData

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
    fun Success(
        notification: NotificationData,
        onNotificationClicked: (NotificationData) -> Unit,
        actionLabel: String?,
        onActionClicked: () -> Unit
    )

    @Composable
    fun Warning(
        notification: NotificationData,
        onNotificationClicked: (NotificationData) -> Unit,
        actionLabel: String?,
        onActionClicked: () -> Unit
    )

    @Composable
    fun Info(
        notification: NotificationData,
        onNotificationClicked: (NotificationData) -> Unit,
        actionLabel: String?,
        onActionClicked: () -> Unit
    )

    @Composable
    fun Error(
        notification: NotificationData,
        onNotificationClicked: (NotificationData) -> Unit,
        actionLabel: String?,
        onActionClicked: () -> Unit
    )
}