package qzl.com.qgmusickotlin.base

/**
 * @desc 所有下拉刷新和上拉加载更多列表页面的基类
 * @author Qzl
 * @email 2538096489@qq.com
 * @time 2019-02-23 10:20
 * @class QGMusicKotlin
 * @package qzl.com.qgmusickotlin.base
 */
interface BasListPresenter {
    /**
     * 在伴生对象中定义常量
     */
    companion object {
        /**
         * 初始化类型
         */
        val TYPE_INIT_OR_REFRESH = 1
        /**
         * 加载更多类型
         */
        val TYPE_LOAD_MORE = 2
    }
    /**
     * 加载数据
     */
    fun loadDatas()

    /**
     * 加载更多数据
     */
    fun loadMore(offset: Int)

    /**
     * 解绑presenter和view层
     */
    fun destroyView()
}