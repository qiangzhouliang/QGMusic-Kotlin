package qzl.com.qgmusickotlin.ui.fragment

import android.view.View
import com.itheima.player.model.bean.MvAreaBean
import kotlinx.android.synthetic.main.fragment_mv.*
import qzl.com.qgmusickotlin.R
import qzl.com.qgmusickotlin.adapter.MvPageAdapter
import qzl.com.qgmusickotlin.base.BaseFragment
import qzl.com.qgmusickotlin.presenter.impl.MvPresenterImpl
import qzl.com.qgmusickotlin.view.MVView


/**
 * ClassName:HomeFragment
 * Description:
 */
class MvFragment: BaseFragment(), MVView {
    override fun onError(msg: String?) {
        myToast("加载区域数据失败")
    }

    override fun onSuccess(result: List<MvAreaBean>) {
        //在fragment中管理fragment需要用childFragmentManager
        val adapter = MvPageAdapter(context,result,childFragmentManager)
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)
    }

    val presenter by lazy { MvPresenterImpl(this) }
    override fun initView(): View? {
        return View.inflate(context, R.layout.fragment_mv,null)
    }

    override fun initListener() {

    }

    override fun initData() {
        //加载区域数据
        presenter.loadDatas()
    }
}