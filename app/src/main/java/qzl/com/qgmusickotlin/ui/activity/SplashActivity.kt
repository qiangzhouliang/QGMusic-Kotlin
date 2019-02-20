package qzl.com.qgmusickotlin.ui.activity

import android.support.v4.view.ViewCompat
import android.support.v4.view.ViewPropertyAnimatorListener
import android.view.View
import kotlinx.android.synthetic.main.p_splach.*
import qzl.com.qgmusickotlin.R
import qzl.com.qgmusickotlin.base.BaseActivity

class SplashActivity: BaseActivity(),ViewPropertyAnimatorListener {

    override fun getLayoutId(): Int {
        return R.layout.p_splach
    }

    override fun initData() {
        //缩小动画
        ViewCompat.animate(imageView).scaleX(1.0f).scaleY(1.0f).setListener(this).duration = 2000
    }

    override fun onAnimationEnd(p0: View?) {
        //进入到主界面
        startActivityAndFFinish<MainActivity>()
    }

    override fun onAnimationCancel(p0: View?) {
    }

    override fun onAnimationStart(p0: View?) {
    }
}
