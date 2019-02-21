package qzl.com.qgmusickotlin.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.itheima.player.model.bean.HomeItemBean
import qzl.com.qgmusickotlin.view.LoadMoreView
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
    fun updateList(list: List<HomeItemBean>?){
        list?.let {
            this.list.clear()
            this.list.addAll(list)
            notifyDataSetChanged()
        }

    }

    /**
     * 加载更多方法
     */
    fun loadMore(list: List<HomeItemBean>?){
        list?.let {
            this.list.addAll(list)
            notifyDataSetChanged()
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): HomeHolder {
        when(viewType){
            //最后一个条目
            1 -> return HomeHolder(LoadMoreView(parent?.context))
            //其他条目
            else ->  return HomeHolder(HomeItemView(parent?.context))
        }

    }

    override fun getItemViewType(position: Int): Int {
        if (position == list.size){
            //说明是最后一条
            return 1
        }else{
            return 0
        }
    }
    override fun getItemCount(): Int {
        return list.size+1
    }

    override fun onBindViewHolder(holder: HomeHolder, position: Int) {
        //如果是最后一条，就不需要刷新了
        if(position == list.size) return
        //条目数据
        val data = list.get(position)
        //条目view
        val itemView = holder.itemView as HomeItemView
        //条目刷新
        itemView.setData(data)
    }

    class HomeHolder(itemView:View):RecyclerView.ViewHolder(itemView){

    }
}