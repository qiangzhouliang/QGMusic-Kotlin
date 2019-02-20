package com.itheima.player.model.bean

/**
 * ClassName:HomeItemBean
 * Description:home界面每个条目的bean
 */
data class HomeItemBean(var type: String, var id: Int,
                        var title: String, var description: String?,
                        var posterPic: String?,var thumbnailPic: String?,
                        var url: String,var hdUrl: String?,var videoSize: Int,
                        var hdVideoSize: Int,var uhdVideoSize: Int,var status: Int,
                        var traceUrl: String?,var clickUrl: String?,var uhdUrl: String?)
