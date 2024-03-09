package com.KNUT_CLUB_Test.web.club;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ClubDetailController {

//    @GetMapping("/clubJoin/detail")
//    public String goClubDetail(@RequestParam("num") int num, HttpServletRequest request, Model model) {
//
//        ClubService service = new ClubService();
//        ManageService manageService = new ManageService();
//
//        String name = manageService.getClubName(num);
//        List<Club> list = service.getClubDetail(num);
//
//        model.addAttribute("list", list);
//        model.addAttribute("name", name);
//
//        return "/club/detail/clubJoinDetail";
//    }
}
