package com.muxiyun2.bika.adapter

import com.muxiyun2.bika.R
import com.muxiyun2.bika.base.BaseBindingAdapter
import com.muxiyun2.bika.base.BaseBindingHolder
import com.muxiyun2.bika.bean.ChapterBean
import com.muxiyun2.bika.databinding.ItemChapterBinding
import com.muxiyun2.bika.utils.TimeUtil

class ChapterAdapter : BaseBindingAdapter<ChapterBean.Eps.Doc, ItemChapterBinding>(R.layout.item_chapter) {

    override fun bindView(
        holder: BaseBindingHolder<*, *>,
        bean: ChapterBean.Eps.Doc,
        binding: ItemChapterBinding,
        position: Int
    ) {
        binding.chapterTitle.text = bean.title
        binding.chapterTime.text = TimeUtil.B(bean.updated_at)
    }
}