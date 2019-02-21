package qzl.com.qgmusickotlin.ui.fragment

import android.graphics.Color
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.itheima.player.model.bean.HomeItemBean
import kotlinx.android.synthetic.main.fragment_home.*
import qzl.com.qgmusickotlin.R
import qzl.com.qgmusickotlin.adapter.HomeAdapter
import qzl.com.qgmusickotlin.base.BaseFragment
import qzl.com.qgmusickotlin.presenter.impl.HomePresenterImpl
import qzl.com.qgmusickotlin.view.HomeView

/**
 * ClassName:HomeFragment
 * Description:
 */
class HomeFragment : BaseFragment(), HomeView {
    //适配
    val adapter by lazy { HomeAdapter()}
    val presenter by lazy { HomePresenterImpl(this) }
    override fun initView(): View? {
        return View.inflate(context, R.layout.fragment_home,null)
    }

    override fun initListener() {
        //初始化Recycleview
        recycleView.layoutManager = LinearLayoutManager(context)
        recycleView.adapter = adapter

        //初始化刷新控件
        //1 设置控件颜色
        refreshLayout.setColorSchemeColors(Color.RED,Color.YELLOW,Color.GREEN)
        //2设置刷新监听
        refreshLayout.setOnRefreshListener {
            //刷新监听
            presenter.loadDatas()
        }

        //监听列表的滑动
        recycleView.setOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                //滑动状态的改变
                /*when(newState){
                    RecyclerView.SCROLL_STATE_IDLE ->{
                        println("空闲状态")
                    }
                    RecyclerView.SCROLL_STATE_DRAGGING ->{
                        println("拖动状态")
                    }
                    RecyclerView.SCROLL_STATE_SETTLING ->{
                        println(" SETTLING 状态")
                    }
                }*/
                if (newState == RecyclerView.SCROLL_STATE_IDLE){
                    //空闲状态，是否是最后一条已经显示
                    val layoutManager = recyclerView.layoutManager
                    if (layoutManager is LinearLayoutManager){
                        val manage = layoutManager
                        val lastPosition = manage.findLastVisibleItemPosition()
                        if (lastPosition == adapter.itemCount - 1){
                            //最后一条已经显示了
                            presenter.loadMore(adapter.itemCount - 1)
                        }
                    }
                }
            }

            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                println("onScrolled dx = $dx dy = $dy")
            }
        })
    }

    override fun initData() {
        //初始化数据
        presenter.loadDatas()
    }
    override fun onError(message: String?) {
        //隐藏刷新控件
        refreshLayout.isRefreshing = false
        myToast("加载数据失败")
    }

    override fun loadSuccess(list: List<HomeItemBean>?) {
        //隐藏刷新控件
        refreshLayout.isRefreshing = false
        //刷新列表
        adapter.updateList(list)
    }

    override fun loadMoreSuccess(list: List<HomeItemBean>?) {
        //刷新列表
        adapter.loadMore(list)
    }
}