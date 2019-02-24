package qzl.com.qgmusickotlin.ui.activity

import kotlinx.android.synthetic.main.activity_video_player.*
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
class VideoPlayerActivity: BaseActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_video_player
    }

    override fun initData() {
        //获取传递的数据
        val videoBean = intent.getParcelableExtra<VideoPlayBean>("item")
        videoView.setVideoPath(videoBean.url)//异步准备
        videoView.setOnPreparedListener {
            videoView.start()
        }
//        videoView.start()
    }

}