package com.KNUT_CLUB_Test.domain.clubservice;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Club {
    private int num;
    private String campus;
    private String type;
    private String name;
    private String activity;
    private String introduce;
    private String promotion;
    private String img;

    public Club(String campus, String type, String name, String activity,
                String introduce, String promotion, String img) {
        this.campus = campus;
        this.type = type;
        this.name = name;
        this.activity = activity;
        this.introduce = introduce;
        this.promotion = promotion;
        this.img = img;
    }

    public Club(String name, String type, String promotion, String img) {
        this.name = name;
        this.type = type;
        this.promotion = promotion;
        this.img = img;
    }

    // Club
    public Club(int num, String name, String img) {
        this.num = num;
        this.name = name;
        this.img = img;
    }
}
