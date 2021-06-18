package com.h5190047.ilacinkapinda.data.datasource.remote

import com.h5190047.ilacinkapinda.data.datasource.KategoriDataSource
import com.h5190047.ilacinkapinda.data.datasource.KategoriService
import com.h5190047.ilacinkapinda.data.model.KategorilerUrunlerResponseItem
import com.h5190047.ilacinkapinda.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

//Uzak sunucudan gelen kategori veri kaynağı sınıfıdır.
class RemoteKategoriDataSource: KategoriDataSource {


    //Kategorilerin uzak sunucudan asekron çekildiği metoddur.Eğer retrofit başarılı bir respons getirirse ise resource başarılı mesajı verilir
    override fun kategorileriGetir(): Flow<Resource<List<KategorilerUrunlerResponseItem>>> = flow {
        try {
            emit(Resource.Loading())

            val response = KategoriService.build().kategorileriGetir()

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