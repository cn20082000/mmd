package com.cn.mmd.util

import com.cn.mmd.util.enumi.ErrorType
import org.springframework.http.HttpStatus

class ApiException(
    val httpStatus: HttpStatus = HttpStatus.NOT_FOUND,
    val errorType: ErrorType = ErrorType.UNKNOWN_ERROR,
    val mess: String? = null,
) : Exception("${errorType.name}: $mess") {

    fun httpStatus(status: HttpStatus) = ApiException(status, errorType, mess)

    fun error(er: ErrorType) = ApiException(httpStatus, er, er.message())

    fun message(msg: String) = ApiException(httpStatus, errorType, msg)
}