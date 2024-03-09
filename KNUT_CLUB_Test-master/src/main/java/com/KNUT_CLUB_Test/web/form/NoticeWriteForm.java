package com.KNUT_CLUB_Test.web.form;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class NoticeWriteForm {
    private String title;
    private String writer;
    private MultipartFile attachFile;
    private String content;
}
