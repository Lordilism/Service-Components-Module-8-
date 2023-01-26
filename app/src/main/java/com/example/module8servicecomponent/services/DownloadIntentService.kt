package com.example.module8servicecomponent.services

import android.app.DownloadManager
import android.app.IntentService
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.util.Log
import android.widget.Toast
import java.io.File

const val SERVCE_NAME = "Download"

class DownloadIntentService : IntentService(SERVCE_NAME) {
    override fun onHandleIntent(intent: Intent?) {
        val imagePath = intent?.getStringExtra("image_path")
        if (imagePath != null) {
            downloadImage(imagePath)
        } else {
            Log.d("Missing Image Path", "Stopping Service")
            stopSelf()
        }
    }

    override fun onDestroy() {
        Toast.makeText(this, "Stopping Service!", Toast.LENGTH_SHORT).show()
        super.onDestroy()
    }

    private fun downloadImage(imagePath: String) {
        downloadImageNew(downloagUrlOfImage = imagePath)

    }

    private fun downloadImageNew(
        filename: String = "PADC MYANMAR.jpg",
        downloagUrlOfImage: String
    ) {
        try {
            val dm: DownloadManager = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            val downloadUrl = Uri.parse(downloagUrlOfImage)
            val request = DownloadManager.Request(downloadUrl)

            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
                .setAllowedOverRoaming(false)
                .setTitle(filename)
                .setMimeType("image/jpg")
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                .setDestinationInExternalPublicDir(
                    Environment.DIRECTORY_PICTURES,
                    File.separator + filename
                )
            dm.enqueue(request)
            Toast.makeText(this, "Start Downloading", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Toast.makeText(this, "Download failed", Toast.LENGTH_SHORT).show()
        }


    }
}