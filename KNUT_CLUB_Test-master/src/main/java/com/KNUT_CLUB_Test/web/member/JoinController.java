package com.KNUT_CLUB_Test.web.member;

import com.KNUT_CLUB_Test.domain.adminservice.Admin;
import com.KNUT_CLUB_Test.domain.adminservice.service.AdminService;
import com.KNUT_CLUB_Test.domain.mail.MailService;
import com.KNUT_CLUB_Test.domain.memberservice.Member;
import com.KNUT_CLUB_Test.domain.memberservice.service.MemberService;
import com.KNUT_CLUB_Test.domain.noticeservice.Notice;
import com.KNUT_CLUB_Test.domain.noticeservice.service.NoticeService;
import com.KNUT_CLUB_Test.web.form.AdminJoinForm;
import com.KNUT_CLUB_Test.web.form.JoinForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/check/join")
@RequiredArgsConstructor
public class JoinController {

    private final MemberService memberService;
    private final NoticeService noticeService;
    private final AdminService adminService;
    private final MailService mailService;

    /* 회원 회원가입 페이지 이동*/
    @GetMapping
    public String goJoin(Model model, HttpSession session) {

        String password = (String) session.getAttribute("password");

        if (password==null) {
            model.addAttribute("url", "/check/mailChk");
            model.addAttribute("message", "이메일 인증이 필요합니다.");

            return "alert";
        }

        model.addAttribute("join", new JoinForm());
        return "/sign/join";
    }

    /* 관리자 회원가입 페이지 이동 */
    @GetMapping("/admin")
    public String goJoinAdmin(Model model) {

        model.addAttribute("adminJoin", new AdminJoinForm());
        return "/sign/joinAdmin";
    }

    /* 관리자 회원가입 */
    @PostMapping("/admin")
    public String doJoinAdimin(@ModelAttribute("adminJoin") AdminJoinForm adminJoinForm,
                               Model model) throws Exception {

        boolean check = adminService.getJoin(adminJoinForm);
        List<Notice> noticeList = noticeService.getNoticeSelect();
        List<Notice> boardList = noticeService.getBoardSelect();

        model.addAttribute("noticeList", noticeList);
        model.addAttribute("boardList", boardList);

        if (check == true) {
            mailService.sendMail("wonn510@a.ut.ac.kr",
                    "동아리 관리자 가입 문의 " +
                            " 동아리아이디 : " + adminJoinForm.getClubId() +
                            " 동아리명 : " + adminJoinForm.getClubName() +
                            " 동아리비밀번호 : " + adminJoinForm.getPassword() +
                            " 회장이름 : " + adminJoinForm.getName() +
                            " 회장이름 : " + adminJoinForm.getEmail() +
                            " 휴대전화 : " + adminJoinForm.getPhone()
            );
            model.addAttribute("message", "회원가입이 완료되었습니다. 승인을 기다려주세요.");
            model.addAttribute("url", "/index");
        }
        else {
            model.addAttribute("message", "필수 정보가 비어있습니다.");
            model.addAttribute("url", "/check/join/admin");
        }

        return "alert";
    }

    /* 회원 회원가입 */
    @PostMapping()
    public String doJoin(@ModelAttribute("join") JoinForm joinForm,
                         @RequestParam("birth_mm") String mm,
                         @RequestParam("gender") String gender,
                         Model model,
                         HttpSession session) {

        log.info("회원 회원가입");

        String birth = joinForm.getBirth_yy()+"."+mm+"."+joinForm.getBirth_dd();
        String email = (String) session.getAttribute("email");

        boolean check = memberService.getJoin(joinForm, birth, gender, email);
        List<Notice> noticeList = noticeService.getNoticeSelect();
        List<Notice> boardList = noticeService.getBoardSelect();

        model.addAttribute("noticeList", noticeList);
        model.addAttribute("boardList", boardList);

        if (check == true) {
            model.addAttribute("message", "회원가입이 완료되었습니다.");
            model.addAttribute("url", "/index");
        }
        else {
            model.addAttribute("message", "필수 정보가 비어있습니다.");
            model.addAttribute("url", "/check/join");
        }
        return "/alert";
    }
}
