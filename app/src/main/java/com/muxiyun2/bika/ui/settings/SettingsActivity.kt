package com.muxiyun2.bika.ui.settings

import android.os.Bundle
import android.view.MenuItem
import com.muxiyun2.bika.BR
import com.muxiyun2.bika.R
import com.muxiyun2.bika.base.BaseActivity
import com.muxiyun2.bika.databinding.ActivitySettingsBinding

class SettingsActivity : BaseActivity<ActivitySettingsBinding, SettingsViewModel>() {

    override fun initContentView(savedInstanceState: Bundle?): Int {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.cat_topappbar_preferences_container, SettingsPreferenceFragment())
                .commit()
        }
        return R.layout.activity_settings
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }

    override fun initData() {
        binding.comiclistInclude.toolbar.title = "设置"
        setSupportActionBar(binding.comiclistInclude.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            //Toolbar的事件---返回
            android.R.id.home -> {
                finish()
            }
        }

        return super.onOptionsItemSelected(item)
    }

}