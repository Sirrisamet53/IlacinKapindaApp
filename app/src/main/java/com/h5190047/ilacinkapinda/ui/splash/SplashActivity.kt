package com.h5190047.ilacinkapinda.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import com.h5190047.ilacinkapinda.R
import com.h5190047.ilacinkapinda.ui.giris.GirisActivity
import com.h5190047.ilacinkapinda.util.AlertUtil
import com.h5190047.ilacinkapinda.util.Constants
import com.h5190047.ilacinkapinda.util.NetworkUtil
import com.h5190047.ilacinkapinda.util.UYARI_TIPLERI

class SplashActivity : AppCompatActivity() {

    var countDownTimer: CountDownTimer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        timeriBaslat()
    }

    fun timeriBaslat() {
        countDownTimer = object : CountDownTimer(Constants.MILI_SANIYE_SPLSSCRN, Constants.MILI_SANIYE_SPLSSCRN){
            override fun onTick(millisUntilFinished: Long) {

            }

            override fun onFinish() {
                ekraniDegistir()
            }
        }.start()
    }

    fun ekraniDegistir() {

        if (NetworkUtil.internetVarMi(this@SplashActivity)){
            startActivity(Intent(this@SplashActivity, GirisActivity::class.java))
            finish()
        }else{
            AlertUtil.alertGoster(this@SplashActivity,getString(R.string.internet_uyari_baslik),getString(
                R.string.internet_uyari_mesaj
            ),
                UYARI_TIPLERI.INTERNET_UYARI)
        }
    }


}