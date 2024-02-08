package com.example.cityguide

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import com.example.cityguide.ui.theme.CityGuideTheme
import com.example.cityguide.ui.CityApp

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            CityGuideTheme {
                val layoutDirection = LocalLayoutDirection.current
                Surface( modifier = Modifier
                    .padding(
                        start = WindowInsets.safeDrawing.asPaddingValues()
                            .calculateStartPadding(layoutDirection),
                        end = WindowInsets.safeDrawing.asPaddingValues()
                            .calculateEndPadding(layoutDirection)
                    )
                ) {
                    val windowSize = calculateWindowSizeClass(this)
                    CityApp(
                        windowSize = windowSize.widthSizeClass,
                        onBackPressed = {finish()}
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 1000)
@Composable
fun CityAppExpandedPreview() {
    CityGuideTheme {
        Surface {
            CityApp(windowSize = WindowWidthSizeClass.Expanded) {
                
            }
        }
    }
}