package com.jkantech.dailyexpenses.data

    data class ResponseData<T> (
        val code:Int,
        val message:String,
        val data:MutableList<T> ,
    )

