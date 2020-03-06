package com.ci.myassignment.utils

import android.content.Context
import android.net.ConnectivityManager
import com.ci.myassignment.MyApplication

object Network {
     fun isNetworkConnected(): Boolean {
        val cm = MyApplication.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo != null
    }
}