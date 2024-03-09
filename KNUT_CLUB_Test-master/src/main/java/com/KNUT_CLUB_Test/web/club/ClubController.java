package com.KNUT_CLUB_Test.web.club;

import com.KNUT_CLUB_Test.domain.adminservice.service.AdminService;
import com.KNUT_CLUB_Test.domain.clubservice.Club;
import com.KNUT_CLUB_Test.domain.clubservice.NewClubDTO;
import com.KNUT_CLUB_Test.domain.clubservice.service.ClubService;
import com.KNUT_CLUB_Test.domain.memberservice.Member;
import com.KNUT_CLUB_Test.domain.memberservice.service.MemberService;
import com.KNUT_CLUB_Test.file.FileStore;
import com.KNUT_CLUB_Test.web.form.UploadFile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ClubController {

    private final ClubService clubService;
    private final MemberService memberService;
    private final AdminService adminService;
    private final FileStore fileStore;

    @GetMapping("/clubJoin")
    public String goClubJoin(@RequestParam(value = "select", required = false) String field_,
                             @RequestParam(value = "campus", required = false) String query_,
                             @RequestParam(value = "type", required = false) String query2_,
                             @RequestParam(value = "p", required = false) String page_,
                             Model model,
                             HttpSession session) {

        String field = "campus";
        if (field_ != null && !field_.equals(""))
            field = field_;

        String field2 = "type";
        if (field_ != null && !field_.equals(""))
            field = field_;

        String query = "";
        if (query_ != null && !query_.equals(""))
            query = query_;

        String query2 = "";
        if (query2_ != null && !query2_.equals(""))
            query2 = query2_;

        List<Club> clubList = clubService.getClubList(field, field2, query, query2);
        String admin = (String) session.getAttribute("admin");

        model.addAttribute("clubList", clubList);

        return "club/clubJoin";
    }

    @GetMapping("/newClub")
    public String goNewClub() {
        return "/club/newClub";
    }

    @GetMapping("/clubJoin/detail")
    public String goClubDetail(@RequestParam("num") int num,
                               Model model) {

        List<Club> clubList = clubService.getClubDetail(num);
        model.addAttribute("clubList", clubList);

        return "/club/detail/clubJoinDetail";
    }

    @GetMapping("/joinManual")
    public String goJoinManual() {
        return "club/joinManual";
    }

    @GetMapping("/clubJoin/membership")
    public String goMembership(@RequestParam("name") String clubName,
                               HttpSession session,
                               Model model) {

        String studentID = (String) session.getAttribute("id");
        boolean grade = memberService.getMemberGrade(studentID);
        List<Member> profile = memberService.getMemberClubJoin(studentID);



        if (studentID == null) {
            model.addAttribute("message", "로그인 후 이용해주세요.");
            model.addAttribute("url", "/clubJoin");
            return "alert";
        }
        else {
            if (grade == true) {
                model.addAttribute("message", "하나의 동아리만 가입이 가능합니다. 탈퇴 후 이용해주세요.");
                model.addAttribute("url", "/clubJoin");
                return "alert";
            }
            else {
                model.addAttribute("profile", profile);
                model.addAttribute("clubName", clubName);
                return "/club/membership";
            }
        }
    }

    /* 한명의 인원은 하나의 동아리 가입만 가능 */
    @PostMapping("/clubJoin/membership")
    public String doMembership(@RequestParam("content") String motive,
                               @RequestParam("club") String clubName,
                               HttpSession session,
                               Model model) {
        String id = (String) session.getAttribute("id");
        clubService.joinClub(id, clubName, motive);

        model.addAttribute("message", "가입을 축하드립니다. 승인 후 이용가능합니다.");
        model.addAttribute("url", "/clubJoin");

        return "/alert";
    }

    /* 동아리 생성 */
    @GetMapping("/club/create")
    public String goClubCreate(HttpSession session,
                               Model model) {

        String id = (String) session.getAttribute("id");
        String club = adminService.getAdminClub(id);

//        if (club != null) {
//            model.addAttribute("message", "동아리 생성이 불가능한 계정입니다.");
//            model.addAttribute("url", "/index");
//            return "/alert";
//        }

        model.addAttribute("newClub", new NewClubDTO());
        return "/club/newClub";
    }

    @PostMapping("/club/create")
    public String doClubCreate(@ModelAttribute("newClub") NewClubDTO dto,
                               @RequestParam("attachFile") MultipartFile file,
                               Model model) throws IOException {

        boolean check = clubService.createClub(dto);

        UploadFile attachFile = fileStore.storeFile(file, "");

        String club = dto.getClubName();

        if (attachFile != null) {

            String filename = fileStore.createStoreFileName(file.getOriginalFilename());
            String fullPath = "/attachFile/" + filename;
            clubService.uploadClubImg(fullPath, club);
        }

        if (check == true) {
            model.addAttribute("message", "동아리 생성에 성공하셨습니다.");
            model.addAttribute("url", "/index");
        }
        else {
            model.addAttribute("message", "필수 정보가 비어있습니다.");
            model.addAttribute("url", "/club/newClub");
        }

        return "/alert";
    }
}
