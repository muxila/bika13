package com.muxiyun2.bika.bean

data class PunchInBean(
        val res: Res
    ) {

        data class Res(
            val punchInLastDay: String,
            val status: String
        )
    }