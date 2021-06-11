package com.h5190047.ilacinkapinda.data.datasource

import com.h5190047.ilacinkapinda.data.model.KategorilerUrunlerResponseItem
import com.h5190047.ilacinkapinda.data.service.KategoriService
import com.h5190047.ilacinkapinda.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
//Modellenen kategorilerin kaynak dosyasından çekilen bilgiler ile birleştirildiği classtır

class RemoteKategoriDataSource: KategoriDataSource {
    //kategorilerin responstan çekildiği metoddur.

    override fun kategorileriGetir(): Flow<Resource<List<KategorilerUrunlerResponseItem>>> = flow {
        try {
            emit(Resource.Loading())
            //eğer response başarılı gelir ise resource başarılı mesajı verilir

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