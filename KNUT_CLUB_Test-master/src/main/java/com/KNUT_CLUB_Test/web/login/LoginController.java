package com.KNUT_CLUB_Test.web.login;

import com.KNUT_CLUB_Test.domain.adminservice.Admin;
import com.KNUT_CLUB_Test.domain.adminservice.service.AdminService;
import com.KNUT_CLUB_Test.domain.memberservice.Member;
import com.KNUT_CLUB_Test.domain.memberservice.service.MemberService;
import com.KNUT_CLUB_Test.domain.noticeservice.Notice;
import com.KNUT_CLUB_Test.domain.noticeservice.service.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final MemberService memberService;
    private final NoticeService noticeService;
    private final AdminService adminService;

    /* 로그인 페이지 이동 */
    @GetMapping("/login")
    public String goLogin(Model model) {
        model.addAttribute("login", new Member());
        model.addAttribute("adminLogin", new Admin());
        return "/sign/login";
    }

    /* 로그아웃 */
    @GetMapping("/logout")
    public String goLogout(HttpSession session) {
        log.info("로그아웃");
        session.invalidate();
        return "redirect:/index";
    }

    /* 회원 로그인 */
    @PostMapping("/login")
    public String doLogin(HttpSession session,
                          @ModelAttribute("login") Member member,
                          Model model) {

        log.info("회원 로그인");
        log.info("학번 : {}", member.getStudentID());
        log.info("비밀번호 : {}", member.getPassword());

        /* sideBar */
        String name = memberService.getMemberName(member.getStudentID());
        String club = memberService.getMemberClub(member.getStudentID());

        /* login Check */
        String loginCheck = memberService.Login(member);

        /* notice & board */
        List<Notice> noticeList = noticeService.getNoticeSelect();
        List<Notice> boardList = noticeService.getBoardSelect();

        model.addAttribute("noticeList", noticeList);
        model.addAttribute("boardList", boardList);

        session.setAttribute("id", member.getStudentID());
        session.setAttribute("name", name);
        session.setAttribute("club", club);
        session.setAttribute("grade", "user");

        log.info("권한 : {}", session.getAttribute("grade"));

        if (loginCheck.equals(member.getStudentID())) {
            model.addAttribute("url", "/index");
        }
        else {
            model.addAttribute("message", "아이디 또는 패스워드가 잘못되었습니다");
            model.addAttribute("url", "/login");
        }
        return "/alert";
    }

    /* 관리자 로그인 */
    @PostMapping("/adminLogin")
    public String doAdminLogin(HttpSession session,
                               @ModelAttribute("adminLogin") Admin admin,
                               Model model) {

        log.info("관리자 로그인");
        log.info("아이디 : {}", admin.getClubId());
        log.info("비밀번호 : {}", admin.getPassword());

        String name = adminService.getAdminName(admin.getClubId());
        String club = adminService.getAdminClub(admin.getClubId());

        String loginCheck = adminService.Login(admin);
        List<Notice> noticeList = noticeService.getNoticeSelect();
        List<Notice> boardList = noticeService.getBoardSelect();

        model.addAttribute("noticeList", noticeList);
        model.addAttribute("boardList", boardList);

        session.setAttribute("id", admin.getClubId());
        session.setAttribute("name", name);
        session.setAttribute("club", club);
        session.setAttribute("grade", "admin");

        log.info("권한 : {}", session.getAttribute("grade"));

        if (loginCheck.equals(admin.getClubId())) {
            model.addAttribute("url", "/index");
        }
        else {
            model.addAttribute("message", "아이디 또는 패스워드가 잘못되었습니다");
            model.addAttribute("url", "/login");
        }
        return "/alert";
    }
}