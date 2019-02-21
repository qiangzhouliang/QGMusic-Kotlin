package qzl.com.qgmusickotlin.presenter.interf

/**
 * @desc
 * @author Qzl
 * @email 2538096489@qq.com
 * @time 2019-02-21 13:51
 * @class QGMusicKotlin
 * @package qzl.com.qgmusickotlin.presenter.interf
 */
interface HomePresenter {
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
}