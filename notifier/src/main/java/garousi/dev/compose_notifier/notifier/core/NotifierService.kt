package garousi.dev.compose_notifier.notifier.core

interface NotifierService {
    fun onReceive(notification: Notification)
}