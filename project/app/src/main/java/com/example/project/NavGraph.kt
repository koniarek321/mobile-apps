package com.example.project
import GameScreen
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.project.ui.theme.SecondActivity

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SetupNavGraph(
    navController: NavHostController
) {
    NavHost(navController = navController,
        startDestination = Screen.Home.route){
        composable(
            route = Screen.Home.route
        ){
            HomeScreen(navController = navController)
        }

        composable(
            route = Screen.GameDesign.route
        ){
            GameScreen(navController = navController)
        }





//       composable(w
//           route = Screen.History.route
//        ) {
//            displayResults(navController = navController)
//        }

        composable(
            route = "History_Screen/{$CALC_ARGUMENT_KEY_LIST}",
            arguments = listOf(
                navArgument(CALC_ARGUMENT_KEY_LIST) {
                    type = NavType.StringType
                }
            )
        ) { navBackStackEntry ->
            val argList = navBackStackEntry.arguments?.getString(CALC_ARGUMENT_KEY_LIST)?.split(",") ?: emptyList()
            displayResults(navController = navController, argList)
        }



}
}