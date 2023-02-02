package com.muxiyun2.bika.ui.collections

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.muxiyun2.bika.base.BaseViewModel
import com.muxiyun2.bika.bean.CollectionsBean
import com.muxiyun2.bika.network.RetrofitUtil
import com.muxiyun2.bika.network.base.BaseHeaders
import com.muxiyun2.bika.network.base.BaseObserver
import com.muxiyun2.bika.network.base.BaseResponse

class CollectionsViewModel(application: Application) : BaseViewModel(application) {
    val liveData: MutableLiveData<BaseResponse<CollectionsBean>> by lazy {
        MutableLiveData<BaseResponse<CollectionsBean>>()
    }

    fun getData() {
        val headers= BaseHeaders("collections","GET").getHeaderMapAndToken()
        RetrofitUtil.service.collectionsGet(headers)
            .doOnSubscribe(this@CollectionsViewModel)
            .subscribe(object : BaseObserver<CollectionsBean>() {

                override fun onComplete() {}
                override fun onSuccess(baseResponse: BaseResponse<CollectionsBean>) {
                    liveData.postValue(baseResponse)
                }

                override fun onCodeError(baseResponse: BaseResponse<CollectionsBean>) {
                    liveData.postValue(baseResponse)
                }
            })
    }

}