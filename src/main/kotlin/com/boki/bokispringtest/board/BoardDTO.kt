package com.boki.bokispringtest.board

data class CreateBoardDTO(
    val title: String,
    var content: String? = null,
    var viewCnt: Int = 0,
    var likeCnt: Int = 0,
)

data class UpdateBoardDTO(
    val title: String? = null,
    var content: String? = null,
    var likeCnt: Int? = 0,
)