package com.h5190047.ilacinkapinda.util

import android.app.ProgressDialog
import android.content.Context
import com.h5190047.ilacinkapinda.R


//kullanıcının uygulama içerisinde hangi aşamada olduğunu belirleyen kotlin nesne sınıfıdır
object ProgressUtil {
    var progressDialog: ProgressDialog?=null

    fun dialogGoster(context: Context, loadingTitle: String){
        progressDialog = ProgressDialog(context)

        progressDialog!!.apply {
            setTitle(loadingTitle)
            setMessage(context.getString(R.string.progres_dialog_yazi))
            setCancelable(false)
            show()
        }
    }
    fun dialogGizle(){
        progressDialog!!.let {
            it.dismiss()
        }
    }
}