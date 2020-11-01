package com.wnsgml972.strada.common.service

data class PageInfo<T> (
    val totalCount: Long,
    val data: List<T>
)