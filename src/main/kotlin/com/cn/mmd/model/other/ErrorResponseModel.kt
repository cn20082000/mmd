package com.cn.mmd.model.other

import com.cn.mmd.util.ApiException
import com.cn.mmd.util.enumi.ErrorType
import com.fasterxml.jackson.annotation.JsonInclude
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException

@JsonInclude(JsonInclude.Include.NON_NULL)
class ErrorResponseModel private constructor(
    val statusCode: Int,
    val error: ErrorType,
    val message: String,
    val stacktrace: String?,
) {
    constructor(ex: ApiException) : this(
        ex.httpStatus.value(),
        ex.errorType,
        ex.mess ?: ex.errorType.message(),
        ex.stackTraceToString()
    )

    constructor(ex: MethodArgumentNotValidException) : this(
        HttpStatus.BAD_REQUEST.value(),
        ErrorType.validType(ex.bindingResult.allErrors.first().defaultMessage),
        ErrorType.validType(ex.bindingResult.allErrors.first().defaultMessage).message(),
        ex.stackTraceToString()
    )

    constructor(ex: Exception) : this(
        HttpStatus.INTERNAL_SERVER_ERROR.value(),
        ErrorType.UNKNOWN_ERROR,
        ex.localizedMessage,
        ex.stackTraceToString()
    )
}