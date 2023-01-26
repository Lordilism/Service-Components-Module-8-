package com.example.module8servicecomponent.services

import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.core.app.JobIntentService
import java.io.File

class DownloadJobIntentService : JobIntentService() {
    companion object {
        const val JOB_ID = 20
        
        fun startWork(context: Context, intent: Intent) {
            enqueueWork(context, DownloadJobIntentService::class.java, JOB_ID, intent)
        }
    }

    override fun onDestroy() {
        Log.d("Missing image path", "Stopping Service")
        super.onDestroy()
    }

    override fun onHandleWork(intent: Intent) {
        val imagePath = intent.getStringExtra("image_path")
        if (imagePath != null) {
            dowmloadImage(imagePath)

        } else {
            Toast.makeText(this, "File Does not exist", Toast.LENGTH_SHORT).show()
            stopSelf()

        }

    }

    private fun dowmloadImage(imagePath: String) {
        downloadImageNew(downloadUrlImage = imagePath)

    }

    private fun downloadImageNew(filename: String = "Myanmar.jpg", downloadUrlImage: String) {


        try {
            val dm = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            val downloadUrl = Uri.parse(downloadUrlImage)
            val request = DownloadManager.Request(downloadUrl)
                .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
                .setTitle(filename)
                .setAllowedOverRoaming(false)
                .setMimeType("image/jpeg")
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                .setDestinationInExternalPublicDir(
                    Environment.DIRECTORY_PICTURES,
                    File.separator + filename
                )
            dm.enqueue(request)
//            Toast.makeText(this, "Start Download", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
//            Toast.makeText(this, "Download Failed!", Toast.LENGTH_SHORT).show()
        }


    }
}