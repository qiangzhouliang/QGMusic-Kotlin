package qzl.com.qgmusickotlin.adapter

import android.content.Context
import com.itheima.player.model.bean.YueDanBean
import qzl.com.qgmusickotlin.base.BasListAdapter
import qzl.com.qgmusickotlin.widget.YueDanItemView

/**
 * @desc 悦单适配器
 * @author Qzl
 * @email 2538096489@qq.com
 * @time 2019-02-01 17:19
 * @class QGMusicKotlin
 * @package qzl.com.qgmusickotlin.adapter
 */
class YueDanAdapter : BasListAdapter<YueDanBean.PlayListsBean,YueDanItemView>(){
    override fun getItemView(context: Context?): YueDanItemView {
        return YueDanItemView(context)
    }

    override fun refreshView(itemView: YueDanItemView, data: YueDanBean.PlayListsBean) {
        itemView.setData(data)
    }

}