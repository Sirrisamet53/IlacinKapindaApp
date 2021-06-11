package com.h5190047.ilacinkapinda.data.model
//Kategori verilerinin tutlduğu json'ı modelleyen classtır
class KategorilerUrunlerResponse : ArrayList<KategorilerUrunlerResponseItem>()

data class KategorilerUrunlerResponseItem(
    val Products: List<Product>,
    val categoryName: String,
    val imageURL: String
)

data class Product(
    val Aciklama: String,
    val boyut: String,
    val categoryName: String,
    val fiyat: String,
    val marka: String,
    val mensei: String,
    val urunResim: String
)