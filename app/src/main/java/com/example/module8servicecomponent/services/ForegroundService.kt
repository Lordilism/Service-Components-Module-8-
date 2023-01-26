package com.example.module8servicecomponent.services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.example.module8servicecomponent.activities.ServiceAndIntentServiceActivity
import com.example.module8servicecomponent.R


class ForegroundService : Service() {
    val OWN_CHANNEL = "OWN CHANNEL"

    companion object {
        val IE_EXTRA = "Foreground Service"

        fun startService(context: Context, message: String) {
            val start = Intent(context, ForegroundService::class.java)
            start.putExtra(IE_EXTRA, message)
            ContextCompat.startForegroundService(context,start)
        }

        fun stopService(context: Context) {
            val end = Intent(context, ForegroundService::class.java)
            context.stopService(end)
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        creatNotification()
        val input = intent?.getStringExtra(IE_EXTRA)
        val notiIntent = Intent(this, ServiceAndIntentServiceActivity::class.java)
        val pendingIntent =
            PendingIntent.getActivity(this, 0, notiIntent, PendingIntent.FLAG_IMMUTABLE)

        val notification = NotificationCompat.Builder(this,OWN_CHANNEL)
            .setContentTitle("Foreground Service")
            .setContentText(input)
            .setSmallIcon(R.drawable.ic_baseline_notifications_active_24)
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .build()

        startForeground(1, notification)
        return START_NOT_STICKY

    }

    private fun creatNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val NotificationChannel = NotificationChannel(
                OWN_CHANNEL,
                "Notification Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager!!.createNotificationChannel(NotificationChannel)
        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}