package com.KNUT_CLUB_Test.web.form;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AdminJoinForm {

    private String clubId;
    private String clubName;
    private String password;
    private String name;
    private String email;
    private String phone;
}
