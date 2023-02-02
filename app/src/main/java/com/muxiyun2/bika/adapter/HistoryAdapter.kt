package com.muxiyun2.bika.adapter

import com.muxiyun2.bika.R
import com.muxiyun2.bika.base.BaseBindingAdapter
import com.muxiyun2.bika.base.BaseBindingHolder
import com.muxiyun2.bika.databinding.ItemHistroyBinding
import com.muxiyun2.bika.db.History
import com.muxiyun2.bika.utils.GlideApp
import com.muxiyun2.bika.utils.GlideUrlNewKey
import com.muxiyun2.bika.utils.TimestampFormat

class HistoryAdapter :
    BaseBindingAdapter<History, ItemHistroyBinding>(R.layout.item_histroy) {

    override fun bindView(
        holder: BaseBindingHolder<*, *>,
        bean: History,
        binding: ItemHistroyBinding,
        position: Int
    ) {
        GlideApp.with(holder.itemView)
            .load(GlideUrlNewKey(bean.fileServer, bean.path))
            .placeholder(R.drawable.placeholder_avatar_2)
            .into(binding.historyItemImage)
        binding.historyItemTime.text = TimestampFormat().getDateToString(bean.time)
    }
}