package com.son;

import com.son.common.SearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by kiost on 2017-04-21.
 */
@Controller
@RequestMapping("/board00")
public class BoardController {
    @Autowired
    private BoardService service;

    @RequestMapping("/home.kosc")
    public String home(SearchVO searchVO, Model model) {
        searchVO.pageCalculate(service.selectBoardCount(searchVO));

        model.addAttribute("listview", service.selectBoardList(searchVO));
        model.addAttribute("pageVO", searchVO);

        return "list";
    }

    @RequestMapping("/boardForm.kosc")
    public String form(HttpServletRequest req, Model model) {
        model.addAttribute("req", req);
        service.writeForm(model);

        return "form";
    }

    @RequestMapping("/boardRead.kosc")
    public String read(HttpServletRequest req, Model model) {
        service.readBoard(req);
        model.addAttribute("boardInfo", service.selectBoardOne(req));
        return "read";
    }

    @RequestMapping("/boardSave.kosc")
    public String save(HttpServletRequest req, Model model, BoardVO boardVO) {
        model.addAttribute("req", req);
        service.write(model, boardVO);

        return "redirect:home.kosc";
    }

    @RequestMapping("/boardDelete.kosc")
    public String delete(HttpServletRequest req, Model model) {
        model.addAttribute("req", req);
        service.deleteBoard(model);

        return "redirect:home.kosc";
    }
}
