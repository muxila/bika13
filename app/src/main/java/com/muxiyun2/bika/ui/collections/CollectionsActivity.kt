package com.muxiyun2.bika.ui.collections

import android.os.Bundle
import android.view.MenuItem
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.muxiyun2.bika.BR
import com.muxiyun2.bika.R
import com.muxiyun2.bika.adapter.CollectionsAdapter
import com.muxiyun2.bika.base.BaseActivity
import com.muxiyun2.bika.databinding.ActivityCollectionsBinding

/**
 * 推荐
 */

class CollectionsActivity : BaseActivity<ActivityCollectionsBinding, CollectionsViewModel>() {
    private lateinit var adapter: CollectionsAdapter

    override fun initContentView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_collections
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }

    override fun initData() {
        binding.collectionsInclude.toolbar.title = "推荐"
        setSupportActionBar(binding.collectionsInclude.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        adapter = CollectionsAdapter(this)
        binding.collectionsRv.layoutManager = LinearLayoutManager(this)
        binding.collectionsRv.adapter = adapter

        showProgressBar(true, "")
        if (adapter.itemCount < 1) {
            viewModel.getData()
        }

        //网络重试点击事件监听
        binding.collectionsLoadLayout.setOnClickListener {
            showProgressBar(true, "")
            viewModel.getData()

        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun initViewObservable() {
        viewModel.liveData.observe(this) {
            if (it.code == 200) {
                binding.collectionsLoadLayout.visibility = ViewGroup.GONE
                if (adapter.itemCount < 1) {
                    adapter.addData(it.data.collections)
                }
            } else {
                showProgressBar(
                    false,
                    "网络错误，点击重试\ncode=${it.code} error=${it.error} message=${it.message}"
                )
            }
        }
    }

    private fun showProgressBar(show: Boolean, string: String) {
        binding.collectionsLoadProgressBar.visibility =
            if (show) ViewGroup.VISIBLE else ViewGroup.GONE
        binding.collectionsLoadError.visibility = if (show) ViewGroup.GONE else ViewGroup.VISIBLE
        binding.collectionsLoadText.text = string
        binding.collectionsLoadLayout.isEnabled = !show
    }
}