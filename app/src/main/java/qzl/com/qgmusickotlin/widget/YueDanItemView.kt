package qzl.com.qgmusickotlin.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.itheima.player.model.bean.YueDanBean
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import kotlinx.android.synthetic.main.item_yuedan.view.*
import qzl.com.qgmusickotlin.R

/**
 * @desc 悦单界面的条目的自定义view
 * @author Qzl
 * @email 2538096489@qq.com
 * @time 2019-02-21 19:06
 * @class QGMusicKotlin
 * @package qzl.com.qgmusickotlin.widget
 */
class YueDanItemView:RelativeLayout{
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    init {
        View.inflate(context, R.layout.item_yuedan,this)
    }

    /**
     * 条目view的控件初始化
     */
    fun setData(data: YueDanBean.PlayListsBean) {
        //歌单名称
        title.text = data.title
        //歌手名称
        author_name.text = data.creator?.nickName
        //歌曲个数
        count.text = data.videoCount.toString()
        //背景
        Picasso.with(context).load(data.playListBigPic).into(bg)
        //创建者头像
        Picasso.with(context).load(data.creator?.largeAvatar)
            .transform(CropCircleTransformation())
            .into(author_image)
    }
}