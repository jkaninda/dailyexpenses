package com.jkantech.dailyexpenses.utils

import android.content.Context
import android.widget.Toast

object Utils {
    fun showToast(context: Context,message:String?) {
        return Toast.makeText(context, message.orEmpty(), Toast.LENGTH_LONG).show()
    }
}