package garousi.dev.compose_notifier.notifier.core

data class NotificationData(
    val id : String,
    val title: String,
    val description: String,
    val duration: Long,
    val type: NotificationType
)



