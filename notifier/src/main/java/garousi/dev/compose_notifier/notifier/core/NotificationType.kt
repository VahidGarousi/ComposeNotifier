package garousi.dev.compose_notifier.notifier.core

enum class NotificationType(val value: Int) {
    SUCCESS(1),
    ERROR(2),
    WARN(3),
    INFO(4);

    companion object {
        infix fun from(value: Int): NotificationType = values().firstOrNull { it.value == value } ?: throw IllegalArgumentException("Are you sure?")
    }
}