package com.KNUT_CLUB_Test.domain.eventsrvice;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Event {
    private int num;
    private String campus;
    private String type;
    private String name;
    private String activity;
    private String promotion;
    private String img;

    public Event(int num, String name, String img) {
        this.num = num;
        this.name = name;
        this.img = img;
    }

    /* eventDetail */
    public Event(String name, String promotion, String img) {
        this.name = name;
        this.promotion = promotion;
        this.img = img;
    }
}
