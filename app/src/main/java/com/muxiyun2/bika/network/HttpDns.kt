package com.muxiyun2.bika.network

import com.muxiyun2.bika.MyApp
import com.muxiyun2.bika.utils.SPUtil
import okhttp3.Dns
import java.net.InetAddress

class HttpDns:Dns {
    private val DNS_SYSTEM=Dns.SYSTEM

    override fun lookup(hostname: String): MutableList<InetAddress> {

        if (hostname != "68.183.234.72" ){
            //dns host 不是这个ip时用其他ip //这个sp影响加载速度 以后再说 //直接默认走第一个ip不改了
            val host=SPUtil.get(MyApp.contextBase,"addresses1",hostname) as String //获得分流对应的hostname

            return DNS_SYSTEM.lookup(host)
        }
        return DNS_SYSTEM.lookup(hostname)
    }
}