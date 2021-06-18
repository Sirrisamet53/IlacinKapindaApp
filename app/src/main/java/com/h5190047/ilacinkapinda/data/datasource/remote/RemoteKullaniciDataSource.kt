package com.h5190047.ilacinkapinda.data.datasource.remote

import com.h5190047.ilacinkapinda.data.datasource.KategoriService
import com.h5190047.ilacinkapinda.data.datasource.KullaniciDataSource
import com.h5190047.ilacinkapinda.data.model.KullanicilarResponse
import com.h5190047.ilacinkapinda.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
//Uzak sunucudan gelen kullanıcı veri kaynağı sınıfıdır.
class RemoteKullaniciDataSource: KullaniciDataSource {
    //Kullanıcıların uzak sunucudan asekron çekildiği metoddur.Eğer retrofit başarılı bir respons getirirse ise resource başarılı mesajı verilir
    override fun kullanicilariGetir(): Flow<Resource<KullanicilarResponse>> = flow {
        try {
            emit(Resource.Loading())

            val response = KategoriService.build().kullanicilariGetir()

            if (response.isSuccessful) {
                response.body()?.let {
                    emit(Resource.Success(it))
                }
            }

        } catch (ex: Exception) {
            emit(Resource.Error(ex))
            ex.printStackTrace()
        }
    }
}