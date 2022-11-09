package ru.vlasov.myintentservice

import android.app.Service
import android.content.Intent
import android.os.*

abstract class MyIntentService(name: String) : Service() {

    private val handlerThread: HandlerThread = HandlerThread(name)
    private var serviceHandler: ServiceHandler? = null

    private inner class ServiceHandler(looper: Looper) : Handler(looper) {
        override fun handleMessage(msg: Message) {
            onHandleIntent(msg.obj as Intent)
            stopSelf(msg.arg1)
        }
    }

    override fun onBind(intent: Intent?): IBinder? = null

    abstract fun onHandleIntent(intent: Intent?)

    override fun onCreate() {
        super.onCreate()
        handlerThread.start()
        serviceHandler = ServiceHandler(handlerThread.looper)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        serviceHandler?.obtainMessage()?.also { message ->
            message.arg1 = startId
            message.obj = intent
            serviceHandler?.sendMessage(message)
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        handlerThread.quit()
        super.onDestroy()
    }
}