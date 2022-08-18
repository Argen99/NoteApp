package com.geektech.homework71.data.network

data class MainResultDto<T>(
    val info: InfoDto? = null,
    val result: List<T>
)
