package com.son;

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

    public List<?> selectBoardList() {
        return session.selectList("board.selectBoardList");
    }

    public BoardVO selectBoardOne(String brdno){
        return session.selectOne("board.selectBoardOne", brdno);
    }
}
