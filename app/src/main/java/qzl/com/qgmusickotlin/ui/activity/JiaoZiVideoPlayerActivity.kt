package qzl.com.qgmusickotlin.ui.activity

import android.support.v4.view.ViewPager
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard
import kotlinx.android.synthetic.main.activity_video_player_jiecao.*
import qzl.com.qgmusickotlin.R
import qzl.com.qgmusickotlin.adapter.VideoPageAdapter
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
class JiaoZiVideoPlayerActivity: BaseActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_video_player_jiecao
    }

    override fun initData() {
        var data = intent.data;
        if (data == null){
            //从应用内响应
            //获取传递的数据
            val videoBean = intent.getParcelableExtra<VideoPlayBean>("item")
            videoplayer.setUp(videoBean.url,
                JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL,videoBean.title)
        }else{
            if (data.toString().startsWith("http:")){
                //外部网络视频
                videoplayer.setUp(data.toString(),
                    JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL,data.toString())
            }else{
                //从应用外响应
                videoplayer.setUp(data.path,
                    JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL,data.toString())
            }
        }

    }
    override fun onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return
        }
        super.onBackPressed()
    }

    override fun onPause() {
        super.onPause()
        JCVideoPlayer.releaseAllVideos()
    }

    override fun initListener() {
        //适配viewPager
        viewPager.adapter = VideoPageAdapter(supportFragmentManager)
        //radiogroup 选中监听
        rg.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId){
                R.id.rb1 -> viewPager.setCurrentItem(0)
                R.id.rb2 -> viewPager.setCurrentItem(1)
                R.id.rb3 -> viewPager.setCurrentItem(2)
            }
        }
        //viewpage设置选中状态监听
        viewPager.addOnPageChangeListener(object :ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {
                //滑动状态改变
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                //滑动的回调
            }

            override fun onPageSelected(position: Int) {
                //选中状态改变
                when(position){
                    0 -> rg.check(R.id.rb1)
                    1 -> rg.check(R.id.rb2)
                    2 -> rg.check(R.id.rb3)
                }
            }

        })
    }
}