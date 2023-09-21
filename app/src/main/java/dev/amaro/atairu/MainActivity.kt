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
import dev.amaro.atairu.core.models.Screen
import dev.amaro.atairu.core.service.AtairuServerFeedService
import dev.amaro.atairu.core.service.FeedService
import dev.amaro.atairu.ui.compose.elements.AtairuScreen
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {

    private lateinit var service: FeedService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        service = AtairuServerFeedService()
        setContent {
            val feedContent = remember { mutableStateOf(null as Screen?) }
            Column {
                Button(onClick = { refresh(feedContent) }) {
                    Text("Refresh")
                }
                LaunchedEffect("Loading") {
                    refresh(feedContent)
                }
                feedContent.value?.apply { AtairuScreen(this) }
            }
        }
    }

    private fun refresh(state: MutableState<Screen?>) {
        GlobalScope.launch {
            state.value = service.getScreen()
        }
    }
}
