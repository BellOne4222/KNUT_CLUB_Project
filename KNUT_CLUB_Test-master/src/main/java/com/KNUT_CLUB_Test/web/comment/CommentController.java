package com.KNUT_CLUB_Test.web.comment;

import com.KNUT_CLUB_Test.domain.noticeservice.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CommentController {

    private final NoticeService noticeService;

    @PostMapping("/writeComment")
    public String writeComment(@RequestParam("comment") String comment,
                               @RequestParam("board_num") int board_num,
                               HttpSession session,
                               Model model) {

        String name = (String) session.getAttribute("name");
        String id = (String) session.getAttribute("id");

        if (id == null) {
            model.addAttribute("message", "로그인 후 이용해주세요.");
            model.addAttribute("url", "/board/detail?num=" + board_num);
        }
        else {
            noticeService.writeComment(board_num, name, comment);
            model.addAttribute("message", "작성이 완료되었습니다.");
            model.addAttribute("url", "/board/detail?num=" + board_num);
        }

        return "alert";
    }

    @PostMapping("/deleteComment")
    public String deleteComment(@RequestParam("comment_num") int comment_num,
                                @RequestParam("board_num") int board_num,
                                Model model,
                                HttpSession session) {

        String name = (String) session.getAttribute("name");

        noticeService.deleteComment(comment_num);

        model.addAttribute("message", "삭제되었습니다.");
        model.addAttribute("url", "/board/detail?num=" + board_num);

        return "alert";
    }
}
