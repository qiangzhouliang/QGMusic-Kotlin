package qzl.com.qgmusickotlin.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import qzl.com.qgmusickotlin.R


/**
 * @desc 加载更多进度条
 * @author Qzl
 * @email 2538096489@qq.com
 * @time 2019-02-21 13:17
 * @class QGMusicKotlin
 * @package qzl.com.qgmusickotlin.view
 */
class LoadMoreView : RelativeLayout {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    init {
        View.inflate(context, R.layout.view_loadmore,this)
    }
}