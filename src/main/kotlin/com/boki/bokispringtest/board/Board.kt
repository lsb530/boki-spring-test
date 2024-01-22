package com.boki.bokispringtest.board

import jakarta.persistence.*

@Entity
@Table
class Board (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,
    var title: String,
    var content: String? = null,
    var viewCnt: Int = 0,
    var likeCnt: Int = 0,
)