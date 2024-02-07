package com.example.cityguide

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.cityguide.ui.theme.CityGuideTheme
import com.example.cityguide.ui.CityApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CityGuideTheme {
                // A surface container using the 'background' color from the theme
                Surface {

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