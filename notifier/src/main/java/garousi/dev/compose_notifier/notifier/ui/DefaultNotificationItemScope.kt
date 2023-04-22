package garousi.dev.compose_notifier.notifier.ui

import androidx.compose.runtime.Composable

/**
 * @see NotificationItemScope
 */
class DefaultNotificationItemScope : NotificationItemScope {
    @Composable
    override fun Success(notification: garousi.dev.compose_notifier.notifier.core.Notification) {
        Notification(notification = notification)
    }

    @Composable

    override fun Warning(notification: garousi.dev.compose_notifier.notifier.core.Notification) {
        Notification(notification = notification)
    }

    @Composable

    override fun Info(notification: garousi.dev.compose_notifier.notifier.core.Notification) {
        Notification(notification = notification)
    }

    @Composable
    override fun Error(notification: garousi.dev.compose_notifier.notifier.core.Notification) {
        Notification(notification = notification)
    }

}