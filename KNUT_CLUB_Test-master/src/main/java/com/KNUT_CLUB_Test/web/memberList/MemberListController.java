package com.KNUT_CLUB_Test.web.memberList;

import com.KNUT_CLUB_Test.domain.adminservice.service.AdminService;
import com.KNUT_CLUB_Test.domain.memberservice.ManageService;
import com.KNUT_CLUB_Test.domain.memberservice.Member;
import com.KNUT_CLUB_Test.domain.memberservice.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberListController {

    private final AdminService adminService;

    @GetMapping("/memberList")
    public String goMemberList(@RequestParam(value = "select", required = false) String field_,
                               @RequestParam(value = "word", required = false) String query_,
                               @RequestParam(value = "p", required = false) String page_,
                               HttpSession session,
                               Model model) {

        String field = "name";
        if (field_ != null && !field_.equals(""))
            field = field_;

        String query = "";
        if (query_ != null && !query_.equals(""))
            query = query_;

        int page = 1;
        if (page_ != null && !page_.equals(""))
            page = Integer.parseInt(page_);

        String id = (String) session.getAttribute("id");
        String club = adminService.getAdminClub(id);

        List<Member> memberList = adminService.getMemberList(club, field, query, page);
        int count = adminService.getMemberCount(club, field, query);
        int startNum = page - (page - 1) % 5;
        int lastNum = (int) Math.ceil(count / 10);

        if (count % 10 != 0)
            lastNum = lastNum + 1;

        model.addAttribute("memberList", memberList);
        model.addAttribute("startNum", startNum);
        model.addAttribute("lastNum", lastNum);

        return "memberList/memberList";
    }

    @PostMapping("/memberList/delMember")
    public String delMember(@RequestParam("del_id") String[] delIds) {
        int[] ids = new int[delIds.length];

        for (int i = 0; i < delIds.length; i++)
            ids[i] = Integer.parseInt(delIds[i]);

        int result = adminService.delMemberAll(ids);

        return "redirect:/memberList";
    }

    @GetMapping("/permissionList")
    public String goPermissionList(@RequestParam(value = "select", required = false) String field_,
                                   @RequestParam(value = "word", required = false) String query_,
                                   @RequestParam(value = "p", required = false) String page_,
                                   HttpSession session,
                                   Model model) {

        String field = "name";
        if (field_ != null && !field_.equals(""))
            field = field_;

        String query = "";
        if (query_ != null && !query_.equals(""))
            query = query_;

        int page = 1;
        if (page_ != null && !page_.equals(""))
            page = Integer.parseInt(page_);

        String id = (String) session.getAttribute("id");
        String club = adminService.getAdminClub(id);

        List<Member> permissionList = adminService.getPermissionList(club, field, query, page);
        int count = adminService.getPermissionCount(club, field, query);
        int startNum = page - (page - 1) % 5;
        int lastNum = (int) Math.ceil(count / 10);

        if (count % 10 != 0)
            lastNum = lastNum + 1;

        model.addAttribute("permissionList", permissionList);
        model.addAttribute("startNum", startNum);
        model.addAttribute("lastNum", lastNum);

        return "memberList/permissionList";
    }
//    @PostMapping("/permissionMember")
//    public String permissionMember(@RequestParam("permission_id") String[] permissionIds) {
//        int[] ids = new int[permissionIds.length];
//
//        for (int i = 0; i < permissionIds.length; i++)
//            ids[i] = Integer.parseInt(permissionIds[i]);
//
//        int result = memberService.pser(ids);
//
//        return "redirect:/permissionList";
//    }

    @PostMapping("/permissionList/delNonMember")
    public String delNonMember(@RequestParam("permission_id") String[] permissionIds) {
        int[] ids = new int[permissionIds.length];

        for (int i = 0; i < permissionIds.length; i++)
            ids[i] = Integer.parseInt(permissionIds[i]);

        int result = adminService.delNonMemberAll(ids);

        return "redirect:/permissionList";
    }
}
