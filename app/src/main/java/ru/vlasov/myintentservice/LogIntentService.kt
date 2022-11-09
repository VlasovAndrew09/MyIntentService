package ru.vlasov.myintentservice

import android.content.Context
import android.content.Intent
import android.util.Log
import java.util.concurrent.TimeUnit

class LogIntentService : MyIntentService("LogIntentService") {

    override fun onHandleIntent(intent: Intent?) {
        intent?.getStringExtra("task").let { task ->
            Log.d(TAG, "start $task")
            TimeUnit.SECONDS.sleep(1)
            Log.d(TAG, "end $task")
        }
    }

    companion object {
        private const val TAG = "LogIntentService"
        private const val TASK_EXTRA_KEY = "task"

        fun startLogIntentService(context: Context, task: String) {
            val logIntentService = Intent(context, LogIntentService::class.java)
            logIntentService.putExtra(TASK_EXTRA_KEY, task)
            context.startService(logIntentService)
        }
    }
}