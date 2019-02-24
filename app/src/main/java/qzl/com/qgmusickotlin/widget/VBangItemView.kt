package qzl.com.qgmusickotlin.widget

import android.content.Context
import android.text.format.Formatter
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.item_vbang.view.*
import qzl.com.qgmusickotlin.R
import qzl.com.qgmusickotlin.model.AudioBean

/**
 * @desc v榜界面view
 * @author Qzl
 * @email 2538096489@qq.com
 * @time 2019-02-24 11:37
 * @class QGMusicKotlin
 * @package qzl.com.qgmusickotlin.widget
 */
class VBangItemView:RelativeLayout {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    init {
        View.inflate(context, R.layout.item_vbang,this)
    }
    //view 和数据绑定
    fun setData(itemBean: AudioBean) {
        //歌曲名
        title.text = itemBean.display_name
        //歌手名
        artist.text = itemBean.artist
        //歌曲大小
        size.text = Formatter.formatFileSize(context,itemBean.size)
    }
}