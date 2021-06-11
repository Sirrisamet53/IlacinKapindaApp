package com.h5190047.ilacinkapinda.data.datasource

import com.h5190047.ilacinkapinda.data.model.Kullanicilar
import com.h5190047.ilacinkapinda.data.model.KullanicilarResponse
import com.h5190047.ilacinkapinda.util.Resource
import kotlinx.coroutines.flow.Flow
//Kullanıcının veri kaynağının çekildiği kotlin interface sınıfıdır.
interface KullaniciDataSource {
    //kullanıcıların getirileceği metoddur. Flow coroutines kullanılarak resource kaynağından kullanıcı responsuna erişiliyor.
    fun kullanicilariGetir(): Flow<Resource<KullanicilarResponse>>
}