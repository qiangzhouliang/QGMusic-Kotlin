package qzl.com.qgmusickotlin.net

import com.itheima.player.model.bean.MvPagerBean
import qzl.com.qgmusickotlin.util.URLProviderUtils

/**
 * @desc
 * @author Qzl
 * @email 2538096489@qq.com
 * @time 2019-02-23 13:06
 * @class QGMusicKotlin
 * @package qzl.com.qgmusickotlin.net
 */
class MvListRequest(type: Int, code:String,offset:Int, handler: ResponseHandler<MvPagerBean>) :
    MRequest<MvPagerBean>(type, URLProviderUtils.getMVListUrl(code,offset,20), handler) {
}