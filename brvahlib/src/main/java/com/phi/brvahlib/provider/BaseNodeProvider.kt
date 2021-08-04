package com.phi.brvahlib.provider

import com.phi.brvahlib.BaseNodeAdapter
import com.phi.brvahlib.entity.node.BaseNode

abstract class BaseNodeProvider : BaseItemProvider<BaseNode>() {

    override fun getAdapter(): BaseNodeAdapter? {
        return super.getAdapter() as? BaseNodeAdapter
    }

}