package com.h5190047.ilacinkapinda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class KategoriActivity : AppCompatActivity() {

    override fun onBackPressed() {
        AlertUtil.alertGoster(
            this,
            getString(R.string.cikis_uyari_baslik),
            getString(R.string.cikis_uyari_mesaj),
            UYARI_TIPLERI.CIKIS_UYARI
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kategori)
    }
}