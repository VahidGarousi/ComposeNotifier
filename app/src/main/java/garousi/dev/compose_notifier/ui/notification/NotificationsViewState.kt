package garousi.dev.compose_notifier.ui.notification

import garousi.dev.compose_notifier.notifier.ui.model.Notification

sealed interface NotificationsViewState {
    object Loading : NotificationsViewState
    data class Notifications(val items: List<Notification>) : NotificationsViewState
    data class Error(val error: String) : NotificationsViewState
}