package qzl.com.qgmusickotlin.base

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import qzl.com.qgmusickotlin.view.LoadMoreView

/**
 * @desc 所有下拉刷新和上拉加载更多列表界面基类
 * @author Qzl
 * @email 2538096489@qq.com
 * @time 2019-02-23 10:28
 * @class QGMusicKotlin
 * @package qzl.com.qgmusickotlin.base
 */
abstract class BasListAdapter <ITEMBEAN,ITEMVIEW:View>: RecyclerView.Adapter<BasListAdapter.BasListHolder>() {
    private var list = ArrayList<ITEMBEAN>()
    /**
     * 更新数据
     */
    fun updateList(list: List<ITEMBEAN>?){
        list?.let {
            this.list.clear()
            this.list.addAll(list)
            notifyDataSetChanged()
        }

    }

    /**
     * 加载更多方法
     */
    fun loadMore(list: List<ITEMBEAN>?){
        list?.let {
            this.list.addAll(list)
            notifyDataSetChanged()
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BasListHolder? {
        when(viewType){
            //最后一个条目
            1 -> return BasListHolder(LoadMoreView(parent?.context))
            //其他条目
            else ->  return BasListHolder(getItemView(parent?.context))
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
    override fun onBindViewHolder(holder: BasListHolder?, position: Int) {
        //如果是最后一条，就不需要刷新了
        if(position == list.size) return
        //条目数据
        val data = list.get(position)
        //条目view
        val itemView = holder?.itemView as ITEMVIEW
        //条目刷新
        refreshView(itemView,data)
        //设置点击事件
        itemView.setOnClickListener {
            //条目点击事件
            listener?.let {
                it(data)
            }
        }
    }
    //定义了一个函数类型的变量
    var listener:((itemBean:ITEMBEAN)->Unit)? = null
    fun setMyListener(listener:(itemBean:ITEMBEAN)->Unit){
        this.listener = listener
    }
    /**
     * 获取条目的view
     */
    abstract fun getItemView(context: Context?): ITEMVIEW
    /**
     * 刷新条目view
     */
    abstract fun refreshView(itemView: ITEMVIEW, data: ITEMBEAN)

    class BasListHolder(itemView: View): RecyclerView.ViewHolder(itemView){}
}