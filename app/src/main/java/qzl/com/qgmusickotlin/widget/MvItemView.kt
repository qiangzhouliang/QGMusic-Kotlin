package qzl.com.qgmusickotlin.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.itheima.player.model.bean.VideosBean
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_mv.view.*
import qzl.com.qgmusickotlin.R

/**
 * @desc mv每一个界面条目view
 * @author Qzl
 * @email 2538096489@qq.com
 * @time 2019-02-23 12:51
 * @class QGMusicKotlin
 * @package qzl.com.qgmusickotlin.widget
 */
class MvItemView:RelativeLayout {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    init {
        View.inflate(context, R.layout.item_mv,this)
    }

    /**
     * 适配每一个条目的view
     */
    fun setData(data: VideosBean) {
        //歌手名称
        artist.text = data.artistName
        //歌曲名称
        title.text = data.title
        //背景图
        Picasso.with(context).load(data.playListPic).into(bg)
    }
}