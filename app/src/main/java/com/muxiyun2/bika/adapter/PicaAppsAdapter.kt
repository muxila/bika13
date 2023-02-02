package com.muxiyun2.bika.adapter

import com.muxiyun2.bika.R
import com.muxiyun2.bika.base.BaseBindingAdapter
import com.muxiyun2.bika.base.BaseBindingHolder
import com.muxiyun2.bika.bean.PicaAppsBean
import com.muxiyun2.bika.databinding.ItemPicaappsBinding
import com.muxiyun2.bika.utils.GlideApp

class PicaAppsAdapter :
    BaseBindingAdapter<PicaAppsBean.App, ItemPicaappsBinding>(R.layout.item_picaapps) {

    override fun bindView(
        holder: BaseBindingHolder<*, *>,
        bean: PicaAppsBean.App,
        binding: ItemPicaappsBinding,
        position: Int
    ) {

        GlideApp.with(holder.itemView)
            .load(bean.icon)
            .placeholder(R.drawable.placeholder_avatar_2)
            .into(binding.picaAppsImage)

    }
}