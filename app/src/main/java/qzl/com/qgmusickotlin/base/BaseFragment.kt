package qzl.com.qgmusickotlin.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.runOnUiThread
import org.jetbrains.anko.toast

/**
 * @desc 所有fragment的基类
 * @author Qzl
 * @email 2538096489@qq.com
 * @time 2019-02-01 10:19
 * @class QGMusicKotlin
 * @package qzl.com.qgmusickotlin.base
 */
abstract class BaseFragment: Fragment(),AnkoLogger {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    /**
     * fragment初始化
     */
    open protected fun init() {
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return initView()
    }

    /**
     * 获取布局view
     */
    abstract fun initView():View?

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initListener()
        initData()
    }

    /**
     * 数据的初始化
     */
    open protected fun initData() {
    }

    /**
     * adapter 和 listener 的操作
     */
    open protected fun initListener() {
    }
    protected fun myToast(msg:String){
        context?.runOnUiThread { toast(msg) }
    }
}