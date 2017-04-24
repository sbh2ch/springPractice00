package com.son;

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
    public String home(Model model) {
        model.addAttribute("listview", service.selectBoardList());
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
        model.addAttribute("boardInfo", service.selectBoardOne(req));
        return "read";
    }

    @RequestMapping("")
    public String updata(HttpServletRequest req, Model model) {

        return "form";
    }
}
