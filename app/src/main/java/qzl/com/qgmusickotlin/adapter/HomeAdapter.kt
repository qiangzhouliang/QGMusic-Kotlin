package qzl.com.qgmusickotlin.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.itheima.player.model.bean.HomeItemBean
import qzl.com.qgmusickotlin.widget.HomeItemView

/**
 * @desc
 * @author Qzl
 * @email 2538096489@qq.com
 * @time 2019-02-01 17:19
 * @class QGMusicKotlin
 * @package qzl.com.qgmusickotlin.adapter
 */
class HomeAdapter : RecyclerView.Adapter<HomeAdapter.HomeHolder>() {
    private var list = ArrayList<HomeItemBean>()
    /**
     * 更新数据
     */
    fun updateList(list: List<HomeItemBean>){
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): HomeHolder {
        return HomeHolder(HomeItemView(parent?.context))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: HomeHolder?, position: Int) {

    }

    class HomeHolder(itemView:View):RecyclerView.ViewHolder(itemView){

    }
}