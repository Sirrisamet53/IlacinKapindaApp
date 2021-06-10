package com.h5190047.ilacinkapinda.util

object Constants {
    const val MILI_SANIYE_SPLSSCRN: Long = 3000
    const val INTERVAL_SPLSSCRN: Long = 1000
    const val BASE_URL = "https://raw.githubusercontent.com/Sirrisamet53/IlacinKapindaWebService/main/"
    const val GRID_KOLON_SAYISI = 2
    const val TASINANIN_BASLIGI = "TASINAN_URUN_BASLIGI"
}

enum class UYARI_TIPLERI{
    INTERNET_UYARI,
    CIKIS_UYARI,
    GIRIS_UYARI
}

enum class RCV_LAYOUT_TIPLERI{
    LISTE_LAYOUT,
    GRID_LAYOUT
}