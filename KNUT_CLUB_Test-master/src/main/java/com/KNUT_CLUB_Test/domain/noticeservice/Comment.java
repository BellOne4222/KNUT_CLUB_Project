package com.KNUT_CLUB_Test.domain.noticeservice;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
@NoArgsConstructor
public class Comment {
    private int num;
    private int board_num;
    private String writer;
    private Date date;
    private String content;

    public Comment(int num, String writer, Date date, String content) {
        this.num = num;
        this.writer = writer;
        this.date = date;
        this.content = content;
    }
}
