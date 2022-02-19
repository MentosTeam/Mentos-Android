package com.mentos.mentosandroid.data.api

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.mentos.mentosandroid.R
import com.mentos.mentosandroid.data.local.SharedPreferenceController
import com.mentos.mentosandroid.ui.main.AuthActivity
import com.mentos.mentosandroid.ui.main.MainActivity

class MentosFireBaseMessagingService : FirebaseMessagingService() {
    private val pushTag = "PushNotification"

    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        if (remoteMessage.data.isNotEmpty()) {
            if (Integer.parseInt(remoteMessage.data["receiverFlag"]!!) == 1) {
                if (SharedPreferenceController.getAgreementPush(0)) {
                    sendNotification(
                        remoteMessage.data["title"],
                        remoteMessage.data["body"]!!,
                        0
                    )
                }
            } else {
                if (SharedPreferenceController.getAgreementPush(1)) {
                    sendNotification(
                        remoteMessage.data["title"],
                        remoteMessage.data["body"]!!,
                        1
                    )
                }
            }
        }
    }

    // Firebase Cloud Messaging Server 가 대기중인 메세지를 삭제 시 호출
    override fun onDeletedMessages() {
        super.onDeletedMessages()
    }

    // 메세지가 서버로 전송 성공 했을때 호출
    override fun onMessageSent(p0: String) {
        super.onMessageSent(p0)
    }

    // 메세지가 서버로 전송 실패 했을때 호출
    override fun onSendError(p0: String, p1: Exception) {
        super.onSendError(p0, p1)
    }

    private fun sendNotification(title: String?, body: String, nowState: Int) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("isMentoring", true)
        intent.putExtra("nowState", nowState)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(
            this, System.currentTimeMillis().toInt(), intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        val channelId = "channel"
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setContentTitle(title)
            .setContentText(body)
            .setAutoCancel(true)
            .setSmallIcon(R.mipmap.ic_mentos_logo)
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setSound(defaultSoundUri)
            .setContentIntent(pendingIntent)

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Channel human readable title",
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationManager.createNotificationChannel(channel)
        }

        notificationManager.notify(System.currentTimeMillis().toInt(), notificationBuilder.build())
    }
}
