package qzl.com.qgmusickotlin.ui.activity

import android.preference.PreferenceManager
import android.support.v7.widget.Toolbar
import org.jetbrains.anko.find
import qzl.com.qgmusickotlin.R
import qzl.com.qgmusickotlin.base.BaseActivity
import qzl.com.qgmusickotlin.util.ToolBarManage

/**
 * @desc 设置activity
 * @author Qzl
 * @email 2538096489@qq.com
 * @time 2019-02-01 12:52
 * @class QGMusicKotlin
 * @package qzl.com.qgmusickotlin.ui.activity
 */
class SettingActivity: BaseActivity(),ToolBarManage {
    override val toolbar by lazy { find<Toolbar>(R.id.toolbar) }

    override fun getLayoutId(): Int {
        return R.layout.activity_setting
    }

    override fun initData() {
        initSettingToolBar()
        //获取推送通知有没有选中
        val sp = PreferenceManager.getDefaultSharedPreferences(this)
        val push = sp.getBoolean("push", false)
        println("push = $push")
    }
}