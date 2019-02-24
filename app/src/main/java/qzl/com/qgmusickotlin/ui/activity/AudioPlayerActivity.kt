package qzl.com.qgmusickotlin.ui.activity
import android.content.ComponentName
import android.content.Context
import android.content.ServiceConnection
import android.graphics.drawable.AnimationDrawable
import android.os.Handler
import android.os.IBinder
import android.os.Message
import android.view.View
import android.widget.AdapterView
import android.widget.SeekBar
import com.itheima.player.service.AudioService
import com.itheima.player.service.Iservice
import de.greenrobot.event.EventBus
import kotlinx.android.synthetic.main.activity_music_player_bottom.*
import kotlinx.android.synthetic.main.activity_music_player_middle.*
import kotlinx.android.synthetic.main.activity_music_player_top.*
import qzl.com.qgmusickotlin.R
import qzl.com.qgmusickotlin.adapter.PopAdapter
import qzl.com.qgmusickotlin.base.BaseActivity
import qzl.com.qgmusickotlin.model.AudioBean
import qzl.com.qgmusickotlin.util.StringUtil
import qzl.com.qgmusickotlin.widget.PlayListPopWindow

/**
 * @desc
 * @author Qzl
 * @email 2538096489@qq.com
 * @time 2019-02-24 12:39
 * @class QGMusicKotlin
 * @package qzl.com.qgmusickotlin.ui.activity
 */
class AudioPlayerActivity: BaseActivity(), View.OnClickListener, SeekBar.OnSeekBarChangeListener,
    AdapterView.OnItemClickListener {
    var audioBean:AudioBean? = null
    var drawable:AnimationDrawable? = null
    var duration:Int = 0

    var handler = object :Handler(){
        override fun handleMessage(msg: Message?) {
            when(msg?.what){
                MSG_PROGRESS -> startUpdateProgress()
            }
        }
    }
    val MSG_PROGRESS = 0
    override fun onClick(v: View?) {
        when(v?.id){
            R.id.state -> updatePlayState()
            R.id.mode -> updatePlayMode()
            R.id.pre -> iService?.playPre()
            R.id.next -> iService?.playNext()
            R.id.playlist -> showPlayList()
        }
    }

    /**
     * 显示播放列表
     */
    private fun showPlayList() {
        val list = iService?.getPlayList()
        list?.let {
            //创建adapter
            val adapter = PopAdapter(it)
            val bottomH = audio_player_bottom.height
            val popupWindow = PlayListPopWindow(this,adapter,this,window)
            popupWindow.showAsDropDown(audio_player_bottom,0,bottomH)
        }

    }

    /**
     * 更新播放模式
     */
    private fun updatePlayMode() {
        //修改service中的mode
        iService?.updatePlayMode()
        //修改界面模式图标
        updatePlayModeBtn()
    }

    /**
     * 根据播放模式修改播放模式图标
     */
    private fun updatePlayModeBtn() {

        iService?.let {
            //获取播放模式
            val modeI = it.getPlayMode()
            //设置图标
            when(modeI){
                AudioService.MODE_ALL -> mode.setImageResource(R.drawable.selector_btn_playmode_order)
                AudioService.MODE_SINGLE -> mode.setImageResource(R.drawable.selector_btn_playmode_single)
                AudioService.MODE_RANDOM -> mode.setImageResource(R.drawable.selector_btn_playmode_random)
            }
        }

    }

    /**
     * 接收eventbus方法
     */
    fun onEventMainThread(itemBean:AudioBean){
        //设置播放歌曲名称
        lyricView.setSongName(itemBean.display_name)
        this.audioBean = itemBean
        //更新操作
        //歌曲名称
        audio_title.text = audioBean?.display_name
        //歌手名称
        artist.text = audioBean?.artist
        //更新播放状态名
        updatePlayStateBtn()
        //动画播放
        drawable = audio_anim.drawable as AnimationDrawable
        drawable?.start()
        //获取总进度
        duration = iService?.getDuration()?:0
        //设置歌词播放总进度
        lyricView.setSongDuration(duration)
        //设置进度最大值
        progress_sk.max = duration

        //更新播放进度
        startUpdateProgress()
        //更新播放模式图标
        updatePlayModeBtn()
    }

    /**
     * 开始更新进度
     */
    private fun startUpdateProgress() {
        //获取当前进度
        val progress = iService?.getProgress()?:0
        //更新进度数据
        updateProgress(progress)
        //定时获取进度
        handler.sendEmptyMessageDelayed(MSG_PROGRESS,1000)
    }

    /**
     * 根据当前进度数据更新界面
     */
    private fun updateProgress(pro: Int) {
        //更新进度数值
        progress.text = StringUtil.parseDuration(pro)+"/"+StringUtil.parseDuration(duration)
        //更新进度条
        progress_sk.setProgress(pro)
        //更新歌词播放进度
        lyricView.updateProgress(pro)
    }

    /**
     * 更新播放状态
     */
    private fun updatePlayState() {
        //更新播放状态
        iService?.updatePlayState()
        //更新播放状态图标
        updatePlayStateBtn()
    }

    /**
     * 更新状态图标
     */
    private fun updatePlayStateBtn() {
        //获取当前状态，
        val isPlay = iService?.isPlaying()
        isPlay?.let {
            //根据状态更新图标
            if (isPlay){
                //播放
                state.setImageResource(R.drawable.selector_btn_audio_play)
                drawable?.start()
                //开始更新进度
                handler.sendEmptyMessage(MSG_PROGRESS)
            }else{
                //暂停
                state.setImageResource(R.drawable.selector_btn_audio_pause)
                drawable?.stop()
                //停止更新进度
                handler.removeMessages(MSG_PROGRESS)
            }
        }
    }

    val connection by lazy { AudioConnection() }
    override fun getLayoutId(): Int {
        return R.layout.activity_audio_player
    }

    override fun initListener() {
        //播放状态切换
        state.setOnClickListener(this)
        back.setOnClickListener { finish() }
        //进度条变化的监听
        progress_sk.setOnSeekBarChangeListener(this)
        //播放模式点击事件
        mode.setOnClickListener(this)
        //上一曲下一曲点击事件
        pre.setOnClickListener(this)
        next.setOnClickListener(this)
        //播放列表
        playlist.setOnClickListener(this)
        //歌词拖动进度更新监听
        lyricView.setProgressListener {
            //更新播放进度
            iService?.seekTo(it)
            //更新进度显示
            updateProgress(it)
        }
    }
    override fun initData() {
        //注册eventBus
        EventBus.getDefault().register(this)
//        val list = intent.getParcelableArrayListExtra<AudioBean>("list")
//        val position = intent.getIntExtra("position",-1);
//        println("list = $list == position = $position")
        //播放音乐
        /*val mediaPlayer = MediaPlayer()
        mediaPlayer.setOnPreparedListener {
            //开始播放
            mediaPlayer.start()
        }
        mediaPlayer.setDataSource(list.get(position).data)
        mediaPlayer.prepareAsync()*/
        //通过audioservice播放音乐
        intent.setClass(this,AudioService::class.java)
        //通过intent传递数据
//        intent.putExtra("list",list);
//        intent.putExtra("position",position);
        //先绑定
        bindService(intent,connection, Context.BIND_AUTO_CREATE)
        //在开启
        startService(intent)

    }

    var iService:Iservice? = null
    inner class AudioConnection:ServiceConnection{
        /**
         * 意外端口连接的时候
         */
        override fun onServiceDisconnected(name: ComponentName?) {

        }

        /**
         * service连接的是
         */
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            iService = service as Iservice
        }

    }
    /**
     * 进度改变的回调
     * progress 改变之后的进度
     * fromUser 如果为true，通过用户手指拖动改变进度 false 通过代码放上改变进度
     */
    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        //判断是否否是用户操作
        if (!fromUser) return
        //更新播放进度
        iService?.seekTo(progress)
        //更新界面进度的显示
        updateProgress(progress)
    }

    /**
     * 手指触摸时的回调
     */
    override fun onStartTrackingTouch(seekBar: SeekBar?) {
    }

    /**
     * 手指离开时的回调
     */
    override fun onStopTrackingTouch(seekBar: SeekBar?) {
    }

    /**
     * 弹出的播放列表条目点击事件
     */
    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        //播放当前歌曲
        iService?.playPosition(position)
    }
    override fun onDestroy() {
        super.onDestroy()
        //解绑服务
        unbindService(connection)
        //反注册eventBus
        EventBus.getDefault().unregister(this)
        //情况handler发送的所有消息
        handler.removeCallbacksAndMessages(null)
    }
}