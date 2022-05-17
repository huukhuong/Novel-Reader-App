package com.huukhuongit.novelreader.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper
import java.text.DecimalFormat
import kotlin.math.floor
import kotlin.math.log10
import kotlin.math.pow

class Helpers {

    companion object {

        fun overScroll(recyclerView: RecyclerView, orientation: Int) {
            OverScrollDecoratorHelper.setUpOverScroll(
                recyclerView,
                orientation
            )
        }

        // check internet connected
        fun isOnline(context: Context): Boolean {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val capabilities =
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
                } else {
                    TODO("VERSION.SDK_INT < M")
                }
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                        return true
                    }
                }
            }
            return false
        }

        fun formatNumber(number: Number): String? {
            val suffix = charArrayOf(' ', 'K', 'M', 'B', 'T', 'P', 'E')
            val numValue = number.toLong()
            val value = floor(log10(numValue.toDouble())).toInt()
            val base = value / 3
            return if (value >= 3 && base < suffix.size) {
                DecimalFormat("#0.0").format(
                    numValue / 10.0.pow((base * 3).toDouble())
                ) + suffix[base]
            } else {
                DecimalFormat("#,##0").format(numValue)
            }
        }
    }

}