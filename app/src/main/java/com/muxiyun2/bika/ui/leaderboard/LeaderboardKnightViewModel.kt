package com.muxiyun2.bika.ui.leaderboard

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.muxiyun2.bika.base.BaseViewModel
import com.muxiyun2.bika.bean.KnightBean
import com.muxiyun2.bika.network.RetrofitUtil
import com.muxiyun2.bika.network.base.BaseHeaders
import com.muxiyun2.bika.network.base.BaseObserver
import com.muxiyun2.bika.network.base.BaseResponse

class LeaderboardKnightViewModel(application: Application) : BaseViewModel(application) {
    val liveData: MutableLiveData<BaseResponse<KnightBean>> by lazy {
        MutableLiveData<BaseResponse<KnightBean>>()
    }

    fun getKnight() {
        RetrofitUtil.service.knightGet(
            BaseHeaders("comics/knight-leaderboard", "GET").getHeaderMapAndToken()
        )
            .doOnSubscribe(this@LeaderboardKnightViewModel)
            .subscribe(object : BaseObserver<KnightBean>() {
                override fun onSuccess(baseResponse: BaseResponse<KnightBean>) {
                    // 请求成功
                    liveData.postValue(baseResponse)
                }

                override fun onCodeError(baseResponse: BaseResponse<KnightBean>) {
                    liveData.postValue(baseResponse)
                }

            })
    }
}