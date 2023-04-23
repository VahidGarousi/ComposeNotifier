package garousi.dev.compose_notifier.ui.main

import garousi.dev.compose_notifier.notifier.core.NotificationType
import garousi.dev.compose_notifier.notifier.ui.model.Notification
import java.util.UUID
import kotlin.random.Random

object FakeDataProducer {
    internal fun produce(
        title: String,
        description: String,
        duration: Long = 5000
    ): Notification {
        val number = Random.nextInt(1, 4)
        val id = UUID.randomUUID().toString()
        val type = NotificationType.from(number)
        val actions = listOf("Copy", "Delete", "Undo")
        return Notification(
            id = id,
            title = title,
            description = description,
            duration = duration,
            type = type,
            actionLabel = actions.shuffled()[Random.nextInt(0, 2)]
        )
    }
}