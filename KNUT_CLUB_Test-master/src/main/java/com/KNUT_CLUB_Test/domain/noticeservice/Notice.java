package com.KNUT_CLUB_Test.domain.noticeservice;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
@NoArgsConstructor
public class Notice {
    private int n;
    private int num;
    private String title;
    private String writer;
    private Date date;
    private String content;
    private int views;
    private boolean chk;
    private String img;

    public Notice(String title, String writer, String content) {
        this.title = title;
        this.writer = writer;
        this.content = content;
    }

    public Notice(int n, int num, String title, String writer, Date date, int views) {
        this.n = n;
        this.num = num;
        this.title = title;
        this.writer = writer;
        this.date = date;
        this.views = views;
    }

    public Notice(int num, String title, String writer, Date date, String content, int views, boolean chk, String img) {
        this.num = num;
        this.title = title;
        this.writer = writer;
        this.date = date;
        this.content = content;
        this.views = views;
        this.chk = chk;
        this.img = img;
    }

    public Notice(String title, String writer, String content, boolean chk) {
        this.title = title;
        this.writer = writer;
        this.content = content;
        this.chk = chk;
    }

    public Notice(int n, int num, String title, String writer, Date date, int views, boolean chk) {
        this.n = n;
        this.num = num;
        this.title = title;
        this.writer = writer;
        this.date = date;
        this.views = views;
        this.chk = chk;
    }

    public Notice(int num, String title, String writer, Date date, String content, int views, String img) {
        this.num = num;
        this.title = title;
        this.writer = writer;
        this.date = date;
        this.content = content;
        this.views = views;
        this.img = img;
    }
}
