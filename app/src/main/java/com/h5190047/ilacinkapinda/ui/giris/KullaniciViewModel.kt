package com.h5190047.ilacinkapinda.ui.giris

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.h5190047.ilacinkapinda.data.model.KategorilerUrunlerResponseItem
import com.h5190047.ilacinkapinda.data.model.KullanicilarResponse
import com.h5190047.ilacinkapinda.data.repository.KategoriRepository
import com.h5190047.ilacinkapinda.data.repository.KullaniciRepository
import com.h5190047.ilacinkapinda.util.ResourceStatus
import kotlinx.coroutines.launch

class KullaniciViewModel: ViewModel() {
    private val kullaniciRepository: KullaniciRepository = KullaniciRepository()

    init {
        kullanicilariGetir()
    }

    var loading: MutableLiveData<Boolean>? = MutableLiveData()
    var tumKullanicilarLiveData = MutableLiveData<KullanicilarResponse>()
    var error = MutableLiveData<Throwable>()


    fun kullanicilariGetir() = viewModelScope.launch {

        kullaniciRepository.kullanicilariGetir()

            .asLiveData(viewModelScope.coroutineContext).observeForever {

                when (it.status) {
                    ResourceStatus.LOADING -> {
                        loading?.postValue(true)
                    }

                    ResourceStatus.SUCCESS -> {
                        Log.e("SAMTO", it.data.toString())
                        tumKullanicilarLiveData.postValue(it.data!!)
                        loading?.postValue(false)
                    }

                    ResourceStatus.ERROR -> {
                        error.postValue(it.throwable!!)
                        loading?.postValue(false)
                    }
                }
            }
    }
}