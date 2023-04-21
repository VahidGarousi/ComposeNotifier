package garousi.dev.compose_notifier.notifier.ui

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ComposeNotifier(
    modifier: Modifier = Modifier,
    state: NotifierState,
    content: @Composable BoxWithConstraintsScope.() -> Unit
) {
    BoxWithConstraints(
        modifier = modifier.fillMaxSize()
    ) {
        content()
        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(
                items = state.notificationComposables,
                key = { item -> item.getItemKey() }
            ) { notification ->
                notification.content(this, notification)
            }
        }
    }
}
