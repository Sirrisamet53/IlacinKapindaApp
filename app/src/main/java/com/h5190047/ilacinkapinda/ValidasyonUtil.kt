package com.h5190047.ilacinkapinda

import android.util.Patterns

object ValidasyonUtil {
    fun emailGecerliMi(emailMetin: String): Boolean {
        if (Patterns.EMAIL_ADDRESS.matcher(emailMetin).matches() && !emailMetin.isNullOrBlank()){
                return true
        }
        return false
    }

    fun alanDoluMu(metin: String): Boolean {
        if (!metin.isNullOrBlank()){
            return true
        }
            return false
    }
}