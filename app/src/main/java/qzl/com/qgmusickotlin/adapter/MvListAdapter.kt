package qzl.com.qgmusickotlin.adapter

import android.content.Context
import com.itheima.player.model.bean.VideosBean
import qzl.com.qgmusickotlin.base.BasListAdapter
import qzl.com.qgmusickotlin.widget.MvItemView

/**
 * @desc mv界面每一个列表的适配器
 * @author Qzl
 * @email 2538096489@qq.com
 * @time 2019-02-23 12:57
 * @class QGMusicKotlin
 * @package qzl.com.qgmusickotlin.adapter
 */
class MvListAdapter :BasListAdapter<VideosBean,MvItemView>(){
    override fun getItemView(context: Context?): MvItemView {
        return MvItemView(context)
    }

    override fun refreshView(itemView: MvItemView, data: VideosBean) {
        itemView.setData(data)
    }
}