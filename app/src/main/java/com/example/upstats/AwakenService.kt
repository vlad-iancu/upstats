package com.example.upstats

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.os.SystemClock
import com.example.upstats.IAwakeInterface

class AwakenService : Service() {

    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    private val binder = object : IAwakeInterface.Stub() {
        override fun getUptime(): Long {
            // Do something
            return SystemClock.elapsedRealtime();
        }

        //override basicTypes function
        override fun basicTypes(anInt: Int, aLong: Long, aBoolean: Boolean, aFloat: Float, aDouble: Double, aString: String?) {
            // Do something
        }
    }
}

