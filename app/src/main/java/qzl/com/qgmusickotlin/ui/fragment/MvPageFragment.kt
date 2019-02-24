package qzl.com.qgmusickotlin.ui.fragment

import com.itheima.player.model.bean.MvPagerBean
import com.itheima.player.model.bean.VideosBean
import org.jetbrains.anko.support.v4.startActivity
import qzl.com.qgmusickotlin.adapter.MvListAdapter
import qzl.com.qgmusickotlin.base.BasListAdapter
import qzl.com.qgmusickotlin.base.BasListFragment
import qzl.com.qgmusickotlin.base.BasListPresenter
import qzl.com.qgmusickotlin.model.VideoPlayBean
import qzl.com.qgmusickotlin.presenter.impl.MvListPresenterImpl
import qzl.com.qgmusickotlin.ui.activity.JiaoZiVideoPlayerActivity
import qzl.com.qgmusickotlin.view.MvListView
import qzl.com.qgmusickotlin.widget.MvItemView

/**
 * @desc mv界面每个页面的fragment
 * @author Qzl
 * @email 2538096489@qq.com
 * @time 2019-02-23 11:49
 * @class QGMusicKotlin
 * @package qzl.com.qgmusickotlin.ui.fragment
 */
class MvPageFragment: BasListFragment<MvPagerBean,VideosBean,MvItemView>(), MvListView {
    var code:String? = null
    override fun init() {
        //获取传递的数据
        code = arguments.getString("args")
    }
    override fun getSpecAdapter(): BasListAdapter<VideosBean, MvItemView> {
        return MvListAdapter()
    }

    override fun getSpecPresenter(): BasListPresenter {
        return MvListPresenterImpl(code!!,this)
    }

    override fun getList(response: MvPagerBean?): List<VideosBean>? {
        return response?.videos
    }

    override fun initListener() {
        super.initListener()
        //设置条目点击事件监听函数
        adapter.setMyListener {
            //四种播放方式
            val videoPlayBean = VideoPlayBean(it.id,it.title,it.url)
//            startActivity<VideoPlayerActivity>("item" to videoPlayBean)
//            startActivity<TexureVideoPlayerActivity>("item" to videoPlayBean)
//            startActivity<VitimioVideoPlayerActivity>("item" to videoPlayBean)
//            startActivity<IJKVideoPlayerActivity>("item" to videoPlayBean)
            startActivity<JiaoZiVideoPlayerActivity>("item" to videoPlayBean)
        }
    }
}