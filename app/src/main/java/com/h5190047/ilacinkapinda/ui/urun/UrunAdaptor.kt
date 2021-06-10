package com.h5190047.ilacinkapinda.ui.urun

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.h5190047.ilacinkapinda.data.model.KategorilerUrunlerResponseItem
import com.h5190047.ilacinkapinda.data.model.Product
import com.h5190047.ilacinkapinda.databinding.CardViewKategorilerBinding
import com.h5190047.ilacinkapinda.databinding.CardViewUrunlerBinding
import com.h5190047.ilacinkapinda.util.GlideUtil
import com.h5190047.ilacinkapinda.util.OnItemClickListener

class UrunAdaptor(
    var urunList: List<Product>,
    var onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<UrunAdaptor.UrunlerViewHolder>() {

    inner class UrunlerViewHolder(val binding: CardViewUrunlerBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UrunlerViewHolder {
        val binding =
            CardViewUrunlerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return UrunlerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UrunlerViewHolder, position: Int) {
        with(holder) {
            binding.apply {
                txtUrunAdi.text = urunList[position].marka
                var urunResimUrl = urunList[position].urunResim
                GlideUtil.resmiIndiripGoster(imgUrun.context, urunResimUrl, imgUrun)
                crdUrunler.setOnClickListener {
                    onItemClickListener.onItemClick(position)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return urunList.size
    }

}