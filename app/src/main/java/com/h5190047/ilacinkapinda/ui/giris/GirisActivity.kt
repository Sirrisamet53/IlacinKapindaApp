package com.h5190047.ilacinkapinda.ui.giris

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.h5190047.ilacinkapinda.ui.kategori.KategoriActivity
import com.h5190047.ilacinkapinda.R
import com.h5190047.ilacinkapinda.databinding.ActivityGirisBinding
import com.h5190047.ilacinkapinda.util.AlertUtil
import com.h5190047.ilacinkapinda.util.UYARI_TIPLERI
import com.h5190047.ilacinkapinda.util.ValidasyonUtil

class GirisActivity : AppCompatActivity() {

    lateinit var binding: ActivityGirisBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    fun init() {
        binding = ActivityGirisBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnGirisYap.setOnClickListener {
                formuKontrolEt()
            }
        }
    }

    fun formuKontrolEt() {
        binding.apply {
            if (ValidasyonUtil.emailGecerliMi(edtEmail.text.toString()) && ValidasyonUtil.alanDoluMu(edtSifre.text.toString())) {
                if (edtSifre.text.toString() == "4321"){
                    startActivity(Intent(this@GirisActivity, KategoriActivity::class.java))
                    finish()
                }
                else
                    AlertUtil.alertGoster(
                        this@GirisActivity,
                        getString(R.string.giris_uyari_baslik),
                        getString(R.string.giris_sifre_uyari_mesaj),
                        UYARI_TIPLERI.GIRIS_UYARI
                    )
            } else
                AlertUtil.alertGoster(
                    this@GirisActivity,
                    getString(R.string.giris_uyari_baslik),
                    getString(R.string.giris_validasyon_uyari_mesaj),
                    UYARI_TIPLERI.GIRIS_UYARI
                )
        }
    }
}