package qzl.com.qgmusickotlin.ui.fragment

import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.widget.TextView
import org.jetbrains.anko.textColor
import qzl.com.qgmusickotlin.base.BaseFragment

/**
 * @desc 视频播放界面
 * @author Qzl
 * @email 2538096489@qq.com
 * @time 2019-02-23 23:47
 * @class QGMusicKotlin
 * @package qzl.com.qgmusickotlin.ui.fragment
 */
class DefaultFragment: BaseFragment() {
    override fun initView(): View? {
        var tv = TextView(context)
        tv.gravity = Gravity.CENTER
        tv.textColor = Color.RED
        tv.text = javaClass.simpleName
        return tv
    }
}