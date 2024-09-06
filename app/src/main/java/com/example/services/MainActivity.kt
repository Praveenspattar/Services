package com.example.services

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.services.services.ForeGroundService
import android.Manifest
import android.os.Build

class MainActivity : AppCompatActivity() {

    lateinit var startBtn: Button
    lateinit var stopBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.POST_NOTIFICATIONS), 0
            )
        }

        startBtn = findViewById(R.id.startServiceBtn)
        stopBtn = findViewById(R.id.stopServiceBtn)

        startBtn.setOnClickListener {
            Intent(applicationContext,ForeGroundService::class.java).also {
                it.action = ForeGroundService.Actions.START.toString()
                startService(it)
            }
        }

        stopBtn.setOnClickListener {
            Intent(applicationContext,ForeGroundService::class.java).also {
                it.action = ForeGroundService.Actions.STOP.toString()
                startService(it)
            }
        }
    }
}