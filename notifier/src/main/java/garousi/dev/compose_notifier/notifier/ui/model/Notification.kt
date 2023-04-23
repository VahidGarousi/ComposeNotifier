package garousi.dev.compose_notifier.notifier.ui.model

import garousi.dev.compose_notifier.notifier.core.NotificationType

/**
 *
 */
data class Notification(
    val id: String,
    val title: String,
    val description: String,
    val duration: Long = 5000,
    val type: NotificationType,
    val actionLabel: String? = null
) : ComposableItem() {
    override fun getItemKey(): String = "$title/$description/$id/${type.value}"
}