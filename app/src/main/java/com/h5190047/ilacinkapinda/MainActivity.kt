package com.h5190047.ilacinkapinda

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    var countDownTimer: CountDownTimer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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

        if (NetworkUtil.internetVarMi(this@MainActivity)){
            startActivity(Intent(this@MainActivity, GirisActivity::class.java))
            finish()
        }else{
            AlertUtil.alertGoster(this@MainActivity,getString(R.string.internet_uyari_baslik),getString(R.string.internet_uyari_mesaj),UYARI_TIPLERI.INTERNET_UYARI)
        }
    }


}