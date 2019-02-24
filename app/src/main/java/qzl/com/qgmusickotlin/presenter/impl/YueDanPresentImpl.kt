package qzl.com.qgmusickotlin.presenter.impl

import com.itheima.player.model.bean.YueDanBean
import qzl.com.qgmusickotlin.base.BasListPresenter.Companion.TYPE_INIT_OR_REFRESH
import qzl.com.qgmusickotlin.base.BasListPresenter.Companion.TYPE_LOAD_MORE
import qzl.com.qgmusickotlin.base.BaseView
import qzl.com.qgmusickotlin.net.ResponseHandler
import qzl.com.qgmusickotlin.net.YuedanRequest
import qzl.com.qgmusickotlin.presenter.interf.YueDanPresenter

/**
 * @desc
 * @author Qzl
 * @email 2538096489@qq.com
 * @time 2019-02-21 19:19
 * @class QGMusicKotlin
 * @package qzl.com.qgmusickotlin.presenter.impl
 */
class YueDanPresentImpl(var yueDanView: BaseView<YueDanBean>?):YueDanPresenter, ResponseHandler<YueDanBean> {
    /**
     * 解绑view和presenter
     */
    override fun destroyView(){
        yueDanView.let {
            yueDanView = null
        }
    }
    override fun onError(type: Int, msg: String?) {
        yueDanView?.onError(msg)
    }

    override fun OnSuccess(type: Int, result: YueDanBean) {
        when(type){
            TYPE_INIT_OR_REFRESH -> yueDanView?.loadSuccess(result)
            TYPE_LOAD_MORE -> yueDanView?.loadMoreSuccess(result)
        }
    }

    override fun loadDatas() {
        YuedanRequest(TYPE_INIT_OR_REFRESH,0,this).execute()
    }

    override fun loadMore(offset: Int) {
        YuedanRequest(TYPE_LOAD_MORE,offset,this).execute()
    }
}