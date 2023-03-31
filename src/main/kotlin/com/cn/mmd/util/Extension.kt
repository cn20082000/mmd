package com.cn.mmd.util

import com.cn.mmd.common.Constant
import com.cn.mmd.util.enumi.ErrorType
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

/**
 * Check null cho 1 biến bất kì
 *
 * Created at 5/7/2022 by NCChinh
 */
@OptIn(ExperimentalContracts::class)
fun <T> T.isNull(): Boolean {
    contract {
        returns(false) implies (this@isNull != null)
    }
    if (this != null) return false
    return true
}

/**
 * Check không null cho 1 biến bất kì
 *
 * Created at 5/7/2022 by NCChinh
 */
@OptIn(ExperimentalContracts::class)
fun <T> T.isNotNull(): Boolean {
    contract {
        returns(true) implies (this@isNotNull != null)
    }
    if (this != null) return true
    return false
}

/**
 * Lấy địa chỉ ip từ request
 *
 * Created at 5/7/2022 by NCChinh
 */
fun HttpServletRequest.ipAddress(): String {
    var address = this.getHeader("X-FORWARDED-FOR") ?: ""
    if (address.isBlank()) {
        address = this.remoteAddr
    }

    return address
}

/**
 * Lấy token từ header ra
 *
 * Created at 5/8/2022 by NCChinh
 */
fun HttpServletRequest.token(): String {
    return this.getHeader(Constant.TOKEN_HEADER) ?: ""
}

fun <T : Enum<T>> String.enumOf(enumClazz : Class<T>): T? {
    for (e in enumClazz.enumConstants) {
        if (e.name == this) {
            return e
        }
    }
    return null
}

fun Boolean.granted() {
    if (!this) {
        throw ApiException()
            .httpStatus(HttpStatus.FORBIDDEN)
            .error(ErrorType.PERMISSION_DENIED)
    }
}
