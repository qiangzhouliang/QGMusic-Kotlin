package qzl.com.qgmusickotlin.ui.fragment

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.itheima.player.model.bean.HomeItemBean
import kotlinx.android.synthetic.main.fragment_home.*
import okhttp3.*
import qzl.com.qgmusickotlin.R
import qzl.com.qgmusickotlin.adapter.HomeAdapter
import qzl.com.qgmusickotlin.base.BaseFragment
import qzl.com.qgmusickotlin.util.ThreadUtil
import qzl.com.qgmusickotlin.util.URLProviderUtils
import java.io.IOException

/**
 * ClassName:HomeFragment
 * Description:
 */
class HomeFragment : BaseFragment() {
    //适配
    val adapter by lazy { HomeAdapter()}
    override fun initView(): View? {
        return View.inflate(context, R.layout.fragment_home,null)
    }

    override fun initListener() {
        //初始化Recycleview
        recycleView.layoutManager = LinearLayoutManager(context)
        recycleView.adapter = adapter
    }

    override fun initData() {
        //初始化数据
        loadDatas()
    }

    private fun loadDatas() {
        val path = URLProviderUtils.getHomeUrl(0,20)
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
                myToast("获取数据失败")
                println("获取数据出错："+Thread.currentThread().name)

            }

            override fun onResponse(call: Call, response: Response) {
                val result = response.body()?.string()
                val gson = Gson()
                val list = gson.fromJson<List<HomeItemBean>>(result, object : TypeToken<List<HomeItemBean>>() {}.type)
                //刷新列表
                ThreadUtil.runOnMainThread(object :Runnable{
                    override fun run() {
                        adapter.updateList(list)
                    }

                })
            }
        })
    }
}