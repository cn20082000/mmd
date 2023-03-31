package com.cn.mmd.worker.permission.chain

import com.cn.mmd.model.entity.UserEntity

data class PermissionChainItem(
    var pattern: String,
    var exact: Boolean = true,
    val method: MutableList<String> = mutableListOf(),
    var validateUser: Boolean? = null,
    var block: ((UserEntity) -> Boolean)? = null,
)
