package qzl.com.qgmusickotlin.net

/**
 * @desc 请求回调
 * @author Qzl
 * @email 2538096489@qq.com
 * @time 2019-02-21 14:32
 * @class QGMusicKotlin
 * @package qzl.com.qgmusickotlin.net
 */
interface ResponseHandler<RESPONSE> {
    fun onError(type:Int,msg:String?)
    fun OnSuccess(type:Int,result:RESPONSE)
}