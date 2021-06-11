package com.h5190047.ilacinkapinda.data.datasource

import com.h5190047.ilacinkapinda.data.model.KategorilerUrunlerResponseItem
import com.h5190047.ilacinkapinda.util.Resource
import kotlinx.coroutines.flow.Flow

//kategori ve ürünlerin veri kaynağının çekildiği kotlin interface sınıfıdır.

interface KategoriDataSource {
    //Kategorilerin ve ürünlerin getirileceği metoddur. Flow coroutines kullanılarak resource kaynağından kategori ve ürünler responsuna erişiliyor.

    fun kategorileriGetir(): Flow<Resource<List<KategorilerUrunlerResponseItem>>>
}