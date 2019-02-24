package qzl.com.qgmusickotlin.adapter

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.itheima.player.model.bean.MvAreaBean
import qzl.com.qgmusickotlin.ui.fragment.MvPageFragment

/**
 * @desc
 * @author Qzl
 * @email 2538096489@qq.com
 * @time 2019-02-23 11:46
 * @class QGMusicKotlin
 * @package qzl.com.qgmusickotlin.adapter
 */
class MvPageAdapter(val context: Context,val list: List<MvAreaBean>?, fm: FragmentManager?) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        //第一种传值的方式
//        val fragment = MvPageFragment()
        val bundle = Bundle()
        bundle.putString("args",list?.get(position)?.code)
//        fragment.arguments = bundle

        //第二种传值的方式
        val fragment = Fragment.instantiate(context,MvPageFragment::class.java.name,bundle)
        return fragment
    }

    override fun getCount(): Int {
        return list?.size?:0
    }

    override fun getPageTitle(position: Int): CharSequence {
        return list?.get(position)?.name?:""
    }
}