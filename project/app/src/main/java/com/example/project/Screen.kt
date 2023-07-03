package com.example.project


const val CALC_ARGUMENT_KEY_LIST = "calc_argument_key_list"

sealed class Screen(val route: String){
    object Home: Screen(route = "home_screen")

    object GameDesign: Screen(route = "Game_Design")
    object History: Screen(route = "History_Screen/$CALC_ARGUMENT_KEY_LIST")


}
