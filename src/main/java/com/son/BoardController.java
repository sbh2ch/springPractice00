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
        String brdno = req.getParameter("brdno");
        model.addAttribute("listview", service.selectBoardOne(brdno));
        model.addAttribute("listview",service.selectBoardFileList(brdno));

        return "form";
    }

    @RequestMapping("/boardRead.kosc")
    public String read(HttpServletRequest req, Model model) {
        String brdno = req.getParameter("brdno");
        service.readBoard(req);
        model.addAttribute("boardInfo", service.selectBoardOne(brdno));
        model.addAttribute("listview", service.selectBoardFileList(brdno));
        model.addAttribute("replylist", service.selectBoardReplyList(brdno));

        return "read";
    }

    @RequestMapping("/boardSave.kosc")
    public String save(HttpServletRequest req, BoardVO boardVO) {
        String[] fileNo = req.getParameterValues("fileNo");
        service.write(fileNo, boardVO);

        return "redirect:home.kosc";
    }

    @RequestMapping("/boardDelete.kosc")
    public String delete(HttpServletRequest req) {
        String brdno = req.getParameter("brdno");
        service.deleteBoard(brdno);

        return "redirect:home.kosc";
    }

    @RequestMapping("/replySave")
    public String boardReplySave(ReplyVO replyVO) {
        service.insertReply(replyVO);
        return "redirect:boardRead.kosc?brdno=" + replyVO.getBrdno();
    }

    @RequestMapping("/replyDelete")
    public String boardReplyDelete(ReplyVO replyVO){
        if(!service.deleteReply(replyVO.getReno())){
            return "deleteFailure";
        }

        return "redirect:boardRead.kosc?brdno=" + replyVO.getBrdno();
    }
}
