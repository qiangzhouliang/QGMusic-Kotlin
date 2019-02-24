package qzl.com.qgmusickotlin.model

import android.annotation.SuppressLint
import android.database.Cursor
import android.os.Parcel
import android.os.Parcelable
import android.provider.MediaStore

/**
 * @desc 音乐列表条目bean类
 * @author Qzl
 * @email 2538096489@qq.com
 * @time 2019-02-24 11:50
 * @class QGMusicKotlin
 * @package qzl.com.qgmusickotlin.model
 */
@SuppressLint("ParcelCreator")
data class AudioBean (var data:String, var size:Long, var display_name:String, var artist:String):Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readLong(),
        parcel.readString(),
        parcel.readString()
    ) {
    }
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(data)
        parcel.writeLong(size)
        parcel.writeString(display_name)
        parcel.writeString(artist)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AudioBean> {
        override fun createFromParcel(parcel: Parcel): AudioBean {
            return AudioBean(parcel)
        }

        override fun newArray(size: Int): Array<AudioBean?> {
            return arrayOfNulls(size)
        }
        /**
         * 根据特定位置获取bean类
         */
        fun getAudioBean(cursor:Cursor?):AudioBean{
            //创建audiobean对象
            val audioBean = AudioBean("",0,"","");
            cursor?.let {
                audioBean.data = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA))
                audioBean.size = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.SIZE))
                audioBean.display_name = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME))
                audioBean.display_name = audioBean.display_name.substring(0,audioBean.display_name.lastIndexOf("."))
                audioBean.artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST))
            }
            return audioBean;
        }

        /**
         * 根据特定位置获取获取整个播放列表
         */
        fun getAudioBeans(cursor: Cursor?): ArrayList<AudioBean> {
            //创建集合
            val list = ArrayList<AudioBean>()
            //cursor是否为空
            cursor?.let {
                //将游标移动到第一位
                it.moveToPosition(-1)
                //解析cursor 并添加到集合中
                while (it.moveToNext()){
                    val audioBean = getAudioBean(it)
                    list.add(audioBean)
                }
            }
            return list
        }
    }
}