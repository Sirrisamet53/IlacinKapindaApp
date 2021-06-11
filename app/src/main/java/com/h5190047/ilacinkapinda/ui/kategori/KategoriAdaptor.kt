package com.h5190047.ilacinkapinda.ui.kategori

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.h5190047.ilacinkapinda.data.model.KategorilerUrunlerResponseItem
import com.h5190047.ilacinkapinda.databinding.CardViewKategorilerBinding
import com.h5190047.ilacinkapinda.util.GlideUtil
import com.h5190047.ilacinkapinda.util.OnItemClickListener
//Kategori sayfası içerisinde bulunan verilerin activity ile birleştirildiği kotlin sınıfıdır.
class KategoriAdaptor(
    var kategoriList: List<KategorilerUrunlerResponseItem>,
    var onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<KategoriAdaptor.CategoryViewHolder>() {

    inner class CategoryViewHolder(val binding: CardViewKategorilerBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding =
            CardViewKategorilerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        with(holder) {
            binding.apply {
                txtKategoriAdi.text = kategoriList[position].categoryName
                var imageUrl = kategoriList[position].imageURL as String
                GlideUtil.resmiIndiripGoster(imgKategori.context, imageUrl, imgKategori)
                crdKategori.setOnClickListener {
                    onItemClickListener.onItemClick(position)
                }
            }
        }
    }
//Liste içerisinde bulunan verilerin dizi boyutu kadar döndürüldüğü metoddur
    override fun getItemCount(): Int {
        return kategoriList.size
    }

}