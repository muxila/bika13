package com.muxiyun2.bika.ui.splash

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.muxiyun2.bika.base.BaseViewModel
import com.muxiyun2.bika.bean.InitBean
import com.muxiyun2.bika.network.RetrofitUtil
import io.reactivex.rxjava3.observers.DefaultObserver

class SplashViewModel(application: Application) : BaseViewModel(application) {
    val liveData_init: MutableLiveData<InitBean> by lazy {
        MutableLiveData<InitBean>()
    }
    val liveData_init_error: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    fun getInit() {
        RetrofitUtil.service_init.initGet()
            .doOnSubscribe(this@SplashViewModel)
            .subscribe(object : DefaultObserver<InitBean>() {

                override fun onNext(bean: InitBean) {
                    // 请求成功
                    liveData_init.postValue(bean)

                }

                override fun onError(throwable: Throwable) {
                    //请求失败
                    liveData_init_error.postValue(throwable.message)
                }

                override fun onComplete() {}

            })
    }
}