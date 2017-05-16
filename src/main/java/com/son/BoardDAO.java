package com.son;

import com.son.common.FileVO;
import com.son.common.SearchVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
 * Created by kiost on 2017-04-21.
 */
@Repository
public class BoardDAO {
    @Autowired
    private SqlSession session;

    public List<BoardVO> selectBoardList(SearchVO searchVO) {
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

    public void deleteBoardFile(Map<String, String[]> fparam) {
        session.insert("deleteBoardFile", fparam);
    }

    public void insertBoardFile(FileVO f) {
        session.insert("insertBoardFile", f);
    }

    public List<FileVO> selectBoardFileList(String param) {
        return session.selectList("selectBoardFileList", param);
    }

    public void insertReply(ReplyVO replyVO) {
        session.insert("insertReply", replyVO);
    }

    public void updateReply(ReplyVO replyVO) {
        session.insert("updateReply", replyVO);
    }


    public void deleteReply(String reno) {
        session.update("updateReplyOrder4Delete", reno);
        session.delete("deleteReply", reno);
    }

    public List<ReplyVO> selectBoardReplyList(String brdno) {
        return session.selectList("selectBoardReplyList", brdno);
    }

    public ReplyVO selectReplyParent(String reparent) {
        return session.selectOne("selectReplyParent", reparent);
    }

    public void updateReplyOrder(ReplyVO replyInfo) {
        session.update("updateReplyOrder", replyInfo);
    }


    public Integer selectReplyMaxOrder(String brdno) {
        return session.selectOne("selectReplyMaxOrder", brdno);
    }

    public Integer selectReplyChild(String reno) {
        return session.selectOne("selectReplyChild", reno);
    }
}
