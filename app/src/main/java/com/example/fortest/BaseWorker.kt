package com.example.fortest

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.fortest.R
import com.example.fortest.SecondActivity

private const val CHANNEL_ID = "channel_id"

class BaseWorker(private val ctx: Context, params: WorkerParameters) : Worker(ctx, params) {


    override fun doWork(): Result {
        // Set the notification's tap action
        val pendingIntent: PendingIntent = PendingIntent.getActivity(
            ctx, 0, Intent(ctx, SecondActivity::class.java), 0
        )

        // Set the notification content
        val builder = NotificationCompat.Builder(ctx, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_baseline_airport_shuttle)
            .setContentTitle(ctx.getString(R.string.new_bus))
            .setContentText(ctx.getString(R.string.mess_notification))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        // Show Notification
        with(NotificationManagerCompat.from(ctx)) {
            notify(1, builder.build())
        }


        // THIS CODE is related of work manager
//        // Get input data from main activity
//        val data = inputData.getString("KEY")
//        // Confirm we've received input data
//        Log.d(TAG, "onCreate: $data")
//
//        // Create data object to confirm the output data finished successfully
//        val result = Data.Builder()
//            .putString("KEY_OUTPUT", "Data updated successfully")
//            .build()
        return Result.success()
    }
}