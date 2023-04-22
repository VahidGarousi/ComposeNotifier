package garousi.dev.compose_notifier.notifier.ui

import androidx.compose.runtime.Composable
import garousi.dev.compose_notifier.notifier.core.NotificationData

/**
 * @see NotificationItemScope
 */
class DefaultNotificationItemScope : NotificationItemScope {
    @Composable
    override fun Success(
        notification: NotificationData,
        onNotificationClicked: (NotificationData) -> Unit,
        actionLabel: String?,
        onActionClicked: () -> Unit
    ) {
        Notification(
            notification = notification,
            onNotificationClicked = onNotificationClicked,
            actionLabel = actionLabel,
            onActionClicked = onActionClicked
        )
    }

    @Composable
    override fun Warning(
        notification: NotificationData,
        onNotificationClicked: (NotificationData) -> Unit,
        actionLabel: String?,
        onActionClicked: () -> Unit
    ) {
        Notification(
            notification = notification,
            onNotificationClicked = onNotificationClicked,
            actionLabel = actionLabel,
            onActionClicked = onActionClicked
        )
    }

    @Composable
    override fun Info(
        notification: NotificationData,
        onNotificationClicked: (NotificationData) -> Unit,
        actionLabel: String?,
        onActionClicked: () -> Unit
    ) {
        Notification(
            notification = notification,
            onNotificationClicked = onNotificationClicked,
            actionLabel = actionLabel,
            onActionClicked = onActionClicked
        )
    }

    @Composable
    override fun Error(
        notification: NotificationData,
        onNotificationClicked: (NotificationData) -> Unit,
        actionLabel: String?,
        onActionClicked: () -> Unit
    ) {
        Notification(
            notification = notification,
            onNotificationClicked = onNotificationClicked,
            actionLabel = actionLabel,
            onActionClicked = onActionClicked
        )
    }
}