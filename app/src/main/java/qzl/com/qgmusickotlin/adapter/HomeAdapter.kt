package qzl.com.qgmusickotlin.adapter

import android.content.Context
import com.itheima.player.model.bean.HomeItemBean
import qzl.com.qgmusickotlin.base.BasListAdapter
import qzl.com.qgmusickotlin.widget.HomeItemView

/**
 * @desc
 * @author Qzl
 * @email 2538096489@qq.com
 * @time 2019-02-01 17:19
 * @class QGMusicKotlin
 * @package qzl.com.qgmusickotlin.adapter
 */
class HomeAdapter :BasListAdapter<HomeItemBean,HomeItemView>() {
    override fun getItemView(context: Context?): HomeItemView {
        return HomeItemView(context)
    }

    override fun refreshView(itemView: HomeItemView, data: HomeItemBean) {
        itemView.setData(data)
    }

}