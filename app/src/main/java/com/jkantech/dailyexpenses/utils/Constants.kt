package com.jkantech.dailyexpenses.utils

class Constants {
    companion object{
        var isOnline=false
        private fun baseUrl():String{
            return if (isOnline){
                "https://valeryprojects.jkantech.com/"
            }else{
                "http://192.168.0.100/"

            }
        }
        val baseURL=baseUrl()
        val baseURLAPI= baseURL+"api/"
    }
}