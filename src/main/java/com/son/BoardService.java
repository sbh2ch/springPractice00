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
}
