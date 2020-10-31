package com.appinc.cocochat

import android.app.Application

import com.github.nkzawa.socketio.client.IO
import com.github.nkzawa.socketio.client.Socket

class App : Application() {

    companion object {
        lateinit var socket: Socket
    }

    override fun onCreate() {
        super.onCreate()

        try {
            socket = IO.socket("http://192.168.1.46:3000")
            socket.connect()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }
}