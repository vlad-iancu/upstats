package com.example.upstats

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.MutableLiveData
import com.example.upstats.ui.theme.UpStatsTheme
import java.util.Timer
import java.util.TimerTask
import kotlin.concurrent.scheduleAtFixedRate

class MainActivity : ComponentActivity() {

    private val activeTime: MutableState<Int> = mutableStateOf(0)
    private val inactiveTime: MutableState<Int> = mutableStateOf(0)
    private var active: Boolean = false
    //Create a timer that increments the state every second using the timer class and scheduleAtFixedRate method
    private lateinit var timer: TimerTask
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UpStatsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting(activeTime.value.toString(), inactiveTime.value.toString())
                }
            }
        }
        timer = Timer("schedule", true).scheduleAtFixedRate(0, 1000) {
            if (active) activeTime.value += 1
            else inactiveTime.value += 1
        }
    }

    override fun onPause() {
        super.onPause()
        active = false
    }

    override fun onResume() {
        super.onResume()
        active = true
    }

    override fun onDestroy() {
        super.onDestroy()
        timer.cancel()
    }

    override fun onStop() {
        super.onStop()
        active = false
    }


}

@Composable
fun Greeting(activeTime: String, inactiveTime: String, modifier: Modifier = Modifier) {
    Text(
        text = "Active Time is $activeTime - Inactive Time is $inactiveTime",
        modifier = modifier
    )
}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    UpStatsTheme {
//        Greeting("Android")
//    }
//}