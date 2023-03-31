package com.cn.mmd.worker.handler

import com.cn.mmd.common.Constant
import com.cn.mmd.model.other.ErrorResponseModel
import com.cn.mmd.model.other.ResponseModel
import com.cn.mmd.util.ApiException
import com.cn.mmd.util.enumi.ErrorType
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(ApiException::class)
    fun apiException(ex: ApiException): ResponseEntity<ResponseModel> {
        if (Constant.IS_DEBUG) {
            ex.printStackTrace()
        }
        return ResponseEntity
            .status(ex.httpStatus)
            .body(ResponseModel.error(ErrorResponseModel(ex)))
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun argumentNotValidException(ex: MethodArgumentNotValidException): ResponseModel {
        if (Constant.IS_DEBUG) {
            ex.printStackTrace()
        }
        return ResponseModel.error(ErrorResponseModel(ex))
    }

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun unknownException(ex: Exception): ResponseModel {
        if (Constant.IS_DEBUG) {
            ex.printStackTrace()
        }
        return ResponseModel.error(ErrorResponseModel(ex))
    }
}