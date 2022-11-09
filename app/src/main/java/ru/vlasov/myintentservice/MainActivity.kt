package ru.vlasov.myintentservice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        LogIntentService.startLogIntentService(this, "task 1")
        LogIntentService.startLogIntentService(this, "task 2")
        LogIntentService.startLogIntentService(this, "task 3")
        LogIntentService.startLogIntentService(this, "task 4")
        LogIntentService.startLogIntentService(this, "task 5")
    }
}