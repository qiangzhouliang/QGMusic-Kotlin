package qzl.com.qgmusickotlin.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * @desc 所有activity的基类
 * @author Qzl
 * @email 2538096489@qq.com
 * @time 2019-02-01 10:09
 * @class QGMusicKotlin
 * @package qzl.com.qgmusickotlin.base
 */
abstract class BaseActivity:AppCompatActivity(),AnkoLogger {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initListener()
        initData()
    }

    /**
     * 初始化数据
     */
    open protected fun initData() {
    }

    /**
     * adapter listener相关的操作
     */
    open protected fun initListener() {
    }

    /**
     * 获取布局id
     */
    abstract fun getLayoutId(): Int

    protected fun myToast(msg:String){
        runOnUiThread { toast(msg)}
    }

    /**
     * 开启activity 并且finish当前界面
     */
    inline fun <reified T:BaseActivity>startActivityAndFFinish(){
        //进入到主界面
        startActivity<T>()
        finish()
    }
}