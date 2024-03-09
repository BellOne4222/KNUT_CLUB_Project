package com.KNUT_CLUB_Test.web.index;

import com.KNUT_CLUB_Test.domain.noticeservice.Notice;
import com.KNUT_CLUB_Test.domain.noticeservice.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final NoticeService noticeService;

    @GetMapping("/")
    public String goMain() {
        return "redirect:/index";
    }

    @GetMapping("/index")
    public String goIndex(Model model) {

        List<Notice> noticeList = noticeService.getNoticeSelect();
        List<Notice> boardList = noticeService.getBoardSelect();

        model.addAttribute("noticeList", noticeList);
        model.addAttribute("boardList", boardList);

        return "/index";
    }
}
