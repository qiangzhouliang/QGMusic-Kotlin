package com.itheima.player.util

import android.os.Environment
import java.io.File


/**
 * ClassName:LyricLoader
 * Description:歌词文件加载util
 */
object LyricLoader {
    //歌词文件夹
    //val dir = File(Environment.getExternalStorageDirectory(),"Download/Lyric")
    val dir = File(Environment.getExternalStorageDirectory(),"kugou/Lyrics")
    /**
     * 根据歌曲名称加载歌词文件
     */
    fun loadLyricFile(display_name:String):File{
        return File(dir,display_name+".lrc")
    }
}