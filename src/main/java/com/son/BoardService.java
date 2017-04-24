package com.son;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    public List<?> selectBoardList() {
        return boardDAO.selectBoardList();
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

    public void write(Model model) {
        HttpServletRequest req = (HttpServletRequest) model.asMap().get("req");

        String brdno = req.getParameter("brdno");
        BoardVO b = boardDAO.selectBoardOne(brdno);

        String brdwriter = req.getParameter("brdwriter");
        String brdtitle = req.getParameter("brdtitle");
        String brdmemo = req.getParameter("brdmemo");
        BoardVO boardVO = new BoardVO();
        boardVO.setBrdno(brdno);
        boardVO.setBrdwriter(brdwriter);
        boardVO.setBrdtitle(brdtitle);
        boardVO.setBrdmemo(brdmemo);

        if(b != null){
            boardDAO.updateBoard(boardVO);
        } else {
            boardDAO.insertBoard(boardVO);
        }
    }

    public void deleteBoard(Model model) {
        HttpServletRequest req = (HttpServletRequest) model.asMap().get("req");
        String brdno = req.getParameter("brdno");

        boardDAO.deleteBoard(brdno);
    }
}
