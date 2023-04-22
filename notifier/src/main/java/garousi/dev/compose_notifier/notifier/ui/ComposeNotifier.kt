package garousi.dev.compose_notifier.notifier.ui

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import garousi.dev.compose_notifier.notifier.core.NotificationData
import garousi.dev.compose_notifier.notifier.core.NotificationType
import kotlinx.coroutines.launch

/**
 * @param [state]
 * @param [notificationItemScope]
 * @param [content]
 */
@Composable
fun ComposeNotifier(
    modifier: Modifier = Modifier,
    state: NotifierState,
    notificationItemScope: NotificationItemScope? = null,
    content: @Composable BoxWithConstraintsScope.() -> Unit
) {
    val initialNotificationItemScope = remember { notificationItemScope ?: DefaultNotificationItemScope() }
    val listState = rememberLazyListState()
    val clipboardManager = LocalClipboardManager.current
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
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(16.dp),
            reverseLayout = true
        ) {
            items(
                items = state.notifications,
                key = { item -> item.getItemKey() }
            ) { notification ->
                when (notification.type) {
                    NotificationType.SUCCESS -> initialNotificationItemScope.Success(
                        notification = NotificationData(
                            id = notification.id,
                            title = notification.title,
                            description = notification.description,
                            duration = notification.duration,
                            type = notification.type
                        ),
                        onNotificationClicked = {
                            if (state.shouldShowToastOnAction) {
                                state.showSnackbar(it.title)
                            }
                        },
                        actionLabel = notification.actionLabel,
                        onActionClicked = {
                            when (notification.actionLabel) {
                                "Copy" -> {
                                    clipboardManager.setText(AnnotatedString(text = "${notification.title}/${notification.description}"))
                                    if (state.shouldShowToastOnAction) {
                                        state.showSnackbar("Copied!", actionLabel = "Close")
                                    }
                                }

                                else -> Unit
                            }
                        }
                    )

                    NotificationType.ERROR -> initialNotificationItemScope.Error(
                        notification = NotificationData(
                            id = notification.id,
                            title = notification.title,
                            description = notification.description,
                            duration = notification.duration,
                            type = notification.type
                        ),
                        onNotificationClicked = {
                            if (state.shouldShowToastOnAction) {
                                state.showSnackbar(it.title)
                            }
                        },
                        actionLabel = notification.actionLabel,
                        onActionClicked = {}

                    )

                    NotificationType.WARN -> initialNotificationItemScope.Warning(
                        notification = NotificationData(
                            id = notification.id,
                            title = notification.title,
                            description = notification.description,
                            duration = notification.duration,
                            type = notification.type
                        ),
                        onNotificationClicked = {
                            if (state.shouldShowToastOnAction) {
                                state.showSnackbar(it.title)
                            }
                        },
                        actionLabel = notification.actionLabel,
                        onActionClicked = {}

                    )

                    NotificationType.INFO -> initialNotificationItemScope.Info(
                        notification = NotificationData(
                            id = notification.id,
                            title = notification.title,
                            description = notification.description,
                            duration = notification.duration,
                            type = notification.type
                        ),
                        onNotificationClicked = {
                            if (state.shouldShowToastOnAction) {
                                state.showSnackbar(it.title)
                            }
                        },
                        actionLabel = notification.actionLabel,
                        onActionClicked = {}
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}
