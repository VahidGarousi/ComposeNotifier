package garousi.dev.compose_notifier.notifier.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import garousi.dev.compose_notifier.notifier.R
import garousi.dev.compose_notifier.notifier.core.NotificationType
import garousi.dev.compose_notifier.notifier.ui.model.Notification

/**
 * Default composable which handle [NotificationType.SUCCESS], [NotificationType.ERROR], [NotificationType.WARN], [NotificationType.INFO]
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Notification(
    modifier: Modifier = Modifier,
    notification: Notification,
    actionLabel: String? = null,
    onNotificationClicked: (Notification) -> Unit = {},
    onActionClicked: () -> Unit = {}
) {
    val icon = when (notification.type) {
        NotificationType.SUCCESS -> R.drawable.ic_success
        NotificationType.ERROR -> R.drawable.ic_error
        NotificationType.WARN -> R.drawable.ic_warning
        NotificationType.INFO -> R.drawable.ic_info
    }
    Card(modifier = modifier
        .fillMaxWidth()
        .height(76.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0XFF373E58), contentColor = Color.White),
        onClick = {
            onNotificationClicked(notification)
        }) {
        Row(
            modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.size(56.dp)) {
                Icon(
                    painter = painterResource(icon),
                    contentDescription = null,
                    tint = Color(0XFF63DD7F),
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f), verticalArrangement = Arrangement.Center
            ) {
                Text(text = notification.title, style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold))
                Text(text = notification.description)
            }
            if (actionLabel != null) {
                Button(
                    onClick = onActionClicked, modifier = Modifier.padding(end = 12.dp)
                ) {
                    Text(text = actionLabel)
                }
            }
        }
    }
}


@Composable
@Preview
fun NotificationPreview(
    @PreviewParameter(NotificationPreviewParameter::class) notification: Notification
) {
    Notification(notification = notification)
}
