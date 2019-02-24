package qzl.com.qgmusickotlin.presenter.impl

import com.itheima.player.model.bean.MvAreaBean
import qzl.com.qgmusickotlin.net.MvAreaRequest
import qzl.com.qgmusickotlin.net.ResponseHandler
import qzl.com.qgmusickotlin.presenter.interf.MvPresenter
import qzl.com.qgmusickotlin.view.MVView

/**
 * @desc
 * @author Qzl
 * @email 2538096489@qq.com
 * @time 2019-02-23 11:32
 * @class QGMusicKotlin
 * @package qzl.com.qgmusickotlin.presenter.impl
 */
class MvPresenterImpl(var mvView: MVView):MvPresenter, ResponseHandler<List<MvAreaBean>> {
    override fun onError(type: Int, msg: String?) {
        mvView.onError(msg)
    }

    override fun OnSuccess(type: Int, result: List<MvAreaBean>) {
        mvView.onSuccess(result)
    }

    //加载区域数据
    override fun loadDatas() {
        MvAreaRequest(this).execute()
    }
}