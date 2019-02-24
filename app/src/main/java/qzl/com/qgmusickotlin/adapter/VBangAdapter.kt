package qzl.com.qgmusickotlin.adapter

import android.content.Context
import android.database.Cursor
import android.support.v4.widget.CursorAdapter
import android.view.View
import android.view.ViewGroup
import qzl.com.qgmusickotlin.model.AudioBean
import qzl.com.qgmusickotlin.widget.VBangItemView


/**
 * @desc v榜界面列表适配器
 * @author Qzl
 * @email 2538096489@qq.com
 * @time 2019-02-24 11:42
 * @class QGMusicKotlin
 * @package qzl.com.qgmusickotlin.adapter
 */
class VBangAdapter(context: Context?, c: Cursor?) : CursorAdapter(context, c) {
    /**
     * 创建条目view
     */
    override fun newView(context: Context?, cursor: Cursor?, parent: ViewGroup?): View {
        return VBangItemView(context)
    }

    /**
     * view 和数据绑定
     */
    override fun bindView(view: View?, context: Context?, cursor: Cursor?) {
        //view
        val itemView = view as VBangItemView
        //数据
        val itemBean = AudioBean.getAudioBean(cursor)
        //绑定
        itemView.setData(itemBean)
    }
}