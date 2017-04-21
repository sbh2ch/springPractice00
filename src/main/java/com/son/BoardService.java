package com.son;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
