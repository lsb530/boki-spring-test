package com.boki.bokispringtest.board

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class BoardService(
    private val boardRepository: BoardRepository
) {

    fun createBoard(createBoardDTO: CreateBoardDTO): Board {
        val (title, content) = createBoardDTO
        val board = Board(title = title, content = content)
        return boardRepository.save(board)
    }

    @Transactional(readOnly = true)
    fun getBoards(): MutableList<Board> {
        return boardRepository.findAll()
    }

    fun getBoard(id: Long): Board {
        val board = boardRepository.findByIdOrNull(id) ?: throw RuntimeException("Board not found")
        board.viewCnt += 1
        return board
    }

    fun updateBoard(id: Long, updateBoardDTO: UpdateBoardDTO): Board {
        return getBoard(id).also { board: Board ->
            updateBoardDTO.title?.let { board.title = it }
            updateBoardDTO.content?.let { board.content = it }
            updateBoardDTO.likeCnt?.let { board.likeCnt = it }
        }
    }

    fun deleteBoard(id: Long) {
        boardRepository.deleteById(id)
    }
}