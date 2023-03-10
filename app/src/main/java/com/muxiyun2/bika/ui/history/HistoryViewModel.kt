package com.muxiyun2.bika.ui.history

import android.app.Application
import androidx.lifecycle.LiveData
import com.muxiyun2.bika.base.BaseViewModel
import com.muxiyun2.bika.db.History
import com.muxiyun2.bika.db.HistoryRepository

class HistoryViewModel (application: Application) : BaseViewModel(application) {
    var page=0

    private val historyRepository: HistoryRepository = HistoryRepository(application)

    //查询第一页
    val firstPageHistoryLive: LiveData<List<History>>
        get() = historyRepository.firstPageLiveData

    //分页查询 （未验证能不能用）
    fun getAllHistory(): List<History>{
        page += 20
        return historyRepository.getAllHistory(page)
    }

    fun deleteHistory(vararg history: History?) {
        historyRepository.deleteHistory(*history)
    }

    fun deleteAllHistory() {
        historyRepository.deleteAllHistory()
    }
}