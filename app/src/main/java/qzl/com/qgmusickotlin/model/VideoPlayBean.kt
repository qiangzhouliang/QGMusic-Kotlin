package qzl.com.qgmusickotlin.model

import android.os.Parcel
import android.os.Parcelable
import org.xml.sax.Parser
import java.io.Serializable

/**
 * @desc 传递给视频播放的bean类
 * @author Qzl
 * @email 2538096489@qq.com
 * @time 2019-02-23 14:15
 * @class QGMusicKotlin
 * @package qzl.com.qgmusickotlin.model
 */
data class VideoPlayBean(val id:Int,val title:String,val url:String) :Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<VideoPlayBean> {
        override fun createFromParcel(parcel: Parcel): VideoPlayBean {
            return VideoPlayBean(parcel)
        }

        override fun newArray(size: Int): Array<VideoPlayBean?> {
            return arrayOfNulls(size)
        }
    }
}