package com.example.module8servicecomponent.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.module8servicecomponent.R
import com.example.module8servicecomponent.services.DownloadIntentService
import com.example.module8servicecomponent.services.DownloadJobIntentService
import kotlinx.android.synthetic.main.activity_job_intent_service.*

class JobIntentServiceActivity : AppCompatActivity() {
    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, JobIntentServiceActivity::class.java)

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_intent_service)
        btnJobIntentDownload.setOnClickListener {
            val intent = Intent(this, DownloadJobIntentService::class.java)
                .putExtra(
                    "image_path",
                    "https://starsbiog.com/wp-content/uploads/2018/06/about-sai-pallavi.jpg"
                )
            DownloadJobIntentService.startWork(this,intent)
        }
    }
}