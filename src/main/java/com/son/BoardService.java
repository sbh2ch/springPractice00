package com.son;

import com.son.common.SearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by kiost on 2017-04-21.
 */
@Service
public class BoardService {
    @Autowired
    private BoardDAO boardDAO;

    @Autowired
    private DataSourceTransactionManager txManager;

    public List<?> selectBoardList(SearchVO searchVO) {
        return boardDAO.selectBoardList(searchVO);
    }

    public BoardVO selectBoardOne(HttpServletRequest req) {
        String brdno = req.getParameter("brdno");
        return boardDAO.selectBoardOne(brdno);
    }

    public void writeForm(Model model) {
        HttpServletRequest req = (HttpServletRequest) model.asMap().get("req");

        String brdno = req.getParameter("brdno");

        if (brdno != null) {
            model.addAttribute("boardinfo", boardDAO.selectBoardOne(brdno));
        }
    }

    public void write(Model model, BoardVO boardVO) {
        HttpServletRequest req = (HttpServletRequest) model.asMap().get("req");
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = txManager.getTransaction(def);

        try {
            if (boardVO.getBrdno() == null || "".equals(boardVO.getBrdno())) {
                boardDAO.insertBoard(boardVO);
            } else {
                boardDAO.updateBoard(boardVO);
            }
            txManager.commit(status);
        } catch (Exception e) {
            txManager.rollback(status);
        }
    }

    public void deleteBoard(Model model) {
        HttpServletRequest req = (HttpServletRequest) model.asMap().get("req");
        String brdno = req.getParameter("brdno");

        boardDAO.deleteBoard(brdno);
    }

    public Integer selectBoardCount(SearchVO searchVO) {
        return boardDAO.selectBoardCount(searchVO);
    }

    public void readBoard(HttpServletRequest req) {
        boardDAO.readBoard(req.getParameter("brdno"));
    }
}
