package com.son;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by kiost on 2017-05-10.
 */
@Controller
public class MvcController {

    @RequestMapping("/home")
    public String home(Model model){
        model.addAttribute("testText","helloWorld");

        return "home";
    }
}
