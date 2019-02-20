package qzl.com.qgmusickotlin.util

import android.support.v7.widget.Toolbar
import org.jetbrains.anko.startActivity
import qzl.com.qgmusickotlin.R
import qzl.com.qgmusickotlin.ui.activity.SettingActivity


/**
 * @desc 所有界面toolbar的管理类
 * @author Qzl
 * @email 2538096489@qq.com
 * @time 2019-02-01 12:03
 * @class QGMusicKotlin
 * @package qzl.com.qgmusickotlin.util
 */
interface ToolBarManage {
    val toolbar: Toolbar
    //初始化主界面中的toolbar
    fun initMainToolBar(){
        toolbar.title = "强哥影音"
        toolbar.inflateMenu(R.menu.main)
        //kotlin 和 java调运特性
        //如果java接口中只有一个未实现的方法，可以省略接口对象，直接{}表示未实现的方法
        toolbar.setOnMenuItemClickListener {
            when(it?.itemId){
                R.id.setting -> {
                    //跳转到设计界面
                    toolbar.context.startActivity<SettingActivity>()
                }
            }
            true
        }
        /*toolbar.setOnMenuItemClickListener(object :Toolbar.OnMenuItemClickListener{
            override fun onMenuItemClick(item: MenuItem?): Boolean {
                when(item?.itemId){
                    R.id.setting -> {
                        //跳转到设计界面
                        toolbar.context.startActivity<SettingActivity>()
                    }
                }
                return true
            }
        })*/
    }
    fun initSettingToolBar(){
        toolbar.title = "设置界面"
    }
}