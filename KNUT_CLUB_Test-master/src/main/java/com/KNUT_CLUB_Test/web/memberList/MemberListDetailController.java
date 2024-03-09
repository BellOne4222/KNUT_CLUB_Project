package com.KNUT_CLUB_Test.web.memberList;

import com.KNUT_CLUB_Test.domain.adminservice.UserDetailDTO;
import com.KNUT_CLUB_Test.domain.adminservice.service.AdminService;
import com.KNUT_CLUB_Test.domain.memberservice.ManageService;
import com.KNUT_CLUB_Test.domain.memberservice.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberListDetailController {

    private final AdminService adminService;

    @GetMapping("/memberList/detail")
    public String goMemberListDetail(@RequestParam("num") int num, Model model) {

        List<UserDetailDTO> profile = adminService.getUserDTO(num);
        model.addAttribute("profile", profile);

        return "memberList/detail/memberListDetail";
    }

    @GetMapping("/permissionList/detail")
    public String goPermissionListDetail(@RequestParam("num") int num,
                                         Model model) {

        List<UserDetailDTO> profile = adminService.getUserDTO(num);
        model.addAttribute("profile", profile);

        return "memberList/detail/permissionListDetail";
    }

    @PostMapping("/permissionList/permission")
    public String doPermission(@RequestParam("userId") int userId) {

        adminService.permissionUser(userId);

        return "redirect:/permissionList";
    }

}
