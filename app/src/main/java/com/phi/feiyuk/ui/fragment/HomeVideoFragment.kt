package com.phi.feiyuk.ui.fragment

import androidx.recyclerview.widget.GridLayoutManager
import com.phi.basemodule.base.BaseVMFragment
import com.phi.feiyuk.R
import com.phi.feiyuk.ui.adapter.HomeVideoAdapter
import com.phi.feiyuk.utils.DeviceUtils
import com.phi.feiyuk.view.GridSpaceItemDecoration
import com.phi.feiyuk.viewmodel.HomeVideoViewModel
import kotlinx.android.synthetic.main.fragment_homevideo.*
import org.koin.androidx.viewmodel.ext.android.getViewModel


class HomeVideoFragment:BaseVMFragment<HomeVideoViewModel>() {

    private var pageIndex = 1
    private val videoAdapter by lazy { HomeVideoAdapter() }

    override fun initVM(): HomeVideoViewModel = getViewModel()

    override fun getLayoutResId(): Int = R.layout.fragment_homevideo

    override fun initView() {
        recy.run {
            adapter = videoAdapter
            layoutManager = GridLayoutManager(recy.context,2)
        }
        val gridSpaceItemDecoration = GridSpaceItemDecoration(
            DeviceUtils.dip2px(requireContext(),6f),
            DeviceUtils.dip2px(requireContext(),6f))
        recy.addItemDecoration(gridSpaceItemDecoration)

        //下拉刷新
        refreshLayout.setOnRefreshListener {
            it.finishRefresh(2000)
            //请求数据
            pageIndex = 1
            mViewModel.getHomeVideoData(pageIndex)
        }
        //加载更多
        refreshLayout.setOnLoadMoreListener {
            pageIndex++
            mViewModel.getHomeVideoData(pageIndex)
        }

    }

    override fun initData() {
        mViewModel.getHomeVideoData(pageIndex)
    }
    override fun startObserve() {

        mViewModel.videoData.observe(viewLifecycleOwner,{

            if (pageIndex == 1){
                videoAdapter.setList(it)
            }else{
                videoAdapter.addData(it)
            }
            if (it.size<20){
                refreshLayout.finishLoadMoreWithNoMoreData()
            }else{
                refreshLayout.finishLoadMore()
            }
        })

    }
}