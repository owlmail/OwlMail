package github.owlmail.mail.manager

import android.app.Notification
import android.app.NotificationChannel
import android.app.PendingIntent
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.core.app.NotificationManagerCompat
import androidx.core.net.toUri

class NotificationManager(private val context: Context) {
    init {
        registerNotificationChannel()
    }

    private val notificationManager = NotificationManagerCompat.from(context)

    fun showNotification(notificationId: Int, notification: Notification) {
        notification.contentIntent = getPendingIntent()
        notificationManager.notify(notificationId, notification)
    }

    private fun getPendingIntent(): PendingIntent {
        val intent = Intent(Intent.ACTION_VIEW)

        intent.setData("https://mail.nitrkl.ac.in/".toUri())
// Create the TaskStackBuilder
        val pendingIntent =
            PendingIntent.getActivity(
                context,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE,
            )
        return pendingIntent
    }

    private fun registerNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "owlmail_notification_id",
                "owlmail_notification_channel",
                android.app.NotificationManager.IMPORTANCE_DEFAULT,
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
