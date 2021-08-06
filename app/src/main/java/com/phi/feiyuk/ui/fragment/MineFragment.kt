package com.phi.feiyuk.ui.fragment

import android.annotation.SuppressLint
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
import com.phi.feiyuk.utils.*
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
        // smartRefresh.setPrimaryColors(ContextCompat.getColor(requireContext(), R.color.colorPrimary))
        initRecy()
        //监听appBarLayout滚动
        appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            val totalScrollRange = appBarLayout.totalScrollRange.toFloat()
            val rate = -1 * verticalOffset / totalScrollRange
            tv_title.alpha = rate
        })
        smartRefresh.setOnRefreshListener {
            it.finishRefresh(2000)
            mViewModel.getUserInfoData()
        }
        click()
    }

    private fun initRecy() {
        recy.run {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = mAdapter
        }
        val mDivider = ContextCompat.getDrawable(requireContext(), R.color.transparent)
        recy.addItemDecoration(
            VerticalDecoration(
                requireContext(),
                mDivider,
                DeviceUtils.dip2px(requireContext(), 0.5f)
            )
        )
        mAdapter.setOnItemClickListener { _, _, position ->
            LogUtils.error("${mAdapter.data[position].id} ${mAdapter.data[position].name}")
        }
    }

    private fun click() {
        iv_edit.click {
            RouteUtils.go2UserProfile(requireContext())
        }
    }

    override fun initData() {
        mViewModel.getUserInfoData()
    }


    //Map<Integer, List<PlaySelectBean>> map = new Gson().fromJson(strLastPlay, new TypeToken<HashMap<Integer, List<PlaySelectBean>>>(){}.getType());
    override fun startObserve() {
        mViewModel.run {
            userInfoData.observe(viewLifecycleOwner, { list ->
                //            val json = Gson().toJson(it.list)
//            val type = genType<List<List<MyEntity>>>()
//            val list = Gson().fromJson<List<List<MyEntity>>>(json, type)
                if (list != null && list.isNotEmpty()) {
                    val myItemList = mutableListOf<MyEntity>()
                    for (i in list.first().list.indices) {
                        //添加头部
                        myItemList.add(MyEntity(isHeader = true))
                        for (k in list.first().list[i].indices) {
                            myItemList.add(list.first().list[i][k])
                        }
                    }
                    mAdapter.setList(myItemList)
                    setUserInfo(list.first())
                }

            })


        }
    }

    private fun setUserInfo(userInfo: UserInfoEntity) {
        tv_title.text = userInfo.user_nicename
        tv_name.text = userInfo.user_nicename
        GlideUtils.loadImage(iv_avatar, userInfo.avatar)
        tv_id.text = "ID:${userInfo.id}"
        iv_sex.setImageResource(ComUtils.getSexIcon(userInfo.sex))
        val config =
            MMKV.defaultMMKV().decodeParcelable(Const.KEY_CONFIG, ConfigEntity::class.java)
        if (config != null) {
            val leveAnchor = ComUtils.getLevelAnchor(userInfo.level_anchor, config.levelanchor)
            val level = ComUtils.getLevel(userInfo.level, config.level)
            if (leveAnchor != null) {
                GlideUtils.loadImage(iv_levelanchor, leveAnchor.thumb)
            }
            if (level != null) {
                GlideUtils.loadImage(iv_level, level.thumb)
            }
        }
        tv_live.text = userInfo.lives.toString()
        tv_follow.text = userInfo.follows
        tv_fans.text = userInfo.fans
    }

}