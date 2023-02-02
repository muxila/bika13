package com.muxiyun2.bika.ui.games

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.muxiyun2.bika.base.BaseViewModel
import com.muxiyun2.bika.bean.GamesBean
import com.muxiyun2.bika.network.RetrofitUtil
import com.muxiyun2.bika.network.base.BaseHeaders
import com.muxiyun2.bika.network.base.BaseObserver
import com.muxiyun2.bika.network.base.BaseResponse

class GamesViewModel(application: Application) : BaseViewModel(application) {
    var page = 0//当前页数

    val liveData: MutableLiveData<BaseResponse<GamesBean>> by lazy {
        MutableLiveData<BaseResponse<GamesBean>>()
    }

    fun getGames() {
        page++//每次请求加1
        RetrofitUtil.service.gamesGet(
            page.toString(),
            BaseHeaders("games?page=$page", "GET").getHeaderMapAndToken()
        )
            .doOnSubscribe(this)
            .subscribe(object : BaseObserver<GamesBean>(){
                override fun onSuccess(baseResponse: BaseResponse<GamesBean>) {
                    liveData.postValue(baseResponse)
                }

                override fun onCodeError(baseResponse: BaseResponse<GamesBean>) {
                    page--
                    liveData.postValue(baseResponse )
                }
            })
    }

}

