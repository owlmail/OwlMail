package github.owlmail.mail.manager

import android.app.Notification
import android.app.NotificationChannel
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.graphics.Color
import android.os.Build
import androidx.core.app.NotificationManagerCompat

class NotificationManager(private val context: Context) {
    init {
        registerNotificationChannel()
        createPendingIntent()
    }

    private val notificationManager = NotificationManagerCompat.from(context)

    fun showNotification(notification: Notification) {
        notificationManager.notify(4000, notification)
    }

    private fun createPendingIntent() {
//        Intent(context,)
    }

    private fun registerNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "owlmail_notification_id",
                "owlmail_notification_channel",
                android.app.NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                lightColor = Color.BLUE
                enableLights(true)
                enableVibration(true)
            }
            val notificationManager =
                context.getSystemService(NOTIFICATION_SERVICE) as android.app.NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}
