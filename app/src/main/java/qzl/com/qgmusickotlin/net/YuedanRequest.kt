package qzl.com.qgmusickotlin.net

import com.itheima.player.model.bean.YueDanBean
import qzl.com.qgmusickotlin.util.URLProviderUtils

/**
 * @desc 悦单界面网络请求request
 * @author Qzl
 * @email 2538096489@qq.com
 * @time 2019-02-21 14:58
 * @class QGMusicKotlin
 * @package qzl.com.qgmusickotlin.net
 */
class YuedanRequest(type:Int, offset:Int, handler: ResponseHandler<YueDanBean>):MRequest<YueDanBean>(type,URLProviderUtils.getYueDanUrl(offset,20),handler) {
}