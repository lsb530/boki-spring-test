package com.boki.bokispringtest.board

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RequestMapping("/api/boards")
@RestController
class BoardController(
    private val boardService: BoardService
) {
    @GetMapping
    fun getBoards(): ResponseEntity<Any> {
        return ResponseEntity.ok().body(boardService.getBoards())
    }

    @GetMapping("/{boardId}")
    fun getBoard(@PathVariable boardId: Long): ResponseEntity<Board> {
        return ResponseEntity.ok().body(boardService.getBoard(boardId))
    }

    @PostMapping
    fun createBoard(@RequestBody createBoardDTO: CreateBoardDTO): ResponseEntity<Any> {
        val createBoard = boardService.createBoard(createBoardDTO)
        val uri = URI.create("/api/boards/${createBoard.id}")
        return ResponseEntity.created(uri).body(createBoard)
    }

    @PatchMapping("/{boardId}")
    fun updateBoard(@PathVariable boardId: Long, @RequestBody updateBoardDTO: UpdateBoardDTO): ResponseEntity<Board> {
        return ResponseEntity.ok().body(boardService.updateBoard(boardId, updateBoardDTO))
    }

    @DeleteMapping("/{boardId}")
    fun deleteBoard(@PathVariable boardId: Long): ResponseEntity<Void> {
        boardService.deleteBoard(boardId)
        return ResponseEntity.noContent().build()
    }
}