package qzl.com.qgmusickotlin.presenter.impl

import com.itheima.player.model.bean.HomeItemBean
import qzl.com.qgmusickotlin.net.HomeRequest
import qzl.com.qgmusickotlin.net.ResponseHandler
import qzl.com.qgmusickotlin.presenter.interf.HomePresenter
import qzl.com.qgmusickotlin.presenter.interf.HomePresenter.Companion.TYPE_INIT_OR_REFRESH
import qzl.com.qgmusickotlin.presenter.interf.HomePresenter.Companion.TYPE_LOAD_MORE
import qzl.com.qgmusickotlin.view.HomeView

/**
 * @desc 首页业务逻辑处理类
 * @author Qzl
 * @email 2538096489@qq.com
 * @time 2019-02-21 13:51
 * @class QGMusicKotlin
 * @package qzl.com.qgmusickotlin.presenter.impl
 */
class HomePresenterImpl(var homeView: HomeView) :HomePresenter, ResponseHandler<List<HomeItemBean>> {
    /**
     * 初始化数据或刷新数据
     */
    override fun loadDatas() {
        //1定义一个request+执行
        HomeRequest(TYPE_INIT_OR_REFRESH,0,this).execute()
    }

    override fun loadMore(offset: Int) {
        //1定义一个request
        HomeRequest(TYPE_LOAD_MORE,offset,this).execute()
    }

    /**
     * 加载数据失败
     */
    override fun onError(type:Int,msg: String?) {
        homeView.onError(msg)
    }
    /**
     * 加载数据成功
     */
    override fun OnSuccess(type:Int,result: List<HomeItemBean>) {
        //区分 初始化数据和加载更多数据
        when(type){
            TYPE_INIT_OR_REFRESH -> homeView.loadSuccess(result)
            TYPE_LOAD_MORE -> homeView.loadMoreSuccess(result)
        }

    }
}