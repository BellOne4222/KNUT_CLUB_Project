package com.KNUT_CLUB_Test.domain.eventsrvice;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter @Setter
@NoArgsConstructor
public class EventPostDTO {

    private String campus;
    private String type;
    private String name;
    private String activity;
    private String promotion;
//    private String img;
//    private String date;

}
