    package com.example.test_1



    import android.os.Bundle
    import androidx.activity.ComponentActivity
    import androidx.activity.compose.setContent
    import androidx.activity.enableEdgeToEdge
    import androidx.compose.foundation.layout.fillMaxSize
    import androidx.compose.foundation.layout.systemBarsPadding
    import androidx.compose.material3.MaterialTheme
    import androidx.compose.material3.Surface
    import androidx.compose.runtime.Composable
    import androidx.compose.ui.Modifier
    import androidx.navigation.compose.NavHost
    import androidx.navigation.compose.composable
    import androidx.navigation.compose.rememberNavController
    import com.example.test_1.ui.theme.Test_1Theme

    class MainActivity : ComponentActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            enableEdgeToEdge()
            setContent {
                Test_1Theme {

                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .systemBarsPadding()
                    ) {
                        App()
                    }
                }

            }
        }
    }

    @Composable
    fun App() {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "firstscreen") {
            composable("firstscreen") {
                FirstScreen {name->
                    navController.navigate("secondscreen/$name")
                }


            }
            composable("secondscreen/{name}") { backstackentry ->
                val n = backstackentry.arguments?.getString("name") ?: ""
                SecondScreen(n) {
                    navController.navigate("thirdscreen")
                }
            }
            composable("thirdscreen"){
                ThirdScreen {
                    navController.popBackStack()
                }
            }
        }
    }