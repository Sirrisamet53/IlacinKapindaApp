package com.h5190047.ilacinkapinda.util

import android.util.Patterns

//kullanıcının uygulamaya giriş yaparken bilgilerinin doğruluğunu kontrol eden nesne sınıfıdır
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