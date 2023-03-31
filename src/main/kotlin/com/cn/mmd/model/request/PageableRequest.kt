package com.cn.mmd.model.request

import com.cn.mmd.util.enumi.ValidateError
import com.cn.mmd.util.isNull
import com.fasterxml.jackson.annotation.JsonInclude
import jakarta.validation.constraints.Min
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.domain.Sort.Order

@JsonInclude(JsonInclude.Include.NON_NULL)
data class PageableRequest(

    @field:Min(0, message = ValidateError.INVALID_PAGE_INDEX.toString())
    val pageIndex: Int? = null,

    @field:Min(0, message = ValidateError.INVALID_PAGE_SIZE.toString())
    val pageSize: Int? = null,

    val sorts: List<SortableRequest> = listOf(),
) {
    fun pageable(): Pageable {
        if (sorts.isEmpty()) {
            return PageRequest.of(pageIndex ?: 0, pageSize ?: 9999)
        }

        val sort = Sort.by(sorts.mapNotNull {
            if (it.property.isNull()) {
                return@mapNotNull null
            }

            Order(
                if (it.isDesc == true) Sort.Direction.DESC else Sort.Direction.ASC,
                it.property,
            )
        })
        return PageRequest.of(pageIndex ?: 0, pageSize ?: 9999, sort)
    }
}
