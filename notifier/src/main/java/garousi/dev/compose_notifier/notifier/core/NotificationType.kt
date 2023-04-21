package garousi.dev.compose_notifier.notifier.core

enum class NotificationType(val value: Int) {
    SUCCESS(1),
    ERROR(2),
    WARN(3),
    INFO(4)
}