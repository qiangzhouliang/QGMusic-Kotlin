package qzl.com.qgmusickotlin.ui.activity

import android.graphics.SurfaceTexture
import android.media.MediaPlayer
import android.view.Surface
import android.view.TextureView
import kotlinx.android.synthetic.main.activity_video_player_texture.*
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
class TexureVideoPlayerActivity: BaseActivity(), TextureView.SurfaceTextureListener {
    var videoBean: VideoPlayBean? = null
    val mediaPlayer by lazy { MediaPlayer() }
    override fun onSurfaceTextureSizeChanged(surface: SurfaceTexture?, width: Int, height: Int) {
        //view大小发生变化
    }

    override fun onSurfaceTextureUpdated(surface: SurfaceTexture?) {
        //试图更新
    }

    override fun onSurfaceTextureDestroyed(surface: SurfaceTexture?): Boolean {
        //试图销毁
        mediaPlayer.let {
            it.stop()
            it.release()
        }
        return true
    }

    override fun onSurfaceTextureAvailable(surface: SurfaceTexture?, width: Int, height: Int) {
        //试图可用的时候
        videoBean?.let {
            mediaPlayer.setDataSource(it.url)
            //设置播放视频画面
            mediaPlayer.setSurface(Surface(surface))
            //准备
            mediaPlayer.prepareAsync()
            mediaPlayer.setOnPreparedListener {
                it.start()
                //旋转画面
                textureView.rotation = 100f
            }
        }

    }

    override fun getLayoutId(): Int {
        return R.layout.activity_video_player_texture
    }

    override fun initData() {
        //获取传递的数据
        videoBean = intent.getParcelableExtra<VideoPlayBean>("item")
        textureView.surfaceTextureListener = this
    }

}