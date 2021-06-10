package com.h5190047.ilacinkapinda.ui.kategori

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.h5190047.ilacinkapinda.data.model.KategorilerUrunlerResponseItem
import com.h5190047.ilacinkapinda.data.repository.KategoriRepository
import com.h5190047.ilacinkapinda.util.ResourceStatus
import kotlinx.coroutines.launch

class KategoriViewModel : ViewModel() {

    private val kategoriRepository: KategoriRepository = KategoriRepository()

    init {
        kategorileriGetir()
    }

    var loading: MutableLiveData<Boolean>? = MutableLiveData()
    var tumKategorilerLiveData = MutableLiveData<List<KategorilerUrunlerResponseItem>>()
    var error = MutableLiveData<Throwable>()


    fun kategorileriGetir() = viewModelScope.launch {

        kategoriRepository.kategorileriGetir()

            .asLiveData(viewModelScope.coroutineContext).observeForever {

                when (it.status) {
                    ResourceStatus.LOADING -> {
                        loading?.postValue(true)
                    }

                    ResourceStatus.SUCCESS -> {
                        Log.e("SAMTO", it.data.toString())
                        tumKategorilerLiveData.postValue(it.data!!)
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
