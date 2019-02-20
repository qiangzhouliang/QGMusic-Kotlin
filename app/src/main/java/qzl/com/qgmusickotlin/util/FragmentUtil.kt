package qzl.com.qgmusickotlin.util

import qzl.com.qgmusickotlin.R
import qzl.com.qgmusickotlin.base.BaseFragment
import qzl.com.qgmusickotlin.ui.fragment.HomeFragment
import qzl.com.qgmusickotlin.ui.fragment.MvFragment
import qzl.com.qgmusickotlin.ui.fragment.VBangFragment
import qzl.com.qgmusickotlin.ui.fragment.YueDanFragment

/**
 * ClassName:FragmentUtil
 * Description:管理fragment的util类
 */
class FragmentUtil private constructor(){//私有化构造方法
    val homeFragment by lazy { HomeFragment() }
    val mvFragment by lazy { MvFragment() }
    val vbangFragment by lazy { VBangFragment() }
    val yuedanFragment by lazy { YueDanFragment() }
    //伴生对象
    companion object {
        val fragmentUtil by lazy { FragmentUtil() }
    }

    /**
     * 根据tabid获取对应的fragment
     */
    fun getFragment(tabId:Int): BaseFragment?{
        when(tabId){
            R.id.tab_home -> return homeFragment
            R.id.tab_mv -> return mvFragment
            R.id.tab_vbang -> return vbangFragment
            R.id.tab_yuedan -> return yuedanFragment
        }
        return null
    }
}