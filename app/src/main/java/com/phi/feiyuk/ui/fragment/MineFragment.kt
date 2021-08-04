package com.phi.feiyuk.ui.fragment

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.appbar.AppBarLayout
import com.phi.basemodule.base.BaseVMFragment
import com.phi.basemodule.utils.LogUtils
import com.phi.brvahlib.listener.OnItemClickListener
import com.phi.feiyuk.R
import com.phi.feiyuk.config.Const
import com.phi.feiyuk.model.entity.ConfigEntity
import com.phi.feiyuk.model.entity.MyEntity
import com.phi.feiyuk.model.entity.UserInfoEntity
import com.phi.feiyuk.ui.adapter.MyItemAdapter
import com.phi.feiyuk.utils.ComUtils
import com.phi.feiyuk.utils.DeviceUtils
import com.phi.feiyuk.utils.GlideUtils
import com.phi.feiyuk.view.VerticalDecoration
import com.phi.feiyuk.viewmodel.MineViewModel
import com.tencent.mmkv.MMKV
import kotlinx.android.synthetic.main.fragment_mine.*
import org.koin.androidx.viewmodel.ext.android.getViewModel


class MineFragment : BaseVMFragment<MineViewModel>() {


    val mAdapter by lazy { MyItemAdapter() }

    override fun getLayoutResId(): Int = R.layout.fragment_mine

    override fun initVM(): MineViewModel = getViewModel()

    override fun initView() {
     //   top.setPadding(0, DeviceUtils.getStatusBarHeight(requireContext()), 0, 0)
       initRecy()
        //监听appBarLayout滚动
        appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            val totalScrollRange = appBarLayout.totalScrollRange.toFloat()
            val rate = -1 * verticalOffset / totalScrollRange
            tv_title.alpha = rate
        })
    }

    private fun initRecy(){
        recy.run {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = mAdapter
        }
        val mDivider = ContextCompat.getDrawable(requireContext(), R.color.transparent)
        recy.addItemDecoration(VerticalDecoration(requireContext(), mDivider, DeviceUtils.dip2px(requireContext(), 0.5f)))
        mAdapter.setOnItemClickListener { _, _, position ->
            LogUtils.error(mAdapter.data[position].href)
        }
    }

    override fun initData() {
        mViewModel.getUserInfoData()
    }


    //Map<Integer, List<PlaySelectBean>> map = new Gson().fromJson(strLastPlay, new TypeToken<HashMap<Integer, List<PlaySelectBean>>>(){}.getType());
    override fun startObserve() {
        mViewModel.userInfoLiveData.observe(viewLifecycleOwner, {
//            val json = Gson().toJson(it.list)
//            val type = genType<List<List<MyEntity>>>()
//            val list = Gson().fromJson<List<List<MyEntity>>>(json, type)
            val myItemList = mutableListOf<MyEntity>()
            for (i in it.list.indices) {
                //添加头部
                myItemList.add(MyEntity(isHeader = true))
                for (k in it.list[i].indices) {
                    myItemList.add(it.list[i][k])
                }
            }
            mAdapter.setList(myItemList)
            setUserInfo(it)
        })
    }

    private fun setUserInfo(userInfo:UserInfoEntity){
        tv_title.text = userInfo.user_nicename
        tv_name.text = userInfo.user_nicename
        GlideUtils.loadImage(iv_avatar,userInfo.avatar)
        tv_id.text = "ID:${userInfo.id}"
        iv_sex.setImageResource(ComUtils.getSexIcon(userInfo.sex))
        val config = MMKV.defaultMMKV().decodeParcelable(Const.KEY_CONFIG,ConfigEntity::class.java)
        if (config !=null){
            val leveAnchor = ComUtils.getLevelAnchor(userInfo.level_anchor,config.levelanchor)
            val level = ComUtils.getLevel(userInfo.level,config.level)
            if (leveAnchor != null) {
                GlideUtils.loadImage(iv_levelanchor,leveAnchor.thumb)
            }
            if (level != null) {
                GlideUtils.loadImage(iv_level,level.thumb)
            }
        }
        tv_live.text = userInfo.lives.toString()
        tv_follow.text = userInfo.follows
        tv_fans.text = userInfo.fans
    }

}