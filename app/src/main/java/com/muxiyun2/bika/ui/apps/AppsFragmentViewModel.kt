package com.muxiyun2.bika.ui.apps

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.muxiyun2.bika.base.BaseViewModel
import com.muxiyun2.bika.bean.ChatListBean
import com.muxiyun2.bika.bean.PicaAppsBean
import com.muxiyun2.bika.network.RetrofitUtil
import com.muxiyun2.bika.network.base.BaseHeaders
import com.muxiyun2.bika.network.base.BaseObserver
import com.muxiyun2.bika.network.base.BaseResponse

class AppsFragmentViewModel(application: Application) : BaseViewModel(application) {
    val liveData_chat: MutableLiveData<BaseResponse<ChatListBean>> by lazy {
        MutableLiveData<BaseResponse<ChatListBean>>()
    }

    val liveData_apps: MutableLiveData<BaseResponse<PicaAppsBean>> by lazy {
        MutableLiveData<BaseResponse<PicaAppsBean>>()
    }

    fun getChatList() {
        RetrofitUtil.service.chatListGet(
            BaseHeaders("chat", "GET").getHeaderMapAndToken()
        )
            .doOnSubscribe(this)
            .subscribe(object : BaseObserver<ChatListBean>() {
                override fun onSuccess(baseResponse: BaseResponse<ChatListBean>) {
                    liveData_chat.postValue(baseResponse)
                }

                override fun onCodeError(baseResponse: BaseResponse<ChatListBean>) {
                    liveData_chat.postValue(baseResponse)
                }

            })
    }

    fun getPicaApps() {
        RetrofitUtil.service.picaAppsGet(
            BaseHeaders("pica-apps", "GET").getHeaderMapAndToken()
        )
            .doOnSubscribe(this)
            .subscribe(object : BaseObserver<PicaAppsBean>() {
                override fun onSuccess(baseResponse: BaseResponse<PicaAppsBean>) {
                    liveData_apps.postValue(baseResponse)
                }

                override fun onCodeError(baseResponse: BaseResponse<PicaAppsBean>) {
                    liveData_apps.postValue(baseResponse)
                }

            })
    }
}