package com.h5190047.ilacinkapinda.util

import android.app.Activity
import android.content.Intent
import android.provider.Settings
import androidx.appcompat.app.AlertDialog
import com.h5190047.ilacinkapinda.R
import com.h5190047.ilacinkapinda.data.model.Product
import com.h5190047.ilacinkapinda.ui.urun.UrunAdaptor

//Kullanıcıya verilecek olan uyarıların gösterileceği kotlin nesne sınıfıdır.
object AlertUtil {

    fun alertGoster(ekran: Activity, baslik: String?, mesaj: String?, uyariTuru: UYARI_TIPLERI) {
        val builder = AlertDialog.Builder(ekran)
        builder.setTitle(baslik)
        builder.setMessage(mesaj)
        builder.setCancelable(false)
        if (uyariTuru == UYARI_TIPLERI.INTERNET_UYARI) {
            builder.setPositiveButton(ekran.getString(R.string.internet_uyari_btn_ayarlar)) { dialog, which ->
                dialog.dismiss()
                val intent = Intent(Settings.ACTION_WIFI_SETTINGS)
                ekran.startActivity(intent)
                ekran.finish()
            }
            builder.setNegativeButton(ekran.getString(R.string.internet_uyari_btn_cikis)) { dialog, which ->
                dialog.dismiss()
                ekran.finish()
            }
        } else if (uyariTuru == UYARI_TIPLERI.CIKIS_UYARI) {
            builder.setPositiveButton(ekran.getString(R.string.cikis_uyari_btn_evet)) { dialog, which ->
                dialog.dismiss()
                ekran.finish()
            }
            builder.setNegativeButton(ekran.getString(R.string.cikis_uyari_btn_hayir)) { dialog, which -> dialog.dismiss() }
        } else if (uyariTuru == UYARI_TIPLERI.GIRIS_UYARI) {
            builder.setPositiveButton(ekran.getString(R.string.giris_uyari_btn_tamam)) { dialog, which ->
                dialog.dismiss()
            }
        }
        builder.show()
    }

    fun siralamaAlertiGoster(ekran: Activity, baslik: String?, liste: ArrayList<Product>, adaptor: UrunAdaptor){
        val builder = AlertDialog.Builder(ekran)
        builder.setTitle(baslik)
        builder.setCancelable(false)
        val siralamaSecenekleri = arrayOf("İsme göre artan sıralama", "İsme göre azalan sıralama")
        builder.setItems(siralamaSecenekleri){ diyalog, pozisyon ->
            when (pozisyon) {
                0 -> {
                    liste.sortBy { it.marka }
                }
                1 -> {
                    liste.sortByDescending { it.marka }
                }
            }
            adaptor.notifyDataSetChanged()
        }
        builder.show()
    }
}