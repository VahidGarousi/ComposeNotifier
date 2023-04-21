package garousi.dev.compose_notifier.notifier.ui

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import garousi.dev.compose_notifier.notifier.core.Notification
import garousi.dev.compose_notifier.notifier.core.NotificationType
import java.util.UUID

class NotificationPreviewParameter : PreviewParameterProvider<Notification> {
    override val values: Sequence<Notification>
        get() = sequenceOf(
            Notification(
                id = UUID.randomUUID().toString(),
                title = "Success",
                description = "This is description",
                duration = 5000,
                type = NotificationType.SUCCESS
            ),
            Notification(
                id = UUID.randomUUID().toString(),
                title = "Error",
                description = "This is description",
                duration = 5000,
                type = NotificationType.ERROR
            ),
            Notification(
                id = UUID.randomUUID().toString(),
                title = "Warning",
                description = "This is description",
                duration = 5000,
                type = NotificationType.WARN
            ),
            Notification(
                id = UUID.randomUUID().toString(),
                title = "Info",
                description = "This is description",
                duration = 5000,
                type = NotificationType.INFO
            )
        )
}