package com.example.module8servicecomponent.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.module8servicecomponent.R
import com.example.module8servicecomponent.services.DownloadIntentService
import com.example.module8servicecomponent.services.ForegroundService
import kotlinx.android.synthetic.main.activity_service_and_intent_service.*

class ServiceAndIntentServiceActivity : AppCompatActivity() {
    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, ServiceAndIntentServiceActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service_and_intent_service)
        btnStartService.setOnClickListener {
            ForegroundService.startService(this, "Testing Service")
        }
        btnStopService.setOnClickListener {
            ForegroundService.stopService(this)
        }
        btnDownload.setOnClickListener {
            val intent = Intent(this, DownloadIntentService::class.java)
            intent.putExtra(
                "image_path",
                "https://starsbiog.com/wp-content/uploads/2018/06/about-sai-pallavi.jpg"
            )
            startService(intent)
        }


    }
}