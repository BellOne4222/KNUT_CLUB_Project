package com.KNUT_CLUB_Test.domain.memberservice;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class Member {
    private int n;
    private int num;
    private String name;
    private String department;
    private String birth_yy;
    private String birth_mm;
    private String birth_dd;
    private String gender;
    private String email;
    private String phone;
    private String address;
    private String detailAddress;
    private String club;
    private String motive;
    private boolean grade;
    private String img;

    private String studentID;
    private String password;

    /* 로그인 */
    public Member(String studentID, String password) {
        this.studentID = studentID;
        this.password = password;
    }

    /* 회원관리 */
    public Member(int n, int num, String name, String studentID, String department, String phone, String gender, boolean grade) {
        this.n = n;
        this.num = num;
        this.name = name;
        this.department = department;
        this.gender = gender;
        this.phone = phone;
        this.grade = grade;
        this.studentID = studentID;
    }

    /* 동아리 가입 */
    public Member(String name, String department, String phone) {
        this.name = name;
        this.department = department;
        this.phone = phone;
    }

    /* 회원 가입 */
    public Member(String name, String department, String birth_yy, String birth_mm, String birth_dd,
                  String gender, String email, String phone, String address, String detailAddress, String studentID, String password) {
        this.name = name;
        this.department = department;
        this.birth_yy = birth_yy;
        this.birth_mm = birth_mm;
        this.birth_dd = birth_dd;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.detailAddress = detailAddress;
        this.studentID = studentID;
        this.password = password;
    }

    //    public Member(String name, String studentID, String password, String department, String birth,
//                  String gender, String email, String phone, String address, String detailAddress) {
//        this.name = name;
//        this.studentID = studentID;
//        this.password = password;
//        this.department = department;
//        this.birth = birth;
//        this.gender = gender;
//        this.email = email;
//        this.phone = phone;
//        this.address = address;
//        this.detailAddress = detailAddress;
//    }
//
    /* 마이페이지 */
    public Member(String name, String email, String studentID, String department, String club, boolean grade, String img) {
        this.name = name;
        this.email = email;
        this.studentID = studentID;
        this.department = department;
        this.club = club;
        this.grade = grade;
        this.img = img;
    }
//
//    public Member(int n, int num, String name, String department, String gender, String grade, String studentID) {
//        this.n = n;
//        this.num = num;
//        this.name = name;
//        this.department = department;
//        this.gender = gender;
//        this.studentID = studentID;
//    }
//
//    public Member(String name, String department, String phone) {
//        this.name = name;
//        this.department = department;
//        this.phone = phone;
//    }

    //
//    public Member(int n, int num, String name, String studentID, String department, String phone, String club, String motive) {
//        this.n = n;
//        this.num = num;
//        this.name = name;
//        this.studentID = studentID;
//        this.department = department;
//        this.phone = phone;
//        this.club = club;
//        this.motive = motive;
//    }
//
//
//    public Member(int num, String name, String department, String studentID, String phone, String club, String motive) {
//        this.num = num;
//        this.name = name;
//        this.department = department;
//        this.studentID = studentID;
//        this.phone = phone;
//        this.club = club;
//        this.motive = motive;
//    }
}
