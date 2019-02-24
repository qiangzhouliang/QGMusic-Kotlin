package qzl.com.qgmusickotlin.view

import com.itheima.player.model.bean.MvAreaBean

/**
 * @desc
 * @author Qzl
 * @email 2538096489@qq.com
 * @time 2019-02-23 11:31
 * @class QGMusicKotlin
 * @package qzl.com.qgmusickotlin.view
 */
interface MVView {
    fun onError(msg: String?)
    fun onSuccess(result: List<MvAreaBean>)
}