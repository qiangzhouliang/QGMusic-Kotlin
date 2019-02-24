package com.itheima.player.service

import qzl.com.qgmusickotlin.model.AudioBean


/**
 * ClassName:Iservice
 * Description:
 */
interface Iservice {
    fun updatePlayState()
    fun isPlaying():Boolean?
    fun getDuration(): Int
    fun getProgress(): Int
    fun seekTo(p1: Int)
    fun updatePlayMode()
    fun  getPlayMode(): Int
    fun playPre()
    fun playNext()
    fun getPlayList(): List<AudioBean>?
    fun playPosition(p2: Int)
}