package qzl.com.qgmusickotlin.base

/**
 * @desc 所有下拉刷新和上拉加载更多列表界面的view
 * @author Qzl
 * @email 2538096489@qq.com
 * @time 2019-02-23 10:15
 * @class QGMusicKotlin
 * @package qzl.com.qgmusickotlin.base
 */
interface BaseView<RESPONSE> {
    /**
     * 获取数据失败
     */
    fun onError(message: String?)

    /**
     * 初始化数据或者刷新数据成功
     */
    fun loadSuccess(list: RESPONSE?)

    /**
     * 加载更多成功
     */
    fun loadMoreSuccess(list: RESPONSE?)
}