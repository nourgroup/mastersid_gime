package fr.mastersid.oummadi.stackoverflow.Worker

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.work.ForegroundInfo
import androidx.work.WorkManager
import fr.mastersid.oummadi.stackoverflow.R
import java.util.*

const val NOTIFICATION_UPDATE_DONE_ID = 1
const val NOTIFICATION_START_UPDATE_ID = 2

fun NotificationManager.sendNotificationUpdateDone(appContext: Context,titre :String, texte : String) {
    val notification = NotificationCompat.Builder(
        appContext,
        appContext.getString(R.string.notification_channel_id)
    ).setSmallIcon(R.drawable.ic_baseline_send_24)
        .setContentTitle(titre)
        .setContentText(texte)
        .build()
    Log.d("NOTIFICATION","notify_me_h")
    notify(NOTIFICATION_UPDATE_DONE_ID,notification)
    Log.d("NOTIFICATION","notify_me_hh")
}

fun NotificationManager.createChannel(context:Context) {

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        Log.d("NOTIFICATION","VERSION CODE")
        val notificationChannel = NotificationChannel(
            context.getString(R.string.notification_channel_id),
            context.getString(R.string.notification_channel_name),
            NotificationManager.IMPORTANCE_DEFAULT
        ).apply{
            //setShowBadge(true)
            //enableLights(true)
            //lightColor = Color.RED
            //enableVibration(true)
            description = context.getString(R.string.notification_channel_description)
        }
        createNotificationChannel(notificationChannel)
        Log.d("NOTIFICATION","createChannel")
    }
}

object NotificationUtils {
    fun createForegroundInfo (workerId : UUID,appContext : Context):ForegroundInfo{
        val notification = NotificationCompat
            .Builder(
            appContext,
            appContext.getString(R.string.notification_channel_id)).setContentTitle(appContext.getString(R.string.app_name)
            )
            .setContentText(appContext.getString (R.string.app_name))
            .setSmallIcon(R.drawable.ic_baseline_send_24)
            .setOngoing(true)
            .addAction(
                android.R.drawable.ic_delete,
                appContext.getString(R.string.notification_cancel),
                WorkManager.getInstance(appContext).createCancelPendingIntent(workerId)
            )
            .build()
        return ForegroundInfo ( NOTIFICATION_START_UPDATE_ID , notification )
    }
}