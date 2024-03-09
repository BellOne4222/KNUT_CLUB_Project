CREATE DATABASE KNUT_CLUB;

USE KNUT_CLUB;

# 유저 데이터베이스
CREATE TABLE IF NOT EXISTS MEMBER
(
    num           INT          NOT NULL AUTO_INCREMENT,
    name          VARCHAR(10)  NOT NULL,
    studentID     VARCHAR(10)  NOT NULL ,
    password      VARCHAR(20)  NOT NULL,
    department    VARCHAR(20)  NOT NULL,
    birth         VARCHAR(10)  NOT NULL,
    gender        VARCHAR(5)   NOT NULL,
    email         VARCHAR(30)  NOT NULL,
    phone         VARCHAR(15)  NOT NULL,
    address       VARCHAR(50)  NOT NULL,
    detailAddress VARCHAR(50)  NOT NULL,
    authority     INT          NOT NULL,
    club          VARCHAR(20),
    motive        TEXT
    PRIMARY KEY(num)
);


# 공지사항 데이터베이스
CREATE TABLE IF NOT EXISTS NOTICE
(
    num     int         NOT NULL AUTO_INCREMENT,
    title   VARCHAR(30) NOT NULL,
    writer  VARCHAR(10) NOT NULL,
    date    DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    content text,
    views   INTEGER,
    PRIMARY KEY(num)
);

# 자유게시판 데이터베이스
CREATE TABLE IF NOT EXISTS BOARD
(
    num     int         NOT NULL AUTO_INCREMENT,
    title   VARCHAR(30) NOT NULL,
    writer  VARCHAR(10) NOT NULL,
    date    DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    content text,
    views   INTEGER,
    PRIMARY KEY(num)
);


# 댓글 테이블
CREATE TABLE IF NOT EXISTS COMMENT
(
    num         INT NOT NULL AUTO_INCREMENT,
    board_num   VARCHAR(5) ,
    writer      VARCHAR(10)  NOT NULL,
    date        DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    content     text,
    PRIMARY KEY(num)
)



# 동아리 홍보게시판 데이터베이스
CREATE TABLE IF NOT EXISTS PROMOTION
(
    num         int             NOT NULL AUTO_INCREMENT,
    activity    VARCHAR(10)     NOT NULL,
    introduce   TEXT            NOT NULL,
    promotion   TEXT            NOT NULL,
    name        VARCHAR(100)    NOT NULL,
    campus      VARCHAR(10)     NOT NULL,
    type        VARCHAR(10)     NOT NULL,
    img         VARCHAR(30)
    PRIMARY KEY(num)
);

# 동아리 행사 데이터베이스
CREATE TABLE IF NOT EXISTS EVENT
(
    num         int           NOT NULL AUTO_INCREMENT,
    campus      VARCHAR(10)   NOT NULL,
    type        VARCHAR(10)   NOT NULL,
    name        VARCHAR(100)  NOT NULL,
    field       VARCHAR(10)   NOT NULL,
    introduce   VARCHAR(100)  NOT NULL,
    promotion   TEXT          NOT NULL,
    date        DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    img         VARCHAR(30)
    PRIMARY KEY(num)
);




