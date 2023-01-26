package com.example.module8servicecomponent.workers

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import androidx.work.Worker
import androidx.work.WorkerParameters

class DownloadWorker(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {
    private val context = context
    override fun doWork(): Result {
        try {
            val request =
                DownloadManager.Request(Uri.parse("https://starsbiog.com/wp-content/uploads/2018/06/about-sai-pallavi.jpg"))
            request.apply {
                setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE or DownloadManager.Request.NETWORK_WIFI)
                setTitle("Download Image")
                setDescription("Downloading")
                setMimeType("image/jpg")
                setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                setDestinationInExternalPublicDir(
                    Environment.DIRECTORY_DOWNLOADS,
                    "downloaded Image"
                )

                val downloadManager =
                    context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
                downloadManager.enqueue(request)
            }
            return Result.success()
        } catch (e: Exception) {
            return Result.failure()
        }
    }
}