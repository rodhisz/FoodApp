package com.rsz.foodapp.helper

import android.widget.TextView
import java.text.DecimalFormat

object Helpers {

    fun TextView.formatPrice(value : String) {
        this.text = getCureencyIDR(java.lang.Double.parseDouble(value))
    }

    fun getCureencyIDR (price : Double) : String {
        val format = DecimalFormat("#,###,###")
        return "Rp. " +format.format(price).replace(",".toRegex(),".")
    }



}