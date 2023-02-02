package com.muxiyun2.bika.adapter

import com.muxiyun2.bika.R
import com.muxiyun2.bika.base.BaseBindingAdapter
import com.muxiyun2.bika.base.BaseBindingHolder
import com.muxiyun2.bika.bean.KnightBean
import com.muxiyun2.bika.databinding.ItemKnightBinding
import com.muxiyun2.bika.utils.GlideApp
import com.muxiyun2.bika.utils.GlideUrlNewKey

class KnightAdapter : BaseBindingAdapter<KnightBean.Users, ItemKnightBinding>(R.layout.item_knight) {

    override fun bindView(
        holder: BaseBindingHolder<*, *>,
        bean: KnightBean.Users,
        binding: ItemKnightBinding,
        position: Int
    ) {
        //头像
        GlideApp.with(holder.itemView)
            .load(
                if (bean.avatar != null)
                    GlideUrlNewKey(bean.avatar.fileServer, bean.avatar.path)
                else
                    R.drawable.placeholder_avatar_2
            )
            .placeholder(R.drawable.placeholder_avatar_2)
            .into(binding.knightUserImage)
        //头像框
        GlideApp.with(holder.itemView)
            .load(if (bean.character.isNullOrEmpty()) "" else bean.character)
            .into(binding.knightUserCharacter)

        holder.addOnClickListener(R.id.knight_user_image_layout)
    }
}