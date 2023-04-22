package garousi.dev.compose_notifier.notifier.ui

import androidx.lifecycle.ViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class NotificationViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow<NotificationViewState>(NotificationViewState.Loading)
    val uiState = _uiState.asStateFlow()
}


sealed interface NotificationViewState {
    object Loading : NotificationViewState
    object Error : NotificationViewState
    object Success : NotificationViewState
}