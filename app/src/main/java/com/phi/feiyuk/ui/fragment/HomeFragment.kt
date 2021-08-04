package com.phi.feiyuk.ui.fragment

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.phi.basemodule.base.BaseFragment
import com.phi.feiyuk.R
import com.phi.feiyuk.utils.DeviceUtils
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment:BaseFragment() {

    private val titleList = listOf( "直播","视频","关注")
    private val fragmentList = listOf(HomeLiveFragment(),HomeVideoFragment(),HomeFollowFragment())

    override fun getLayoutResId(): Int = R.layout.fragment_home

    override fun initView() {
        ll_home.setPadding(0, DeviceUtils.getStatusBarHeight(requireContext()), 0, 0)
        initTabVp()
    }
    private fun initTabVp(){
        home_vp.adapter = object : FragmentStateAdapter(this) {

            override fun getItemCount(): Int = titleList.size
            override fun createFragment(position: Int): Fragment {
                return fragmentList[position]
            }
        }
        //tablayout 与 ViewPager2 绑定
        TabLayoutMediator(home_tab, home_vp) { tab, position ->
            tab.text = titleList[position]
        }.attach()
        //默认选中直播
        //home_vp.currentItem = 1
//        val pagerAdapter = TabPagerAdapter(childFragmentManager,titleList,fragmentList)
//        home_vp.adapter = pagerAdapter
//        home_tab.setupWithViewPager(home_vp)
//        home_vp.offscreenPageLimit = 2
//        for (i in 0..2){
//            home_tab.getTabAt(i)?.text =  titleList[i]
//        }

    }

    override fun initData() {

    }
}