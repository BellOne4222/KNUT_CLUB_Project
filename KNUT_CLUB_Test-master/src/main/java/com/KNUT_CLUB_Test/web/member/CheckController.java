package com.KNUT_CLUB_Test.web.member;

import com.KNUT_CLUB_Test.domain.mail.MailService;
import com.KNUT_CLUB_Test.domain.memberservice.Check;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Random;

@Slf4j
@Controller
@RequestMapping("/check")
@RequiredArgsConstructor
public class CheckController {

    /* 메일 */
    private final MailService mailService;

    /* 회원 약관동의 페이지 이동 */
    @GetMapping
    public String goCheck(Model model) {
        model.addAttribute("chk", new Check());
        return "/sign/check";
    }

    /* 관리자 약관동의 페이지 이동 */
    @GetMapping("/admin")
    public String goCheckAdmin(Model model) {
        model.addAttribute("chk", new Check());
        return "/sign/checkAdmin";
    }

    /* 약관동의 */
    @PostMapping
    public String doCheck(@ModelAttribute Check chk,
                          Model model) {

        Boolean chk_1 = chk.getChk_1();
        Boolean chk_2 = chk.getChk_2();

        if (chk_1==true && chk_2==true) {
            model.addAttribute("url", "/check/mailChk");
            return "/alert";
        }
        model.addAttribute("url", "/check");
        model.addAttribute("message", "이용약관과 개인정보 수집 및 이용에 대한 안내 모두 동의해주세요.");
        return "/alert";
    }

    /* 인증번호 발송 페이지 이동 */
    @GetMapping("/mailChk")
    public String goMailCheck() {
        return "/mail/mailChk";
    }

    /* 인증번호 발송 */
    @PostMapping("/mailChk")
    public String doMailCheck(@RequestParam("email") String email,
                              HttpSession session,
                              Model model) throws Exception {

        Random rand = new Random();

        String str = Integer.toString( rand.nextInt(8) + 1);

        for (int i = 0; i < 7; i++) {
            str+= Integer.toString(rand.nextInt(9));
        }

        mailService.sendMail(email,str);
        session.setAttribute("password", str);
        session.setAttribute("email", email);

        model.addAttribute("url", "/check/mailValid");
        model.addAttribute("message", "인증번호가 발송되었습니다.");

        return "alert";
    }

    /* 이메일 인증 페이지 이동 */
    @GetMapping("/mailValid")
    public String goMailValid(HttpSession session) {

        String str = (String) session.getAttribute("password");
        log.info("인증번호 : {}", str);

        return "/mail/mailValid";
    }

    /* 이메일 인증 */
    @PostMapping("/mailValid")
    public String doMailValid(@RequestParam("number") String number,
                              HttpSession session,
                              Model model) {

        String str = (String) session.getAttribute("password");

        if (number.equals(str)) {
            model.addAttribute("url", "/check/join");
            model.addAttribute("message", "인증에 성공하였습니다.");
        }
        else {
            model.addAttribute("url", "/check/mailValid");
            model.addAttribute("message", "인증에 실패하였습니다.");
        }

        return "alert";
    }

    /* 관리자 약관동의 */
    @PostMapping("/admin")
    public String doCheckAdmin(@ModelAttribute Check chk,
                          Model model) {

        Boolean chk_1 = chk.getChk_1();
        Boolean chk_2 = chk.getChk_2();

        if (chk_1==true && chk_2==true) {
            model.addAttribute("url", "/check/join/admin");
            return "/alert";
        }
        model.addAttribute("url", "/check/admin");
        model.addAttribute("message", "이용약관과 개인정보 수집 및 이용에 대한 안내 모두 동의해주세요.");
        return "/alert";
    }
}

