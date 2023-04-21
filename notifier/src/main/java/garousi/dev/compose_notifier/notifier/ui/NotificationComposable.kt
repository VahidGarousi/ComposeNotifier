package garousi.dev.compose_notifier.notifier.ui

import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.runtime.Composable
import garousi.dev.compose_notifier.notifier.core.NotificationType

data class NotificationComposable(
    val id: String,
    val title: String,
    val description: String,
    val duration: Long = 5000,
    val type: NotificationType,
    val content: @Composable (LazyItemScope, NotificationComposable) -> Unit
) {
    fun getItemKey(): String = "$title/$description/$id/${type.value}"
}