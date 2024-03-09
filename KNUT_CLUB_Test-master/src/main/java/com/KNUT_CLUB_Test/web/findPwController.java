package com.KNUT_CLUB_Test.web;

import com.KNUT_CLUB_Test.domain.memberservice.service.MemberService;
import com.KNUT_CLUB_Test.web.form.FindPwForm;
import com.KNUT_CLUB_Test.web.form.ResetPwForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
public class findPwController {

    private final MemberService memberService;

    @GetMapping("/findPw")
    public String goFindPassword(Model model) {
        model.addAttribute("findPw", new FindPwForm());
        return "/sign/findPw";
    }

    @PostMapping("/findPw")
    public String doFindPassword(@ModelAttribute("findPw") FindPwForm findPwForm,
                                 HttpSession session,
                                 Model model) {

        boolean chk = memberService.findPassword(findPwForm);
        session.setAttribute("userId", findPwForm.getStudentId());


        if (chk == false) {
            model.addAttribute("message", "유효하지 않은 정보입니다.");
            model.addAttribute("url", "/findPw");

        } else {
            model.addAttribute("message", "인증이 완료되었습니다.");
            model.addAttribute("url", "/resetPw");
        }
        return "alert";
    }

    @GetMapping("/resetPw")
    public String goResetPassword(Model model) {

        model.addAttribute("resetPw", new ResetPwForm());
        return "/sign/resetPw";
    }

    @PostMapping("/resetPw")
    public String doResetPassword(@ModelAttribute("resetPw") ResetPwForm resetPwForm,
                                  HttpSession session,
                                  Model model) {

        log.info("비밀번호 : {}", resetPwForm.getPassword());
        log.info("비밀번호_ : {}", resetPwForm.getPassword_());

        if (resetPwForm.getPassword().equals("")||resetPwForm.getPassword_().equals("")||!resetPwForm.getPassword().equals(resetPwForm.getPassword_())) {
            model.addAttribute("message", "비밀번호가 일치하지 않습니다.");
            model.addAttribute("url", "/resetPw");
        }
        else {
            String userId = (String) session.getAttribute("userId");
            memberService.resetPassword(resetPwForm, userId);
            model.addAttribute("message", "비밀번호가 변경되었습니다.");
            model.addAttribute("url", "/login");
        }
        return "alert";
    }
}
