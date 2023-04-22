package garousi.dev.compose_notifier.notifier.ui

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import garousi.dev.compose_notifier.notifier.core.NotificationData
import garousi.dev.compose_notifier.notifier.core.NotificationType
import java.util.UUID

class NotificationPreviewParameter : PreviewParameterProvider<NotificationData> {
    override val values: Sequence<NotificationData>
        get() = sequenceOf(
            NotificationData(
                id = UUID.randomUUID().toString(),
                title = "Success",
                description = "This is description",
                duration = 5000,
                type = NotificationType.SUCCESS
            ),
            NotificationData(
                id = UUID.randomUUID().toString(),
                title = "Error",
                description = "This is description",
                duration = 5000,
                type = NotificationType.ERROR
            ),
            NotificationData(
                id = UUID.randomUUID().toString(),
                title = "Warning",
                description = "This is description",
                duration = 5000,
                type = NotificationType.WARN
            ),
            NotificationData(
                id = UUID.randomUUID().toString(),
                title = "Info",
                description = "This is description",
                duration = 5000,
                type = NotificationType.INFO
            )
        )
}