package garousi.dev.compose_notifier.ui.notification

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class NotificationsViewModel @Inject constructor(

) : ViewModel() {
    private val _viewState: MutableStateFlow<NotificationsViewState> = MutableStateFlow(NotificationsViewState.Loading)
    val viewState = _viewState.asStateFlow()

}