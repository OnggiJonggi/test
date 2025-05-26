----------------시스템계정--------------------
create user hugme identified by hugme;
grant resource, connect to hugme;
----------------------------------------------

--회원 식별번호 시퀀스
CREATE SEQUENCE seq_me
START WITH 1
INCREMENT BY 1
NOCACHE;

--토큰번호 시퀀스
CREATE SEQUENCE seq_token
START WITH 1
INCREMENT BY 1
NOCACHE;

--member테이블
CREATE TABLE member (
    user_no    NUMBER        NOT NULL,
    user_id    VARCHAR2(50)  NOT NULL,
    user_pwd   VARCHAR2(100) NOT NULL,
    user_name  VARCHAR2(50)  NOT NULL,
    enrolldate DATE          DEFAULT SYSDATE,
    modifydate DATE          DEFAULT SYSDATE,
    status     VARCHAR2(1)   DEFAULT 'U' CHECK (status IN ('Y', 'N', 'E', 'U')),
    
    CONSTRAINT USER_NO_PK    PRIMARY KEY (user_no),
    CONSTRAINT USER_ID_NN    CHECK (user_id IS NOT NULL),
    CONSTRAINT USER_ID_UQ    UNIQUE (user_id),
    CONSTRAINT USER_PWD_NN   CHECK (user_pwd IS NOT NULL),
    CONSTRAINT USER_NAME_NN  CHECK (user_name IS NOT NULL),
    CONSTRAINT STATUS_CK     CHECK (status IN ('Y', 'N', 'E', 'U'))
);
COMMENT ON TABLE member IS '회원정보';
COMMENT ON COLUMN member.user_no IS '식별번호';
COMMENT ON COLUMN member.user_id IS '아이디';
COMMENT ON COLUMN member.user_pwd IS '비밀번호';
COMMENT ON COLUMN member.user_name IS '이름';
COMMENT ON COLUMN member.enrolldate IS '가입일';
COMMENT ON COLUMN member.modifydate IS '수정일';
COMMENT ON COLUMN member.status IS '회원 상태 (Y:정상, N:탈퇴, E:휴면, U:이메일 미인증)';



--이메일 인증용 토큰 저장소
CREATE TABLE token (
    user_no         NUMBER,
    token_no        NUMBER CONSTRAINT token_no_nn NOT NULL,
    token           VARCHAR2(50) CONSTRAINT token_nn NOT NULL,
    generated_time  DATE DEFAULT SYSDATE CONSTRAINT generated_time_nn NOT NULL,
    token_status    VARCHAR2(1)   DEFAULT 'Y' CHECK (status IN ('Y', 'N')),
    
    CONSTRAINT member_insert_email_pk PRIMARY KEY (token_no),
    CONSTRAINT member_insert_email_user_no_fk FOREIGN KEY (user_no) REFERENCES member(user_no)
);
COMMENT ON TABLE token IS '토큰';
COMMENT ON COLUMN token.user_no IS '회원 식별번호';
COMMENT ON COLUMN token.token_no IS '토큰번호';
COMMENT ON COLUMN token.token IS '토큰';
COMMENT ON COLUMN token.generated_time IS '토큰생성시간';
COMMENT ON COLUMN token.token_status IS '토큰 상태 (Y:정상, N:사용불가)';