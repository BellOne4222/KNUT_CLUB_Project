package com.KNUT_CLUB_Test.domain.clubservice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter @Setter
@NoArgsConstructor
public class NewClubDTO {

    private String clubName;
    private String campus;
    private String type;
    private String activity;
    private String promotion;
    private MultipartFile img;

}
