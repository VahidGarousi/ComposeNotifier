package garousi.dev.compose_notifier.notifier.ui

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import garousi.dev.compose_notifier.notifier.ui.model.Notification
import kotlinx.coroutines.launch

/**
 * @param [state]
 * @param [content]
 */
@Composable
fun ComposeNotifier(
    modifier: Modifier = Modifier,
    state: NotifierState,
    notificationContent: @Composable (Notification) -> Unit,
    content: @Composable BoxWithConstraintsScope.() -> Unit
) {
    val listState = rememberLazyListState()
    LaunchedEffect(state.notifications.size) {
        state.coroutineScope.launch {
            if (state.notifications.isNotEmpty()) {
                listState.animateScrollToItem(0)
            }
        }
    }
    BoxWithConstraints(
        modifier = modifier.fillMaxSize()
    ) {
        content()
        LazyColumn(
            state = listState,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            contentPadding = PaddingValues(16.dp),
            reverseLayout = true
        ) {
            items(
                items = state.notifications,
                key = { item -> item.getItemKey() }
            ) { notification ->
                notificationContent(
                    Notification(
                        id = notification.id,
                        title = notification.title,
                        description = notification.description,
                        duration = notification.duration,
                        type = notification.type
                    )
                )
            }
        }
    }
}
