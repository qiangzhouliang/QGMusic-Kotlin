package qzl.com.qgmusickotlin.ui.fragment

import com.itheima.player.model.bean.HomeItemBean
import qzl.com.qgmusickotlin.adapter.HomeAdapter
import qzl.com.qgmusickotlin.base.BasListAdapter
import qzl.com.qgmusickotlin.base.BasListFragment
import qzl.com.qgmusickotlin.base.BasListPresenter
import qzl.com.qgmusickotlin.presenter.impl.HomePresenterImpl
import qzl.com.qgmusickotlin.widget.HomeItemView

/**
 * ClassName:HomeFragment
 * Description:
 */
class HomeFragment :BasListFragment<List<HomeItemBean>,HomeItemBean,HomeItemView>(){
    override fun getSpecAdapter(): BasListAdapter<HomeItemBean, HomeItemView> {
        return HomeAdapter()
    }

    override fun getSpecPresenter(): BasListPresenter {
        return HomePresenterImpl(this)
    }

    override fun getList(response: List<HomeItemBean>?): List<HomeItemBean>? {
        return response
    }
    override fun onDestroyView() {
        super.onDestroyView()
        //解绑view
        presenter.destroyView()
    }
}