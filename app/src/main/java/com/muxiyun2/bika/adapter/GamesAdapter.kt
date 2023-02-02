package com.muxiyun2.bika.adapter

import com.muxiyun2.bika.R
import com.muxiyun2.bika.base.BaseBindingAdapter
import com.muxiyun2.bika.base.BaseBindingHolder
import com.muxiyun2.bika.bean.GamesBean
import com.muxiyun2.bika.databinding.ItemGamesBinding
import com.muxiyun2.bika.utils.GlideApp
import com.muxiyun2.bika.utils.GlideUrlNewKey

class GamesAdapter :
    BaseBindingAdapter<GamesBean.Games.Docs, ItemGamesBinding>(R.layout.item_games) {

    override fun bindView(
        holder: BaseBindingHolder<*, *>,
        bean: GamesBean.Games.Docs,
        binding: ItemGamesBinding,
        position: Int
    ) {
        GlideApp.with(holder.itemView)
            .load(GlideUrlNewKey(bean.icon.fileServer, bean.icon.path))
            .placeholder(R.drawable.placeholder_avatar_2)
            .into(binding.gamesItemImage)
    }
}