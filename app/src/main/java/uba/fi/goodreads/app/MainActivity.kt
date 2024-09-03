package uba.fi.goodreads.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import uba.fi.goodreads.core.ui.GoodReadsApp
import uba.fi.goodreads.core.ui.rememberGoodReadsAppState
import uba.fi.goodreads.core.design_system.theme.GoodReadsTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val appState = rememberGoodReadsAppState()
            GoodReadsTheme {
                GoodReadsApp(appState)
            }
        }
    }
}
