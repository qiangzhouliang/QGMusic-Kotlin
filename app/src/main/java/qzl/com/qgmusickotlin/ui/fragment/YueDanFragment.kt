package qzl.com.qgmusickotlin.ui.fragment

import com.itheima.player.model.bean.YueDanBean
import qzl.com.qgmusickotlin.adapter.YueDanAdapter
import qzl.com.qgmusickotlin.base.BasListAdapter
import qzl.com.qgmusickotlin.base.BasListFragment
import qzl.com.qgmusickotlin.base.BasListPresenter
import qzl.com.qgmusickotlin.presenter.impl.YueDanPresentImpl
import qzl.com.qgmusickotlin.widget.YueDanItemView

/**
 * ClassName:HomeFragment
 * Description: 悦单界面
 */
class YueDanFragment : BasListFragment<YueDanBean,YueDanBean.PlayListsBean,YueDanItemView>(){
    override fun getSpecAdapter(): BasListAdapter<YueDanBean.PlayListsBean, YueDanItemView> {
        return YueDanAdapter()
    }

    override fun getSpecPresenter(): BasListPresenter {
        return YueDanPresentImpl(this)
    }

    override fun getList(response: YueDanBean?): List<YueDanBean.PlayListsBean>? {
        return response?.playLists
    }
    override fun onDestroyView() {
        super.onDestroyView()
        //解绑view
        presenter.destroyView()
    }
}