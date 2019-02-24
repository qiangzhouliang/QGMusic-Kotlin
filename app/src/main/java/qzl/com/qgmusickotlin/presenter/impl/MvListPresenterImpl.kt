package qzl.com.qgmusickotlin.presenter.impl

import com.itheima.player.model.bean.MvPagerBean
import qzl.com.qgmusickotlin.base.BasListPresenter.Companion.TYPE_INIT_OR_REFRESH
import qzl.com.qgmusickotlin.base.BasListPresenter.Companion.TYPE_LOAD_MORE
import qzl.com.qgmusickotlin.net.MvListRequest
import qzl.com.qgmusickotlin.net.ResponseHandler
import qzl.com.qgmusickotlin.presenter.interf.MvListPresenter
import qzl.com.qgmusickotlin.view.MvListView

/**
 * @desc
 * @author Qzl
 * @email 2538096489@qq.com
 * @time 2019-02-23 13:03
 * @class QGMusicKotlin
 * @package qzl.com.qgmusickotlin.presenter.impl
 */
class MvListPresenterImpl(var code:String,var mvListView: MvListView?):MvListPresenter, ResponseHandler<MvPagerBean> {
    override fun onError(type: Int, msg: String?) {
        mvListView?.onError(msg)
    }

    override fun OnSuccess(type: Int, result: MvPagerBean) {
        when(type){
            TYPE_INIT_OR_REFRESH -> mvListView?.loadSuccess(result)
            TYPE_LOAD_MORE -> mvListView?.loadMoreSuccess(result)
        }
    }

    override fun loadDatas() {
        MvListRequest(TYPE_INIT_OR_REFRESH,code,0,this).execute()
    }

    override fun loadMore(offset: Int) {
        MvListRequest(TYPE_LOAD_MORE,code,offset,this).execute()
    }

    override fun destroyView() {
        mvListView.let {
            mvListView = null
        }
    }
}