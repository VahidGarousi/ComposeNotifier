package garousi.dev.compose_notifier.notifier

import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.runtime.Composable

data class Notification(
    val id : String,
    val title: String,
    val description: String,
    val duration: Long = 5000,
    val content: @Composable (LazyItemScope, Notification) -> Unit
) {
    fun getItemKey(): String = "$title/$description/$id"
}