package qzl.com.qgmusickotlin.presenter.impl

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.itheima.player.model.bean.HomeItemBean
import okhttp3.*
import qzl.com.qgmusickotlin.presenter.interf.HomePresenter
import qzl.com.qgmusickotlin.util.ThreadUtil
import qzl.com.qgmusickotlin.util.URLProviderUtils
import qzl.com.qgmusickotlin.view.HomeView
import java.io.IOException

/**
 * @desc
 * @author Qzl
 * @email 2538096489@qq.com
 * @time 2019-02-21 13:51
 * @class QGMusicKotlin
 * @package qzl.com.qgmusickotlin.presenter.impl
 */
class HomePresenterImpl(var homeView: HomeView) :HomePresenter{
    /**
     * 初始化数据或刷新数据
     */
    override fun loadDatas() {
        val path = URLProviderUtils.getHomeUrl(0,20)
        val client = OkHttpClient()
        val request = Request.Builder().url(path).get().build()
        client.newCall(request).enqueue(object : Callback {
            /**
             * 在子线程中调运的
             */
            override fun onFailure(call: Call, e: IOException) {
                ThreadUtil.runOnMainThread(object :Runnable{
                    override fun run() {
                        //回调到view层进行处理
                        homeView.onError(e.message)
                    }
                })
            }

            override fun onResponse(call: Call, response: Response) {
                val result = response.body()?.string()
                val gson = Gson()
                val list = gson.fromJson<List<HomeItemBean>>(result, object : TypeToken<List<HomeItemBean>>() {}.type)
                println("获取数据成功 "+list.size)
                //刷新列表
                ThreadUtil.runOnMainThread(object :Runnable{
                    override fun run() {
                        //将结果回调到view层
                        homeView.loadSuccess(list)
                    }
                })
            }
        })
    }

    override fun loadMore(offset: Int) {
        val path = URLProviderUtils.getHomeUrl(offset,20)
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(path)
            .get()
            .build()
        client.newCall(request).enqueue(object :Callback{
            /**
             * 在子线程中调运的
             */
            override fun onFailure(call: Call, e: IOException) {
                ThreadUtil.runOnMainThread(object :Runnable{
                    override fun run() {
                        //回调到view层进行处理
                        homeView.onError(e.message)
                    }
                })
            }

            override fun onResponse(call: Call, response: Response) {
                val result = response.body()?.string()
                val gson = Gson()
                val list = gson.fromJson<List<HomeItemBean>>(result, object : TypeToken<List<HomeItemBean>>() {}.type)
                //刷新列表
                ThreadUtil.runOnMainThread(object :Runnable{
                    override fun run() {
                        //将结果回调到view层
                        homeView.loadMoreSuccess(list)
                    }
                })
            }
        })
    }

}