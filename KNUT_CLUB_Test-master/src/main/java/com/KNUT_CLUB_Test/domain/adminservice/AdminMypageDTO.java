package com.KNUT_CLUB_Test.domain.adminservice;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class AdminMypageDTO {

    private String clubId;

    private String clubName;
    private String name;
    private String email;
    private String phone;
    private String img;

    public AdminMypageDTO(String clubName, String name, String email, String phone, String img) {
        this.clubName = clubName;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.img = img;
    }
}
