package com.cn.mmd.worker.permission.chain

import com.cn.mmd.model.entity.UserEntity
import com.cn.mmd.util.ApiException
import com.cn.mmd.util.enumi.ErrorType
import com.cn.mmd.util.granted
import com.cn.mmd.util.isNotNull
import com.cn.mmd.util.isNull
import com.cn.mmd.worker.service.AuthService
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus

class PermissionChain(
    private val authService: AuthService,
    private val chain: MutableList<PermissionChainItem> = mutableListOf(),
) {

    fun pattern(pattern: String): PermissionChain {
        chain.add(PermissionChainItem(pattern, false))
        return this
    }

    fun exact(pattern: String): PermissionChain {
        chain.add(PermissionChainItem(pattern, true))
        return this
    }

    fun methods(list: List<String>): PermissionChain {
        chain.last().method.addAll(list)
        return this
    }

    fun get(): PermissionChain {
        chain.last().method.add("GET")
        return this
    }

    fun post(): PermissionChain {
        chain.last().method.add("POST")
        return this
    }

    fun put(): PermissionChain {
        chain.last().method.add("PUT")
        return this
    }

    fun delete(): PermissionChain {
        chain.last().method.add("DELETE")
        return this
    }

    fun permitAll(): PermissionChain {
        chain.last().validateUser = false
        return this
    }

    fun user(): PermissionChain {
        chain.last().validateUser = true
        return this
    }

    fun hasPermission(block: (UserEntity) -> Boolean): PermissionChain {
        chain.last().block = block
        return this
    }

    fun execute(request: HttpServletRequest) {
        for (item in chain) {
            if (((item.exact && request.servletPath == item.pattern)
                || (!item.exact && request.servletPath.startsWith(item.pattern)))
                && (item.method.isEmpty() || item.method.contains(request.method))) {
                if (item.validateUser == true) {
                    val user = authService.auth(request)
                    if (item.block.isNotNull()) {
                        item.block?.let { it1 -> it1(user).granted() }
                    }
                }
                return
            }
        }
    }
}