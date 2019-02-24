package com.itheima.player.service

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import android.support.v4.app.NotificationCompat
import android.widget.RemoteViews
import de.greenrobot.event.EventBus
import qzl.com.qgmusickotlin.R
import qzl.com.qgmusickotlin.model.AudioBean
import qzl.com.qgmusickotlin.ui.activity.AudioPlayerActivity
import qzl.com.qgmusickotlin.ui.activity.MainActivity
import java.util.*


/**
 * ClassName:AudioService
 * Description:
 */
class AudioService : Service() {
    var mediaPlayer: MediaPlayer? = null
    var list: ArrayList<AudioBean>? = null
    var manager:NotificationManager? = null
    var notification:Notification? = null
    var position: Int = -2  // 正在播放的position

    val FROM_PRE= 1
    val FROM_NEXT= 2
    val FROM_STATE= 3
    val FROM_CONTENT= 4
    val binder by lazy { AudioBinder() }

    companion object {
        val MODE_ALL = 1
        val MODE_SINGLE = 2
        val MODE_RANDOM = 3
    }

    val sp by lazy { getSharedPreferences("config", Context.MODE_PRIVATE) }
    var mode = MODE_ALL  //播放模式
    override fun onCreate() {
        super.onCreate()
        //获取播放模式
        mode = sp.getInt("mode", 1)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        //判断进入service方法
        val from = intent?.getIntExtra("from",-1)
        when(from){
            FROM_PRE->{binder.playPre()}
            FROM_NEXT->{binder.playNext()}
            FROM_CONTENT->{binder.notifyUpdateUi()}
            FROM_STATE->{binder.updatePlayState()}
            else->{
                val pos = intent?.getIntExtra("position", -1) ?: -1 // 想要播放的position
                if(pos!=position){//想要播放的条目和正在播放条目不是同一首
                    position = pos
                    println("intent=$intent")
                    //获取集合以及position
                    list = intent?.getParcelableArrayListExtra<AudioBean>("list")
                    //开始播放音乐
                    binder.playItem()
                }else{
                    //主动通知界面更新
                    binder.notifyUpdateUi()
                }
            }
        }


        //START_STICKY   粘性的  service强制杀死之后 会尝试重新启动service 不会传递原来的intent(null)
        //START_NOT_STICKY 非粘性的 service强制杀死之后 不会尝试重新启动service
        //START_REDELIVER_INTENT service强制杀死之后 会尝试重新启动service  会传递原来的intent
        return START_NOT_STICKY
    }

    override fun onBind(p0: Intent?): IBinder {
        return binder
    }
    inner class AudioBinder : Binder(), Iservice, MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener {
        /**
         * 播放当前位置的歌曲
         */
        override fun playPosition(p2: Int) {
            this@AudioService.position = p2
            playItem()
        }

        /**
         * 获取播放集合
         */
        override fun getPlayList(): List<AudioBean>? {
            return list
        }

        /**
         * 播放上一曲
         */
        override fun playPre() {
            list?.let {
                //获取要播放歌曲position
                when(mode){
                    MODE_RANDOM->list?.let { position = Random().nextInt(it.size-1) }
                    else->{
                        if(position==0){
                            position = it.size-1
                        }else{
                            position--
                        }
                    }
                }
                //playitem
                playItem()
            }
        }

        /**
         * 下一曲
         */
        override fun playNext() {
            list?.let {
                when(mode){
                    MODE_RANDOM->position = Random().nextInt(it.size-1)
                    else->position = (position+1)%it.size
                }
            }
            playItem()
        }

        /**
         * 获取播放模式
         */
        override fun getPlayMode(): Int {
            return mode
        }

        /**
         * 修改播放模式
         * MODE_ALL_MODE_SINGLE_MODE_RANDOM
         */
        override fun updatePlayMode() {
            when (mode) {
                MODE_ALL -> mode = MODE_SINGLE
                MODE_SINGLE -> mode = MODE_RANDOM
                MODE_RANDOM -> mode = MODE_ALL
            }
            //保存播放模式
            sp.edit().putInt("mode", mode).commit()
        }

        /**
         * 歌曲播放完成之后回调
         */
        override fun onCompletion(p0: MediaPlayer?) {
            //自动播放下一曲
            autoPlayNext()
        }

        /**
         * 根据播放模式自动播放下一曲
         */
        private fun autoPlayNext() {
            when (mode) {
                MODE_ALL -> {
//                    if(position==list.size-1){
//                        position=0
//                    }else{
//                        position++
//                    }
                    list?.let { position = (position + 1) % it.size }
                }
//                MODE_SINGLE->
                MODE_RANDOM -> list?.let { position = Random().nextInt(it.size) }
            }

            playItem()
        }

        /**
         * 跳转到当前进度播放
         */
        override fun seekTo(p1: Int) {
            mediaPlayer?.seekTo(p1)
        }

        /**
         * 获取当前播放进度
         */
        override fun getProgress(): Int {
            return mediaPlayer?.currentPosition ?: 0
        }

        /**
         * 获取总进度
         */
        override fun getDuration(): Int {
            return mediaPlayer?.duration ?: 0
        }

        /**
         * 更新播放状态
         */
        override fun updatePlayState() {
            //获取当前播放状态
            val isPlaying = isPlaying()
            //切换播放状态
            isPlaying?.let {
                if (isPlaying) {
                    //播放 暂停
                    pause()
                } else {
                    //暂停 播放
                    start()
                }
            }
        }

        /**
         * 暂停
         */
        private fun pause(){
            mediaPlayer?.pause()
            EventBus.getDefault().post(list?.get(position))
            //更新图标
            notification?.contentView?.setImageViewResource(R.id.state,R.mipmap.btn_audio_pause_normal)
            //重新显示
            manager?.notify(1,notification)
        }

        /**
         * 开始
         */
        private fun start(){
            mediaPlayer?.start()
            EventBus.getDefault().post(list?.get(position))
            //更新图标
            notification?.contentView?.setImageViewResource(R.id.state,R.mipmap.btn_audio_play_normal)
            //重新显示
            manager?.notify(1,notification)
        }
        override fun isPlaying(): Boolean? {
            return mediaPlayer?.isPlaying
        }

        override fun onPrepared(p0: MediaPlayer?) {
            //播放音乐
            start()
            //通知界面更新
            notifyUpdateUi()
            //显示通知
            showNotification()
        }



        /**
         * 显示通知
         */
        private fun showNotification() {
            manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
             notification = getNotification()
            manager?.notify(1,notification)
        }
        /**
         * 创建notification
         * Notification 3.0
         * Notification.Builder 3.0
         *
         */
        private fun getNotification(): Notification? {
            val bitmap = BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher)
            val notification = NotificationCompat.Builder(this@AudioService)
                    .setTicker("正在播放歌曲${list?.get(position)?.display_name}")
                    .setSmallIcon(R.mipmap.ic_launcher)
//                .setLargeIcon(bitmap)
//                .setContentTitle("北京")//通知标题
//                .setContentText("汪峰")//通知内容
                    //自定义通知view
                    .setCustomContentView(getRemoteViews())
                    .setWhen(System.currentTimeMillis())
                    .setOngoing(true)//设置不能滑动删除通知
                    .setContentIntent(getPendingIntent())//通知栏主体点击事件
                    .build()
            return notification
        }
        /**
         * 创建通知自定义view
         */
        private fun getRemoteViews(): RemoteViews? {
            val remoteViews = RemoteViews(packageName,R.layout.notification)
            //修改标题和内容
            remoteViews.setTextViewText(R.id.title,list?.get(position)?.display_name)
            remoteViews.setTextViewText(R.id.artist,list?.get(position)?.artist)
            //处理上一曲 下一曲  状态点击事件
            remoteViews.setOnClickPendingIntent(R.id.pre,getPrePendingIntent())
            remoteViews.setOnClickPendingIntent(R.id.state,getStatePendingIntent())
            remoteViews.setOnClickPendingIntent(R.id.next,getNextPendingIntent())
            return remoteViews
        }

        /**
         * 下一曲点击事件
         */
        private fun getNextPendingIntent(): PendingIntent? {
            val intent = Intent(this@AudioService,AudioService::class.java)//点击主体进入当前界面中
            intent.putExtra("from",FROM_NEXT)
            val pendingIntent = PendingIntent.getService(this@AudioService,2,intent, PendingIntent.FLAG_UPDATE_CURRENT)
            return pendingIntent
        }

        /**
         * 播放暂停按钮点击事件
         */
        private fun getStatePendingIntent(): PendingIntent? {
            val intent = Intent(this@AudioService,AudioService::class.java)//点击主体进入当前界面中
            intent.putExtra("from",FROM_STATE)
            val pendingIntent = PendingIntent.getService(this@AudioService,3,intent, PendingIntent.FLAG_UPDATE_CURRENT)
            return pendingIntent
        }

        /**
         * 上一曲点击事件
         */
        private fun getPrePendingIntent(): PendingIntent? {
            val intent = Intent(this@AudioService, AudioService::class.java)//点击主体进入当前界面中
            intent.putExtra("from",FROM_PRE)
            val pendingIntent = PendingIntent.getService(this@AudioService,4,intent, PendingIntent.FLAG_UPDATE_CURRENT)
            return pendingIntent
        }

        /**
         * 通知栏主体点击事件
         */
        private fun getPendingIntent(): PendingIntent? {
            val intentM = Intent(this@AudioService, MainActivity::class.java)//点击主体进入当前界面中
            val intentA = Intent(this@AudioService, AudioPlayerActivity::class.java)//点击主体进入当前界面中
            intentA.putExtra("from",FROM_CONTENT)
            val intents = arrayOf(intentM,intentA)
            val pendingIntent = PendingIntent.getActivities(this@AudioService,1,intents, PendingIntent.FLAG_UPDATE_CURRENT)
            return pendingIntent
        }




        /**
         * 通知界面更新
         */
        fun notifyUpdateUi() {
            //发送端
            EventBus.getDefault().post(list?.get(position))
        }

        fun playItem() {
            //如果mediaplayer已经存在就先释放掉
            if(mediaPlayer!=null){
                mediaPlayer?.reset()
                mediaPlayer?.release()
                mediaPlayer = null
            }
            mediaPlayer = MediaPlayer()
            mediaPlayer?.let {
                it.setOnPreparedListener(this)
                it.setOnCompletionListener(this)
                it.setDataSource(list?.get(position)?.data)
                it.prepareAsync()
            }
        }
    }
}