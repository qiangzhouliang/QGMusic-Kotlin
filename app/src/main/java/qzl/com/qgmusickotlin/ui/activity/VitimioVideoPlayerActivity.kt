package qzl.com.qgmusickotlin.ui.activity

import io.vov.vitamio.Vitamio
import kotlinx.android.synthetic.main.activity_video_player_vitamio.*
import qzl.com.qgmusickotlin.R
import qzl.com.qgmusickotlin.base.BaseActivity
import qzl.com.qgmusickotlin.model.VideoPlayBean

/**
 * @desc
 * @author Qzl
 * @email 2538096489@qq.com
 * @time 2019-02-23 14:10
 * @class QGMusicKotlin
 * @package qzl.com.qgmusickotlin.ui.activity
 */
class VitimioVideoPlayerActivity: BaseActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_video_player_vitamio
    }

    override fun initData() {
        //初始化vitamio
        Vitamio.isInitialized(this)
        //获取传递的数据
        val videoBean = intent.getParcelableExtra<VideoPlayBean>("item")
        videoView.setVideoPath(videoBean.url)//异步准备
        videoView.setOnPreparedListener {
            videoView.start()
        }
//        videoView.start()
    }

}