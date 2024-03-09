package com.KNUT_CLUB_Test.web.mypage;

//import com.KNUT_CLUB_Test.domain.member.Member;
//import com.KNUT_CLUB_Test.domain.member.MemberService;
import com.KNUT_CLUB_Test.domain.adminservice.AdminMypageDTO;
import com.KNUT_CLUB_Test.domain.adminservice.service.AdminService;
import com.KNUT_CLUB_Test.domain.memberservice.Member;
import com.KNUT_CLUB_Test.domain.memberservice.service.MemberService;

import com.KNUT_CLUB_Test.domain.noticeservice.Notice;
import com.KNUT_CLUB_Test.domain.noticeservice.service.NoticeService;
import com.KNUT_CLUB_Test.file.FileStore;
import com.KNUT_CLUB_Test.web.form.UploadFile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/mypage")
@RequiredArgsConstructor
public class MyPageController {

    private final MemberService memberService;
    private final AdminService adminService;
    private final NoticeService noticeService;
    private final FileStore fileStore;

    /* 회원 마이페이지 이동 */
    @GetMapping
    public String goMyPage(HttpSession session, Model model) {

        String studentID = (String) session.getAttribute("id");

        if (studentID == null) {
            model.addAttribute("message", "로그인 후 이용부탁드립니다.");
            model.addAttribute("url", "/index");
            return "/alert";
        }
        else {
            List<Member> profile = memberService.getMemberProfile(studentID);
            model.addAttribute("profile", profile);
            return "/mypage/mypage";
        }
    }

    /* 회원 마이페이지 수정페이지 이동 */
    @GetMapping("/update")
    public String goMypageUpdate(HttpSession session, Model model) {

        String studentID = (String) session.getAttribute("id");
        List<Member> profile = memberService.getMemberProfile(studentID);

        model.addAttribute("profile", profile);

        return "/mypage/userMypageUpdate";
    }

    /* 관리자 마이페이지 이동 */
    @GetMapping("/admin")
    public String goMyPageAdmin(HttpSession session, Model model) {

        String admin = (String) session.getAttribute("admin");
        String id = (String) session.getAttribute("id");

        if (id == null) {
            return "/login";
        }
        else {
            List<AdminMypageDTO> profile = adminService.getClubProfile(id);
            model.addAttribute("profile", profile);
            return "/mypage/aboutAdmin";
        }
    }

    /* 관리자 마이페이지 수정페이지 이동 */
    @GetMapping("/update/admin")
    public String goAdminMypageUpdate(HttpSession session, Model model) {

        String id = (String) session.getAttribute("id");
        List<AdminMypageDTO> profile = adminService.getClubProfile(id);

        model.addAttribute("profile", profile);

        return "/mypage/adminMypageUpdate";
    }

    /* 회원 마이페이지 정보 수정 */
    @PostMapping("/update")
    public String doMyPageUpdate(@RequestParam("name") String name,
                                 @RequestParam("email") String email,
                                 @RequestParam("department") String department,
                                 @RequestParam("club") String club,
                                 @RequestParam("attachFile") MultipartFile file,
                                 HttpSession session) throws IOException {

        String studentID = (String) session.getAttribute("id");

        if (name.equals("")) {
            name = memberService.getMemberName(studentID);
        }

        if (email.equals("")) {
            email = memberService.getMemberEmail(studentID);
        }

        if (department.equals("")) {
            department = memberService.getMemberDepartment(studentID);
        }

        if (club.equals("")) {
            club = memberService.getMemberClub(studentID);
        }

        UploadFile attachFile = fileStore.storeFile(file, "");

        memberService.getMemberUpdate(name, studentID, email, department, club);

        if (attachFile != null) {
            String filename = fileStore.createStoreFileName(file.getOriginalFilename());
            String fullPath = "/attachFile/" + filename;
            memberService.uploadProfile(fullPath, studentID);
        }

        return "redirect:/mypage";
    }

    /* 관리자 마이페이지 정보 수정 */
    @PostMapping("/update/admin")
    public String doAdminMyPageUpdate(@RequestParam("clubName") String clubName,
                                      @RequestParam("name") String name,
                                      @RequestParam("email") String email,
                                      @RequestParam("phone") String phone,
                                      @RequestParam("attachFile") MultipartFile file,
                                      HttpSession session) throws IOException {

        String clubId = (String) session.getAttribute("id");

        if (clubName.equals("")) {
            clubName = adminService.getAdminClub(clubId);
        }

        if (name.equals("")) {
            name = adminService.getAdminName(clubId);
        }

        if (email.equals("")) {
            email = adminService.getAdminEmail(clubId);
        }

        if (phone.equals("")) {
            phone = adminService.getAdminPhone(clubId);
        }

        UploadFile attachFile = fileStore.storeFile(file, "");

        adminService.getAdminUpdate(clubName, name, email, phone);

        if (attachFile != null) {
            String filename = fileStore.createStoreFileName(file.getOriginalFilename());
            String fullPath = "/attachFile/" + filename;
            adminService.uploadProfileAdmin(fullPath, clubId);
        }
        return "redirect:/mypage/admin";
    }

//    @PostMapping("/uploadProfile")
//    public String doUploadProfile(@RequestParam("attachFile") MultipartFile file,
//                                  @RequestParam("studentId") String id) throws IOException {
//
//        String domain = "/mypage/";
//        UploadFile attachFile = fileStore.storeFile(file, domain);
//
//        String filename = fileStore.createStoreFileName(file.getOriginalFilename());
//        String fullPath = "/attachFile/mypage/" + filename;
//
//        memberService.uploadProfile(fullPath, id);
//
//        return "redirect:/mypage";
//    }

    @PostMapping("/delete")
    public String userMyPageDelete(HttpSession session,
                                 Model model) {
        String studentID = (String) session.getAttribute("id");

        List<Notice> noticeList = noticeService.getNoticeSelect();
        List<Notice> boardList = noticeService.getBoardSelect();
        memberService.getMemberDelete(studentID);

        model.addAttribute("noticeList", noticeList);
        model.addAttribute("boardList", boardList);

        session.invalidate();
        return "/index";
    }

    @PostMapping("/delete/admin")
    public String adminMyPageDelete(HttpSession session,
                                 Model model) {

        String adminId = (String) session.getAttribute("id");

        List<Notice> noticeList = noticeService.getNoticeSelect();
        List<Notice> boardList = noticeService.getBoardSelect();
        adminService.cancelClub(adminId);

        model.addAttribute("noticeList", noticeList);
        model.addAttribute("boardList", boardList);

        session.invalidate();
        return "/index";
    }
}
