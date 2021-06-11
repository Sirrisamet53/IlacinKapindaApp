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


//kullanıcının uygulamaya giriş yapacağı kotlin sınıfıdır.
class GirisActivity : AppCompatActivity() {

    //değişkenlerin tanımlandığı kısımdır.

    lateinit var binding: ActivityGirisBinding
    var kullaniciViewModel = KullaniciViewModel()
    var kullanicilar: KullanicilarResponse? = null
    var girisYapanKullanici: Kullanicilar? = null

    //giriş sayfasında ilk çalışacak olan metoddur. içerisinde init metodu çağırılmıştır.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    fun init() {

        binding = ActivityGirisBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewModel()
        //binding ile uygulanacak olan formu kontrol et metodu çağırılmıştır.
        binding.apply {
            btnGirisYap.setOnClickListener {
                formuKontrolEt()
            }
        }
    }
    //Kullanıcı modelden çekilen verilerin görüntülendiği fonksiyondur
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

    //formun kontrol etildiği fonksiyondur
    fun formuKontrolEt() {
        //kullanıcının girmiş olduğu mail adresinin doğru veya boş olup olmadığını kontrol eder
        binding.apply {
            if (ValidasyonUtil.emailGecerliMi(edtEmail.text.toString()) && ValidasyonUtil.alanDoluMu(
                    edtSifre.text.toString()
                )
            ) {
                //eğer doğru ise girisiKontrolEt fonksiyonu çağırılır
                girisiKontrolEt(edtEmail.text.toString())
            } else
                //eğer değil ise kullanıcıya uyarı verilir
                AlertUtil.alertGoster(
                    this@GirisActivity,
                    getString(R.string.giris_uyari_baslik),
                    getString(R.string.giris_validasyon_uyari_mesaj),
                    UYARI_TIPLERI.GIRIS_UYARI
                )
        }
    }

    private fun girisiKontrolEt(email: String) {
        //anlık giren kullanıcıyı kontrol eden fonksiyondur
        kullanicilar?.run {
            var filtrelenmisKullanicilar =this.Kullanicilar.filter { it.Email.contains(email) }
            if (!filtrelenmisKullanicilar.isNullOrEmpty()){
                girisYapanKullanici = filtrelenmisKullanicilar.first()
            } else
                girisYapanKullanici = null
        }
        //giriş yapan kullanıcının şifresini kontrol eder ve eğer doğru ise uygulamaya girişini sağlar
        binding.apply {
            if (girisYapanKullanici != null && edtSifre.text.toString() == girisYapanKullanici!!.Sifre) {
                startActivity(Intent(this@GirisActivity, KategoriActivity::class.java))
                finish()
            } else
                //şifresi doğru değil ise uyarı verir
                AlertUtil.alertGoster(
                    this@GirisActivity,
                    getString(R.string.giris_uyari_baslik),
                    getString(R.string.giris_sifre_uyari_mesaj),
                    UYARI_TIPLERI.GIRIS_UYARI
                )
        }
    }
}