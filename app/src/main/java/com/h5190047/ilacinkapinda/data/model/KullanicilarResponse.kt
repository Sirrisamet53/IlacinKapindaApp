package com.h5190047.ilacinkapinda.data.model
//Kullanıcıları verilerinin tutlduğu json'ı modelleyen classtır
data class KullanicilarResponse(
    val Kullanicilar: List<Kullanicilar>
)

data class Kullanicilar(
    val AdiSoyadi: String,
    val Email: String,
    val Sifre: String
)