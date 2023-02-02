package com.muxiyun2.bika.adapter

import com.muxiyun2.bika.R
import com.muxiyun2.bika.base.BaseBindingAdapter
import com.muxiyun2.bika.base.BaseBindingHolder
import com.muxiyun2.bika.bean.ChatListBean
import com.muxiyun2.bika.databinding.ItemChatlistBinding
import com.muxiyun2.bika.utils.GlideApp

class ChatListAdapter :
    BaseBindingAdapter<ChatListBean.Chat, ItemChatlistBinding>(R.layout.item_chatlist) {

    override fun bindView(
        holder: BaseBindingHolder<*, *>,
        bean: ChatListBean.Chat,
        binding: ItemChatlistBinding,
        position: Int
    ) {

        GlideApp.with(holder.itemView)
            .load(bean.avatar)
            .placeholder(R.drawable.placeholder_avatar_2)
            .into(binding.chatListImage)

    }
}