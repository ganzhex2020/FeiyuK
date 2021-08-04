package com.phi.feiyuk.ui.fragment

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.phi.basemodule.base.BaseVMFragment
import com.phi.brvahlib.BaseQuickAdapter
import com.phi.brvahlib.listener.OnItemClickListener
import com.phi.feiyuk.R
import com.phi.feiyuk.config.Const
import com.phi.feiyuk.model.entity.BannerEntity
import com.phi.feiyuk.model.entity.ConfigEntity
import com.phi.feiyuk.model.entity.LiveclasEntity
import com.phi.feiyuk.ui.adapter.HomeLiveAdapter
import com.phi.feiyuk.ui.adapter.HomeLiveBannerAdapter
import com.phi.feiyuk.ui.adapter.HomeLiveTopAdapter
import com.phi.feiyuk.utils.DeviceUtils
import com.phi.feiyuk.utils.gone
import com.phi.feiyuk.utils.visible
import com.phi.feiyuk.view.GridSpaceItemDecoration
import com.phi.feiyuk.viewmodel.HomeLiveViewModel
import com.tencent.mmkv.MMKV
import kotlinx.android.synthetic.main.fragment_homelive.*
import org.koin.androidx.viewmodel.ext.android.getViewModel


class HomeLiveFragment:BaseVMFragment<HomeLiveViewModel>(){

    private val kv = MMKV.defaultMMKV()
    private var pageIndex:Int = 1
    //前段自定义全部id 0
    private var classfyId = "0" //后台没有全部的分类id 全部查询Home.getHot 其他分类查询Home.getClassLive

    private val bannerList by lazy { mutableListOf<BannerEntity>() }
    private val bannerAdapter by lazy { HomeLiveBannerAdapter(bannerList) }
    private val liveTopAdapter by lazy { HomeLiveTopAdapter() }
    private val liveTopList by lazy { mutableListOf<LiveclasEntity>() }
    private val liveAdapter by lazy { HomeLiveAdapter() }

    override fun initVM(): HomeLiveViewModel = getViewModel()

    override fun getLayoutResId(): Int = R.layout.fragment_homelive

    override fun initView() {
        val configEntity = kv.decodeParcelable(Const.KEY_CONFIG,ConfigEntity::class.java)

        banner.run {
            addBannerLifecycleObserver(viewLifecycleOwner)
            //    indicator = CircleIndicator(context)
           // setBannerRound2(20f)
            adapter = bannerAdapter
            //setAdapter(bannerAdapter as Nothing?)
        }

        top_recy.run {
            adapter = liveTopAdapter
            layoutManager = LinearLayoutManager(top_recy.context,LinearLayoutManager.HORIZONTAL,false)
        }
        liveTopList.clear()
        liveTopList.add(LiveclasEntity("","0","全部","","",true))
        if (configEntity?.liveclass!=null){
            liveTopList.addAll(configEntity.liveclass)
            liveTopAdapter.setList(liveTopList)
        }
        //点击切换分类
        liveTopAdapter.setOnItemClickListener{ _, _, position ->
            val index = liveTopList.indexOfFirst { it.select }
            if (index == position){
                return@setOnItemClickListener
            }
            liveTopList.forEach { it.select = false }
            liveTopList[position].select = true
            liveTopAdapter.notifyDataSetChanged()
            //请求数据
            pageIndex = 1
            getData()
        }

        live_recy.run {
            adapter = liveAdapter
            layoutManager = GridLayoutManager(live_recy.context,2)
        }
        val gridSpaceItemDecoration = GridSpaceItemDecoration(DeviceUtils.dip2px(requireContext(),6f),DeviceUtils.dip2px(requireContext(),6f))
        live_recy.addItemDecoration(gridSpaceItemDecoration)
        /*liveAdapter.loadMoreModule.run {
            preLoadNumber = 2
            setOnLoadMoreListener {
                pageIndex++
                mViewModel.getHomeLiveData(pageIndex)
            }
        }*/
        liveAdapter.setOnItemClickListener { _, _, position ->

        }
        //下拉刷新
        refreshLayout.setOnRefreshListener {
            it.finishRefresh(2000)
            //请求数据
            pageIndex = 1
            getData()
        }
        //加载更多
        refreshLayout.setOnLoadMoreListener {
         //   it.finishLoadMore(2000)
            pageIndex++
            getData()
        }

    }

    override fun initData() {
       // mViewModel.getHomeLiveData(pageIndex)
        getData()
    }
    private fun getData(){
        val index = liveTopList.indexOfFirst {item-> item.select }
        if (index == 0){
            mViewModel.getHomeLiveData(pageIndex)
        }else{
            mViewModel.getClassLiveData(pageIndex,liveTopList[index].id)
        }
    }

    override fun startObserve() {
        /*viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            mViewModel.homeData.collect {
                bannerList.addAll(it.slide)
                bannerAdapter.notifyDataSetChanged()
            }
        }*/
        mViewModel.run {
            bannerData.observe(viewLifecycleOwner,{
                bannerList.clear()
                bannerList.addAll(it)
                bannerAdapter.notifyDataSetChanged()
            })
            liveData.observe(viewLifecycleOwner,{
                if (pageIndex == 1){
                    liveAdapter.setList(it)
                    /*if (it.isEmpty()){
                        liveAdapter.setEmptyView(R.layout.layout_empty_live)
                    }*/
                    if (it.isEmpty()){
                        empty_placeholder.visible()
                    }else{
                        empty_placeholder.gone()
                    }
                }else{
                    liveAdapter.addData(it)
                }
               /* if (it.size<20){
                    liveAdapter.loadMoreModule.loadMoreEnd()
                }else{
                    liveAdapter.loadMoreModule.loadMoreComplete()
                }*/
                if (it.size<20){
                    refreshLayout.finishLoadMoreWithNoMoreData()
                }else{
                    refreshLayout.finishLoadMore()
                }
            })

        }
    }
}