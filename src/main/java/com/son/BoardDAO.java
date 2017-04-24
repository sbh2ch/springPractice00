package com.son;

import com.son.common.SearchVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by kiost on 2017-04-21.
 */
@Repository
public class BoardDAO {
    @Autowired
    private SqlSession session;

    public List<?> selectBoardList(SearchVO searchVO) {
        return session.selectList("board.selectBoardList", searchVO);
    }

    public BoardVO selectBoardOne(String brdno) {
        return session.selectOne("board.selectBoardOne", brdno);
    }

    public void updateBoard(BoardVO boardVO) {
        session.update("board.updateBoard", boardVO);
    }

    public void insertBoard(BoardVO boardVO) {
        session.insert("board.insertBoard", boardVO);
    }

    public void deleteBoard(String brdno) {
        session.delete("board.deleteBoard", brdno);
    }

    public Integer selectBoardCount(SearchVO searchVO) {
        return session.selectOne("board.selectBoardCount", searchVO);
    }

    public void readBoard(String brdno) {
        session.update("board.updateBoardRead", brdno);
    }
}
