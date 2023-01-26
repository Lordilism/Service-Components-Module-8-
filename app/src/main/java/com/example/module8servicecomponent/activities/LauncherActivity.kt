package com.example.module8servicecomponent.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.module8servicecomponent.R
import kotlinx.android.synthetic.main.activity_launcher.*

class LauncherActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher)
        btnServiceAndIntentService.setOnClickListener {
            val intent = Intent(ServiceAndIntentServiceActivity.newIntent(this))
            startActivity(intent)
        }
        btnJobIntentService.setOnClickListener {
            val intent = Intent(JobIntentServiceActivity.newIntent(this))
            startActivity(intent)
        }
        btnWorkManager.setOnClickListener {
            val intent = Intent(ImageDownloadActivity.newIntent(this))
            startActivity(intent)
        }
    }
}