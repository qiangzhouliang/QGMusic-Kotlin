package qzl.com.qgmusickotlin.ui.fragment

import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.widget.TextView
import org.jetbrains.anko.textColor
import qzl.com.qgmusickotlin.base.BaseFragment

/**
 * ClassName:HomeFragment
 * Description:
 */
class YueDanFragment : BaseFragment() {
    override fun initView(): View? {
        var tv = TextView(context)
        tv.gravity = Gravity.CENTER
        tv.textColor = Color.RED
        tv.text = javaClass.simpleName
        return tv
    }

}