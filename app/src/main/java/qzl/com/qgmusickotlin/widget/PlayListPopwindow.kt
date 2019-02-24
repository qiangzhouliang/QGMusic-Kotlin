package qzl.com.qgmusickotlin.widget

import android.content.Context
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.view.*
import android.widget.AdapterView
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.PopupWindow
import org.jetbrains.anko.find
import qzl.com.qgmusickotlin.R


/**
 * ClassName:PlayListPopWindow
 * Description:
 */
class PlayListPopWindow(context: Context, adapter: BaseAdapter, listener: AdapterView.OnItemClickListener, val window: Window) : PopupWindow() {
    //记录当前应用程序窗体透明度
    var alpha: Float = 0f

    init {
        //记录当前窗体的透明度
        alpha = window.attributes.alpha
        //设置布局
        val view = LayoutInflater.from(context).inflate(R.layout.pop_playlist, null, false)
        //获取listview
        val listView = view.find<ListView>(R.id.listView)
        //适配
        listView.adapter = adapter
        //设置列表条目点击事件
        listView.setOnItemClickListener(listener)
        contentView = view
        //设置宽度和高度
        width = ViewGroup.LayoutParams.MATCH_PARENT
        //设置高度为屏幕高度3/5
        val manager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val point = Point()
        manager.defaultDisplay.getSize(point)
        val windowH = point.y
        height = (windowH * 3) / 5
        //设置获取焦点
        isFocusable = true
        //设置外部点击
        isOutsideTouchable = true
        //能够响应返回按钮(低版本popwindow点击返回按钮能够dismiss关键)
        setBackgroundDrawable(ColorDrawable())
        //处理popwindow动画
        animationStyle = R.style.pop
    }

    override fun showAsDropDown(anchor: View?, xoff: Int, yoff: Int, gravity: Int) {
        super.showAsDropDown(anchor, xoff, yoff, gravity)
        //popwindow已经显示
        val attributes = window.attributes
        attributes.alpha = 0.3f
        //设置到应用程序窗体上
        window.attributes = attributes
    }

    override fun dismiss() {
        super.dismiss()
        //popwindows隐藏 恢复应用程序窗体透明度
        val attributes = window.attributes
        attributes.alpha = alpha
        window.attributes = attributes
    }
}