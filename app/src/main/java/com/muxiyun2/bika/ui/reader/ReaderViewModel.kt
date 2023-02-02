package com.muxiyun2.bika.ui.reader

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.muxiyun2.bika.base.BaseViewModel
import com.muxiyun2.bika.bean.ComicsPictureBean
import com.muxiyun2.bika.db.History
import com.muxiyun2.bika.db.HistoryRepository
import com.muxiyun2.bika.network.RetrofitUtil
import com.muxiyun2.bika.network.base.BaseHeaders
import com.muxiyun2.bika.network.base.BaseObserver
import com.muxiyun2.bika.network.base.BaseResponse

class ReaderViewModel(application: Application) : BaseViewModel(application) {
    var order = 1
    var bookId: String? = null
    var page = 0
    var chapterTotal = 1

    val liveData_picture: MutableLiveData<BaseResponse<ComicsPictureBean>> by lazy {
        MutableLiveData<BaseResponse<ComicsPictureBean>>()
    }

    fun comicsPicture() {
        page++
        RetrofitUtil.service.comicsPictureGet(
            bookId.toString(),
            order.toString(),
            page.toString(),
            BaseHeaders("comics/$bookId/order/$order/pages?page=$page", "GET").getHeaderMapAndToken())
            .doOnSubscribe(this@ReaderViewModel)
            .subscribe(object : BaseObserver<ComicsPictureBean>() {
                override fun onSuccess(baseResponse: BaseResponse<ComicsPictureBean>) {
                    liveData_picture.postValue(baseResponse)
                }

                override fun onCodeError(baseResponse: BaseResponse<ComicsPictureBean>) {
                    page--
                    liveData_picture.postValue(baseResponse)
                }

            })
    }

    private val historyRepository: HistoryRepository = HistoryRepository(application)
    //通过 漫画的id查询
    fun getHistory(): List<History>{
        return historyRepository.getHistory(bookId)
    }

    fun updateHistory(vararg history: History?) {
        historyRepository.updateHistory(*history)
    }

}