package com.KNUT_CLUB_Test.web.introduce;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class IntroduceController {

    @GetMapping("/arrange")
    public String goArrange() {
        return "introduce/arrange";
    }

    @GetMapping("/aboutClub")
    public String goAboutClub() {
        return "introduce/aboutClub";
    }

}
