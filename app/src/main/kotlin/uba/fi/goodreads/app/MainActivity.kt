package uba.fi.goodreads.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uba.fi.goodreads.core.ui.GoodReadsApp
import uba.fi.goodreads.core.ui.rememberGoodReadsAppState
import uba.fi.goodreads.core.design_system.theme.GoodReadsTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainActivityViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var isSignedIn: Boolean by mutableStateOf(false)

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.isSignedIn
                    .onEach { isSignedIn = it }
                    .collect()
            }
        }

        enableEdgeToEdge()
        setContent {
            val appState = rememberGoodReadsAppState(
                isSignedIn = isSignedIn
            )
            GoodReadsTheme {
                GoodReadsApp(appState)
            }
        }
    }
}
