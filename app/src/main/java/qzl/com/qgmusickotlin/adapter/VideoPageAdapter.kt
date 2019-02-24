package qzl.com.qgmusickotlin.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import qzl.com.qgmusickotlin.ui.fragment.DefaultFragment

/**
 * @desc
 * @author Qzl
 * @email 2538096489@qq.com
 * @time 2019-02-23 23:44
 * @class QGMusicKotlin
 * @package qzl.com.qgmusickotlin.adapter
 */
class VideoPageAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return DefaultFragment()
    }

    override fun getCount(): Int {
        return 3
    }
}