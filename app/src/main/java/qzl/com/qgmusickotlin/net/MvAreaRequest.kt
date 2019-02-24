package qzl.com.qgmusickotlin.net

import com.itheima.player.model.bean.MvAreaBean
import qzl.com.qgmusickotlin.util.URLProviderUtils

/**
 * @desc mv 区域数据请求类
 * @author Qzl
 * @email 2538096489@qq.com
 * @time 2019-02-23 11:37
 * @class QGMusicKotlin
 * @package qzl.com.qgmusickotlin.net
 */
class MvAreaRequest(handler: ResponseHandler<List<MvAreaBean>>) :
    MRequest<List<MvAreaBean>>(0, URLProviderUtils.getMVareaUrl(), handler) {
}