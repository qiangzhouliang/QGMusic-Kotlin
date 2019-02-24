package qzl.com.qgmusickotlin.ui.fragment

import android.Manifest
import android.content.AsyncQueryHandler
import android.content.ContentResolver
import android.database.Cursor
import android.os.AsyncTask
import android.os.Handler
import android.os.Message
import android.provider.MediaStore
import android.view.View
import kotlinx.android.synthetic.main.fragment_vbang.*
import kr.co.namee.permissiongen.PermissionGen
import kr.co.namee.permissiongen.PermissionSuccess
import org.jetbrains.anko.support.v4.startActivity
import qzl.com.qgmusickotlin.R
import qzl.com.qgmusickotlin.adapter.VBangAdapter
import qzl.com.qgmusickotlin.base.BaseFragment
import qzl.com.qgmusickotlin.model.AudioBean
import qzl.com.qgmusickotlin.ui.activity.AudioPlayerActivity
import qzl.com.qgmusickotlin.util.CursorUtil


/**
 * ClassName:HomeFragment
 * Description:
 */
class VBangFragment : BaseFragment() {
    val handler = object : Handler(){
        override fun handleMessage(msg: Message?) {
            msg?.let {
                val cursor = it.obj as Cursor
                CursorUtil.logCursor(cursor)
            }
        }
    }
    override fun initView(): View? {
        return View.inflate(context, R.layout.fragment_vbang,null)
    }

    override fun initData() {
        PermissionGen.needPermission(this@VBangFragment, 100,
            arrayOf<String>(Manifest.permission.READ_EXTERNAL_STORAGE))
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>,grantResults: IntArray) {
        PermissionGen.onRequestPermissionsResult(this, requestCode, permissions, grantResults)
    }

    @PermissionSuccess(requestCode = 100)
    fun doSomething() {
        loadMusic()
    }
    private fun loadMusic() {
        val resolver = context.contentResolver
        //加载音乐数据
        /*
        val cursor = resolver.query(
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
            arrayOf(
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.SIZE,
                MediaStore.Audio.Media.DISPLAY_NAME,
                MediaStore.Audio.Media.ARTIST),null,null,null)
        //打印所有的数据
        CursorUtil.logCursor(cursor)*/
        //第一种方法
        //起开线程查询音乐数据
        /*Thread(object :Runnable{
            override fun run() {
                val cursor = resolver.query(
                    MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                    arrayOf(
                        MediaStore.Audio.Media.DATA,
                        MediaStore.Audio.Media.SIZE,
                        MediaStore.Audio.Media.DISPLAY_NAME,
                        MediaStore.Audio.Media.ARTIST),null,null,null)
                val message = Message.obtain()
                message.obj = cursor
                handler.sendMessage(message)
            }
        }).start()*/
        //第二种方法
//        AudioTask().execute(resolver)
        //第三种方法
        val handler = object :AsyncQueryHandler(resolver){
            /**
             * 查询完成的回调 - 主线程中
             */
            override fun onQueryComplete(token: Int, cookie: Any?, cursor: Cursor?) {
                //打印
//                CursorUtil.logCursor(cursor)
                //刷新列表
                //设置数据源
                //刷新列表
                (cookie as VBangAdapter).swapCursor(cursor)
            }
        }
        //开始查询
        handler.startQuery(0,adapter,MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
            arrayOf(
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.SIZE,
                MediaStore.Audio.Media.DISPLAY_NAME,
                MediaStore.Audio.Media.ARTIST),null,null,null)
    }

    /**
     * 音乐查询的任务
     */
    //asynctask
    class AudioTask:AsyncTask<ContentResolver,Void,Cursor?>(){
        /**
         * 后台执行的任务 新线程
         */
        override fun doInBackground(vararg params: ContentResolver?): Cursor? {
            val cursor = params[0]?.query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                arrayOf(
                    MediaStore.Audio.Media.DATA,
                    MediaStore.Audio.Media.SIZE,
                    MediaStore.Audio.Media.DISPLAY_NAME,
                    MediaStore.Audio.Media.ARTIST),null,null,null)
            return cursor
        }

        /**
         * 将后台任务回调到主线程中
         */
        override fun onPostExecute(result: Cursor?) {
            super.onPostExecute(result)
            //打印
            CursorUtil.logCursor(result)
        }
    }
    var adapter: VBangAdapter? = null
    override fun initListener() {
        adapter = VBangAdapter(context,null)
        listView.adapter = adapter
        //设置条目点击事件
        listView.setOnItemClickListener { parent, view, position, id ->
            //获取数据集合
            val cursor = adapter?.getItem(position) as Cursor
            //通过当前位置cursor获取整个播放列表
            val list:ArrayList<AudioBean> = AudioBean.getAudioBeans(cursor)
            //传递位置position
            //跳转到音乐播放界面
            startActivity<AudioPlayerActivity>("list" to list,"position" to position)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        //关闭cursor
        //获取adapter中的cursor
        //关闭
        adapter?.changeCursor(null)
    }
}