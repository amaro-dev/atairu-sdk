package dev.amaro.atairu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import dev.amaro.atairu.service.AtairuFeedService
import dev.amaro.atairu.service.Screen
import dev.amaro.atairu.translators.AtairuScreen
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {

    private lateinit var service: AtairuFeedService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        service = AtairuFeedService(assets)
        setContent {
            val feedContent = remember { mutableStateOf(null as Screen?) }
            Column {
                Button(onClick = { refresh(feedContent) }) {
                    Text("Refresh")
                }
                LaunchedEffect("Loading") {
                    refresh(feedContent)
                }
                feedContent.value?.apply { AtairuScreen(this).Render() }
            }
        }
    }

    private fun refresh(state: MutableState<Screen?>) {
        GlobalScope.launch {
            state.value = service.getFeed()
        }
    }
}
