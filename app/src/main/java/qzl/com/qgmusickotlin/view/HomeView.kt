package qzl.com.qgmusickotlin.view

import com.itheima.player.model.bean.HomeItemBean

/**
 * @desc home界面和presenter层的交互
 * @author Qzl
 * @email 2538096489@qq.com
 * @time 2019-02-21 13:50
 * @class QGMusicKotlin
 * @package qzl.com.qgmusickotlin.view
 */
interface HomeView {
    /**
     * 获取数据失败
     */
    fun onError(message: String?)

    /**
     * 初始化数据或者刷新数据成功
     */
    fun loadSuccess(list: List<HomeItemBean>?)

    /**
     * 加载更多成功
     */
    fun loadMoreSuccess(list: List<HomeItemBean>?)

}