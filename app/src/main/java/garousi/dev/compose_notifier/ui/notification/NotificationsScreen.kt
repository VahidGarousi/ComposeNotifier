package garousi.dev.compose_notifier.ui.notification

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import garousi.dev.compose_notifier.notifier.ui.model.Notification

@Composable
fun NotificationsScreen(
    viewModel: NotificationsViewModel = hiltViewModel()
) {
    val uiState by viewModel.viewState.collectAsStateWithLifecycle()
    NotificationsScreen(
        state = uiState,
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
fun NotificationsScreen(
    modifier: Modifier = Modifier,
    state: NotificationsViewState,
) {
    when (state) {
        is NotificationsViewState.Notifications -> Notifications(notification = state.items, modifier = modifier)
        else -> Unit
    }
}

@Composable
fun Notifications(
    notification: List<Notification>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(
            items = notification,
            key = { index -> index }
        ) { notification ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(bottom = 8.dp),
                colors = CardDefaults.cardColors(containerColor = Color.DarkGray)
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Button(
                        onClick = { },
                        modifier = Modifier.align(Alignment.Center)
                    ) {
                        Text(text = notification.title)
                    }
                }
            }
        }
    }
}
