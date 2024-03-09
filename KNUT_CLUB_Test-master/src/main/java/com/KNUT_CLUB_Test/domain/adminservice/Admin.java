package com.KNUT_CLUB_Test.domain.adminservice;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class Admin {
    private int num;
    private String name;

    /* 로그인 */
    private String clubId;
    private String password;
}
