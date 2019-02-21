package qzl.com.qgmusickotlin.net

import com.itheima.player.model.bean.HomeItemBean
import qzl.com.qgmusickotlin.util.URLProviderUtils

/**
 * @desc
 * @author Qzl
 * @email 2538096489@qq.com
 * @time 2019-02-21 14:58
 * @class QGMusicKotlin
 * @package qzl.com.qgmusickotlin.net
 */
class HomeRequest(type:Int,offset:Int,handler: ResponseHandler<List<HomeItemBean>>):MRequest<List<HomeItemBean>>(type,URLProviderUtils.getHomeUrl(offset,20),handler) {
}