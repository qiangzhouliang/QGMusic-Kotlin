package qzl.com.qgmusickotlin.ui.fragment
import android.os.Bundle
import android.preference.Preference
import android.preference.PreferenceFragment
import android.preference.PreferenceScreen
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.jetbrains.anko.startActivity
import qzl.com.qgmusickotlin.R
import qzl.com.qgmusickotlin.ui.activity.AboutActivity


/**
 * ClassName:SettingFragment
 * Description:
 */
class SettingFragment: PreferenceFragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        //设置布局文件
        addPreferencesFromResource(R.xml.setting)
        return super.onCreateView(inflater, container, savedInstanceState)
    }
    //对界面中点击条目的处理
    override fun onPreferenceTreeClick(preferenceScreen: PreferenceScreen?, preference: Preference?): Boolean {
        val key = preference?.key
        if("about".equals(key)){
            //点击了关于
            activity.startActivity<AboutActivity>()
        }
        return super.onPreferenceTreeClick(preferenceScreen, preference)
    }
}