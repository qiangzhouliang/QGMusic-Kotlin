package qzl.com.qgmusickotlin.util

import android.database.Cursor

/**
 * @desc cursor打印的Util类
 * @author Qzl
 * @email 2538096489@qq.com
 * @time 2019-02-24 09:56
 * @class QGMusicKotlin
 * @package qzl.com.qgmusickotlin.util
 */
object CursorUtil {
    fun logCursor(cursor: Cursor?){
        cursor?.let {
            //将cursor游标复位
            it.moveToPosition(-1)
            while (it.moveToNext()){
                for (index in 0 until it.columnCount){
                    println("key = ${it.getColumnName(index)} -- value = ${it.getString(index)}")
                }
            }
        }
    }
}