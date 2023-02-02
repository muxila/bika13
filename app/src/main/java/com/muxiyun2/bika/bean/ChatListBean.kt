package com.muxiyun2.bika.bean

data class ChatListBean(
    val chatList: List<Chat>
){
    data class Chat(
        val avatar: String,
        val description: String,
        val title: String,
        val url: String
    )
}

