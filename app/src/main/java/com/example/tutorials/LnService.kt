package com.example.tutorials

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class LnService : Service() {

    val TAG = "LnService"

    init {
        Log.d(TAG, "LnService initialized")
    }

    override fun onBind(p0: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }
}