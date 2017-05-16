package com.son;

import com.son.common.FileUtil;
import com.son.common.FileVO;
import com.son.common.SearchVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.HashMap;
import java.util.List;

/**
 * Created by kiost on 2017-04-21.
 */
@Service
public class BoardService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BoardService.class);

    @Autowired
    private BoardDAO boardDAO;

    @Autowired
    private DataSourceTransactionManager txManager;

    public List<BoardVO> selectBoardList(SearchVO searchVO) {
        return boardDAO.selectBoardList(searchVO);
    }

    public BoardVO selectBoardOne(String brdno) {
        return boardDAO.selectBoardOne(brdno);
    }

    public void write(String[] fileNo, BoardVO boardVO) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = txManager.getTransaction(def);

        FileUtil fs = new FileUtil();

        List<FileVO> fileList = fs.saveAllFiles(boardVO.getUploadfile());

        try {
            if (boardVO.getBrdno() == null || "".equals(boardVO.getBrdno())) {
                boardDAO.insertBoard(boardVO);
            } else {
                boardDAO.updateBoard(boardVO);
            }

            if (fileNo != null) {
                HashMap<String, String[]> fparam = new HashMap<String, String[]>();
                fparam.put("fileNo", fileNo);
                boardDAO.deleteBoardFile(fparam);
            }

            for (FileVO f : fileList) {
                f.setParentPK(boardVO.getBrdno());
                boardDAO.insertBoardFile(f);
            }

            txManager.commit(status);
        } catch (Exception e) {
            LOGGER.error(e.toString());
            txManager.rollback(status);
        }
    }

    public List<FileVO> selectBoardFileList(String brdno) {
        return boardDAO.selectBoardFileList(brdno);
    }

    public void deleteBoard(String brdno) {
        boardDAO.deleteBoard(brdno);
    }

    public Integer selectBoardCount(SearchVO searchVO) {
        return boardDAO.selectBoardCount(searchVO);
    }

    public void readBoard(String brdno) {
        boardDAO.readBoard(brdno);
    }

    public void insertReply(ReplyVO replyVO) {
        if (replyVO.getReno() == null || "".equals(replyVO.getReno())) {
            if (replyVO.getReparent() != null) {
                ReplyVO replyInfo = boardDAO.selectReplyParent(replyVO.getReparent());
                replyVO.setRedepth(replyInfo.getRedepth());
                replyVO.setReorder(replyInfo.getReorder());
                boardDAO.updateReplyOrder(replyInfo);
            } else {
                Integer reorder = boardDAO.selectReplyMaxOrder(replyVO.getBrdno());
                replyVO.setReorder(reorder);
            }
            boardDAO.insertReply(replyVO);
        } else {
            boardDAO.updateReply(replyVO);
        }
    }

    public boolean deleteReply(String reno) {
        Integer cnt = boardDAO.selectReplyChild(reno);

        if (cnt > 0) {
            return false;
        }
        boardDAO.deleteReply(reno);

        return true;
    }

    public List<ReplyVO> selectBoardReplyList(String brdno) {
        return boardDAO.selectBoardReplyList(brdno);
    }
}
