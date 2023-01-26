package com.example.module8servicecomponent.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.module8servicecomponent.R
import com.example.module8servicecomponent.workers.DownloadWorker
import kotlinx.android.synthetic.main.activity_image_download.*

class ImageDownloadActivity : AppCompatActivity() {
    companion object{
        fun newIntent(context: Context): Intent {
            return Intent(context,ImageDownloadActivity::class.java)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_download)
        btnDownload.setOnClickListener {
            //Constraints
            val constraint = Constraints.Builder()

                .setRequiredNetworkType(NetworkType.NOT_ROAMING)
                .setRequiresBatteryNotLow(true)
                .setRequiresStorageNotLow(true)
                .build()


            //work request
            val downloadWorkRequest = OneTimeWorkRequestBuilder<DownloadWorker>()
                .setConstraints(constraint)
                .build()

            //Enqueue Work Manager
            WorkManager.getInstance(applicationContext).enqueue(downloadWorkRequest)

        }
    }
}