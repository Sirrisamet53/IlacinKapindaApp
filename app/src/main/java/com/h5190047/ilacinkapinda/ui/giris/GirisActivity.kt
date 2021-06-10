package com.h5190047.ilacinkapinda.ui.giris

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.h5190047.ilacinkapinda.ui.kategori.KategoriActivity
import com.h5190047.ilacinkapinda.R
import com.h5190047.ilacinkapinda.data.model.Kullanicilar
import com.h5190047.ilacinkapinda.data.model.KullanicilarResponse
import com.h5190047.ilacinkapinda.databinding.ActivityGirisBinding
import com.h5190047.ilacinkapinda.util.AlertUtil
import com.h5190047.ilacinkapinda.util.UYARI_TIPLERI
import com.h5190047.ilacinkapinda.util.ValidasyonUtil

class GirisActivity : AppCompatActivity() {

    lateinit var binding: ActivityGirisBinding
    var kullaniciViewModel = KullaniciViewModel()
    var kullanicilar: KullanicilarResponse? = null
    var girisYapanKullanici: Kullanicilar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    fun init() {
        binding = ActivityGirisBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewModel()

        binding.apply {
            btnGirisYap.setOnClickListener {
                formuKontrolEt()
            }
        }
    }

    private fun initViewModel() {
        kullaniciViewModel.apply {
            tumKullanicilarLiveData.observe(this@GirisActivity, androidx.lifecycle.Observer {
                Log.e("SAMTO", "observe: " + it.toString())
                kullanicilar = it
            })

            error?.observe(this@GirisActivity, androidx.lifecycle.Observer {
                Log.e("SAMTO", "error:")
            })

            loading?.observe(this@GirisActivity, androidx.lifecycle.Observer {
                Log.e("SAMTO", "loading:")

            })
        }
    }

    fun formuKontrolEt() {
        binding.apply {
            if (ValidasyonUtil.emailGecerliMi(edtEmail.text.toString()) && ValidasyonUtil.alanDoluMu(
                    edtSifre.text.toString()
                )
            ) {
                girisiKontrolEt(edtEmail.text.toString())
            } else
                AlertUtil.alertGoster(
                    this@GirisActivity,
                    getString(R.string.giris_uyari_baslik),
                    getString(R.string.giris_validasyon_uyari_mesaj),
                    UYARI_TIPLERI.GIRIS_UYARI
                )
        }
    }

    private fun girisiKontrolEt(email: String) {

        kullanicilar?.run {
            var filtrelenmisKullanicilar =this.Kullanicilar.filter { it.Email.contains(email) }
            if (!filtrelenmisKullanicilar.isNullOrEmpty()){
                girisYapanKullanici = filtrelenmisKullanicilar.first()
            } else
                girisYapanKullanici = null
        }

        binding.apply {
            if (girisYapanKullanici != null && edtSifre.text.toString() == girisYapanKullanici!!.Sifre) {
                startActivity(Intent(this@GirisActivity, KategoriActivity::class.java))
                finish()
            } else
                AlertUtil.alertGoster(
                    this@GirisActivity,
                    getString(R.string.giris_uyari_baslik),
                    getString(R.string.giris_sifre_uyari_mesaj),
                    UYARI_TIPLERI.GIRIS_UYARI
                )
        }
    }
}