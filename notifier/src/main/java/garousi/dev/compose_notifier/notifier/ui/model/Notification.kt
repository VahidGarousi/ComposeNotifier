package garousi.dev.compose_notifier.notifier.ui.model

import androidx.compose.runtime.Composable
import garousi.dev.compose_notifier.notifier.core.NotificationData
import garousi.dev.compose_notifier.notifier.core.NotificationType
import garousi.dev.compose_notifier.notifier.ui.NotificationItemScope

/**
 *
 */
data class Notification(
    val id: String,
    val title: String,
    val description: String,
    val duration: Long = 5000,
    val type: NotificationType,
    val actionLabel : String? = null,
    val content: @Composable NotificationItemScope.(NotificationData) -> Unit
) : ComposableItem() {
    override fun getItemKey(): String = "$title/$description/$id/${type.value}"
}