# 프로젝트

제목: 너와 나의 연결 공간


## 📜 목차

 - [개발 주제](#-개발-주제)
 - [개발 기간](#-개발기간)
 - [개발 환경](#-개발-환경)
 - [주요 기능](#-주요-기능)
 - [서비스 아키텍처](#서비스-아키텍처)
 - [관계 데이터베이스 테이블 구조 및 관계도](#-관계-DB-테이블-구조-및-관계도)
 - [기능 구현 방식(Back End)](#기능-구현-방식Back-End)
 - [api 정리](#-api-정리)
 - [예시 화면](#-예시-화면)

## 💡 개발 주제

여러 등급의 사용자들이 자발적으로 관리하고 서로 실시간으로 소통하는 게시판을 구현하자 


## 🕔 개발기간

2023년 11월 15일 ~ 2024년 5월 21일
(유지보수 기간은 미포함)

## ⚙️ 개발 환경
 - **OS**: *Windows 11*
 - **programming language** : 'Java 11', 'JavaScript ES12'
 - 'JDK 11.0.18', 'JSP 2.3'
 - **DataBase** : *Oracle DB 18*
 - **Framework** : *Spring Framework 5.0.7.RELEASE*, *HikariCP*, *MyBatis*
 - **IDE** : 'STS 3.9.18.RELEASE(Eclipse 2021-09 (4.21.0))', 'SQL Developer 22.2.1.234.1810'
 - **Web Application Server** : 'Apache Tomcat 9.0'
 - **Cooperation Tools**: 'GitHub', 'Whimsical'
 - **Etc**: 'Ajax', 'JQuery'

## 🔧 주요 기능

- 게시물 업로드, 삭제, 수정, 읽기, 게시물 리스트 읽기
  
- 게시판 개설, 삭제, 게시판 목록 읽기, 게시판 설명 수정
  
- 댓글 작성, 수정, 삭제, 읽기
  
- 게시물 내 파일 업로드, 다운로드, 삭제, 이미지 파일 보기, 파일 리스트 읽기
  
- 로그인, 로그아웃, 회원 가입, 회원 탈퇴, 아이디 찾기, 비밀번호 재설정
  
- 마이 페이지, 기타정보 등록, 삭제, 수정, 읽기
  
- 마이 페이지를 통한 게시물, 댓글, 파일 읽기, 삭제

- 친구 추가 및 삭제 
  
- 회원 차단, 차단 해제, 권한 부여, 권한 회수

### 권한 리스트 
![auth_list](https://github.com/somecreater/springminiproject/assets/127456520/0e1f83e0-1506-48d5-982b-3cddeef69f0b)


- 친구 추가, 삭제
- 채팅방 생성, 입장, 나가기, 폭파, 채팅방 리스트 읽기, 채팅 메시지 전송

### 채팅 기능
| ![스크린샷 2024-05-29 105620](https://github.com/somecreater/springminiproject/assets/127456520/97480037-6288-4e04-bfed-bc30fa92efa4) | ![스크린샷 2024-05-29 105632](https://github.com/somecreater/springminiproject/assets/127456520/2c7c1120-4aec-48cd-9c1e-95753716e54c) |
|--------------------------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------|


## 서비스 아키텍처

https://whimsical.com/project-architecture-GFHYdk3n9kjUqHmXaip3H2


## 💿 관계 DB 테이블 구조 및 관계도

![DB Diagram](https://github.com/user-attachments/assets/eff45bf2-fc35-40a9-a271-ee8b595ec18a)


## 기능 구현 방식(Back End)

- 데이터 저장 및 처리 방식
    
    게시판 데이터, 댓글 데이터, 채팅방 데이터, 회원 데이터, 회원 권한 데이터, 회원 차단 데이터 등등 서비스에서 다루는 모든 데이터들을 Oracle Database에 저장하고, 서버와 DB 사이의 원활한 통신을 위해서, ORM 프레임워크 Mybatis와 Connection pool을 관리해주는 HikariCP를 이용한다.
  
    즉 데이터베이스에 접근하기 위한 요소는 mybatis, hikaricp xml 설정 파일, mapper 인터페이스, mapper xml 파일, dao 클래스(DB 상에 데이터 조회, 삽입,삭제,수정을 위한 클래스), dto 클래스(코드 내에서 데이터 교환을 위한 클래스)로 구성되어 있다.

    db 상의 데이터는 클라이언트에서 Ajax를 이용해서 api를 호출하고, api에서 mapper를 이용해서 sql문을 실행하는 식으로 Read, Insert, Delete, Update를 한다



- 회원 가입 처리 방식
   
   1. 사용자가 회원 가입 화면에서 아이디를 입력한다
   2. 아이디 길이 만족 여부는 js 코드로 간단히 확인하고 사용자 아이디의 중복 여부는 Ajax를 통해서 Rest API로 db 조회로 직접 중복 여부를 확인한다
   3. 위의 조건을 만족하면 사용자는 비밀번호, 사용자 이름, 휴대폰 번호를 입력하고, 입력값이 길이, 특정 조건을 만족하는 것 여부는 ajax로 Rest api를 통해 자바 정규식으로 확인한다.
   4. 위의 조건들이 만족하면, 입력 폼을 서버로 제출하고, 회원 가입이 완료 된다.
   5. 초기 회원 가입 시 회원의 권한은 일반 권한인 ‘common’이다.



- 로그인 처리 방식


  Spring Security의 '사용자 인증' 기능을 사용하였다.
  
  UserDetailsService를 직접 구현하였다.

  
   1. 사용자가 로그인 화면에서 데이터를 입력한다.
   2. 로그인을 할 때 DB 조회로 차단 여부를 확인하고, 해당 유저의 권한들을 DB 조회로 가져와서 해당 권한들을 부여하고 로그인을 완료 시킨다.


   로그인한 사용자의 모든 작성 데이터는 마이페이지에서 열람 및 삭제 가능하다. 



- 채팅 기능 구현 방식

   
  Spring webSocket STOMP를 이용한다.
  
  채팅방 데이터는 DB에 저장한다
  
  메세지는 Front-End와 코드 상에서 DTO 객체로만 처리하게 했다.



  
- 게시판 내 파일 처리 방식  

  
  파일의 용량은 하나에 최대 500MB 까지 등록 가능하고 한 요청 최대 1GB 까지 등록이 가능하다

  파일 처리에서 수정은 없고 다운로드, 삽입, 읽기, 삭제만 있다

  게시글 입력 창에서 파일을 업로드만 하고, 게시글 등록을 안하면 게시글 번호 없이 PRO_MEMBER_FILE 테이블에 데이터가 저장되고, 게시글 업로드가 된다면 PRO_MEMBER_FILE, PRO_FILE 테이블에 완전한 데이터를 저장한다


  

## 📋 api 정리


### **board api**

| API NAME       | URL                    | Method | URL Params                                                | Data Params                                                                                                                                                      | Success Response      | Error Response | etc(부가 설명)                                       |
|:----------------:|:------------------------:|:--------:|:-----------------------------------------------------------:|:------------------------------------------------------------------------------------------------------------------------------------------------------------------:|:-----------------------:|:----------------:|:--------------------------------------------------:|
| Board List     | /board/listboard       | GET    | boardsearch: search, Model: model                         | -                                                                                                                                                                | -                     | -              | View, 게시물 리스트 페이지(페이징 기능, 검색에 따라 게시물 리스트를 보여준다)     |
| Board Read     | /board/readBoard       | GET    | Long: bno, boardsearch: search, Model: model              | -                                                                                                                                                                | -                     | -              | View, 게시물 읽기 페이지                                    |
| Board Insert-1 | /board/createBoard     | GET    | boardsearch: search, Model: model                         | -                                                                                                                                                                | -                     | -              | View, 게시물 작성하기 페이지                                  |
| Board Insert-2 | /board/saveBoard       | POST   | board: brd, boardsearch: search, RedirectAttributes: rttr | board:{Long: bno, String: boardname, String: title String: content, String: writer, Date: regdate,  Date: udate, int: comment_num, List<attachfile>: attachlist} | String(redirect: URL) | -              | 게시물 작성하기 실행                                         |
| Board Update-1 | /board/updateBoard     | GET    | Long: bno, boardsearch: search, Model: model              |                                                                                                                                                                  | -                     | -              | View, 게시물 수정하기 페이지                                  |
| Board Update-2 | /board/updatesaveBoard | POST   | board: brd, boardsearch: search, RedirectAttributes: rttr | board:{Long: bno, String: boardname, String: title String: content, String: writer, Date: regdate,  Date: udate, int: comment_num, List<attachfile>: attachlist} | String(redirect: URL) | -              | 게시물 수정하기 실행                                         | 


| API NAME            | URL                      | Method | URL Params                                                      | Data Params                                                                                                                       | Success Response                                | Error Response                                  | etc(부가 설명)             |
|:---------------------:|:--------------------------:|:--------:|:-----------------------------------------------------------------:|:-----------------------------------------------------------------------------------------------------------------------------------:|:-------------------------------------------------:|:-------------------------------------------------:|:------------------------:|
| Board Delete -1     | /board/removeBoard       | POST   | board: brd, boardsearch: search, RedirectAttributes: rttr       | -                                                                                                                                 | String(redirect: URL)                           | -                                               | 게시물 삭제하기 실행               |
| Board Delete -2     | /board/directremoveBoard | POST   | Long: bno                                                       | -                                                                                                                                 | Map<String,Object> response (result: “success”) | Map<String,Object> response (result: “failure”) | 게시물 삭제하기(마이 페이지에서 이용)     |
| Boardlist List      | /board/selectBoardlist   | GET    | Model: model                                                    | -                                                                                                                                 | -                                               | -                                               | View, 게시판 목록 페이지          |
| Boardlist Insert -1 | /createBoardlist         | GET    | Model: model                                                    | -                                                                                                                                 | -                                               | -                                               | View, 게시판 개설하기 페이지        |
| Boardlist Insert -2 | /createBoardlistaction   | POST   | boardlist: brdli, boardsearch: search, RedirectAttributes: rttr | boardlist:{Long:boardnum, String: boardname, String: boardsubject, Date: regdate, String: reguserid, List<String>: manageridlist} | String(redirect: URL)                           | -                                               | 게시판 개설하기 실행               |
| Boardlist Delete-1  | /removeBoardlist         | GET    | Model: model                                                    | -                                                                                                                                 | -                                               | -                                               | View, 게시판 제거하기 페이지        |


| API NAME           | URL                    | Method | URL Params                        | Data Params | Success Response                                     | Error Response                                          | etc(부가 설명)               |
|:--------------------:|:------------------------:|:--------:|:-----------------------------------:|:-------------:|:------------------------------------------------------:|:---------------------------------------------------------:|:--------------------------:|
| Boardlist Delete-2 | /removeBoardlistaction | POST   | String: brdname                   | -           | Map<String,Object> response (result: “success”)      | Map<String,Object> response (result: “failure”)         | 게시판 제거하기 실행                 |
| Boardlist Update   | /updatebrdlistsubac    | POST   | String: brdname, String: brdsub   | -           | Map<String,Object> response(result: “success”)       | Map<String,Object> response result: “failure”)          | 게시판 설명 수정하기 실행              |
| Manager List       | /getauthlist           | GET    | boardsearch: search, Model: model | -           | Map<String,Object> response (authlist: List<String>) | Map<String,Object> response (authlist: “해당 게시판 관리자 아님”) | 특정 게시판 관리자 리스트 가져오기 실행      |


---

### **comment api**

| API NAME       | URL                      | Method | URL Params                             | Data Params                                                                       | Success Response      | Error Response | etc(부가 설명)      | 
|:----------------:|:--------------------------:|:--------:|:----------------------------------------:|:-----------------------------------------------------------------------------------:|:-----------------------:|:----------------:|:-----------------:|
| Comment List   | /comment/readcommentlist | GET    | Long: bno, int: pagenum                | -                                                                                 | -                     | -              | 댓글 리스트 가져오기 실행     |
| Comment Read   | /comment/readComment     | GET    | Long: rno                              | -                                                                                 | -                     | -              | 댓글 읽어오기 실행         |
| Comment Insert | /comment/insertcomment   | POST   | comment: cmt, RedirectAttributes: rttr | comment: { Long: rno, Long: bno, String: comments, String: writer, Date: regdate} | String(redirect: URL) | -              | 댓글 작성하기 실행         |
| Comment Update | /comment/updatecomment   | POST   | comment: cmt, RedirectAttributes: rttr | comment: { Long: rno, Long: bno, String: comments, String: writer, Date: regdate} | String(redirect: URL) | -              | 댓글 수정하기 실행         |
| Comment Delete | /comment/deletecomment   | POST   | Long: rno, String: userid              | -                                                                                 | -                     | -              | 댓글 삭제하기 실행         |

---

### **file api**

| API NAME      | URL         | Method | URL Params                         | Data Params                                                                                                                                                 | Success Response                                                                                                | Error Response | etc           |
|:---------------:|:-------------:|:--------:|:------------------------------------:|:-------------------------------------------------------------------------------------------------------------------------------------------------------------:|:-----------------------------------------------------------------------------------------------------------------:|:----------------:|:---------------:|
| File Upload   | /uploadFile | POST   | MultipartFile[]: uploadFile        | memberfile[]: { String: pro_mem_file_code, String: userid, String: uuid, String:  uploadPath, String: filename, Boolean: image,  Long: bno, Date: regDate;} | Map<String,Object> response (attachfilelist: List<file>) (thumbnaillist: List<File>) (result: “upload_success”) | -              | 파일 등록하기 실행       |
| File Display  | /display    | GET    | String: fileuri                    | -                                                                                                                                                           | ResponseEntity<Resource>                                                                                        | -              | 이미지 파일 보기 실행     | 
| File Donwload | /download   | GET    | String: fileuri                    | -                                                                                                                                                           | ResponseEntity<Resource>                                                                                        | -              | 파일 다운로드 실행       | 
| File Delete   | /deletefile | POST   | String: fileuri, boolean: filetype | -                                                                                                                                                           | Map<String,Object> response (result: “success”)                                                                 | -              | 파일 삭제하기 실행       | 

---

### **security api**

| API NAME      | URL              | Method | URL Params                                                 | Data Params                                                                                                                                                                         | Success Response      | Error Response | etc               |
|:---------------:|:------------------:|:--------:|:------------------------------------------------------------:|:-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------:|:-----------------------:|:----------------:|:-------------------:|
| Login-1       | /loginboard      | GET    | -                                                          | -                                                                                                                                                                                   | String(URL)           | -              | View, 로그인 페이지      |
| Login-2       | /loginaction     | GET    | -                                                          | -                                                                                                                                                                                   | String(redirect: URL) | -              | 로그인 실행              |
| LoginError    | /loginerror      | GET    | -                                                          | -                                                                                                                                                                                   | -                     | -              | View, 로그인 오류 페이지     |
| Logout        | /logoutaction    | GET    | -                                                          | -                                                                                                                                                                                   | String(redirect: URL) | -              | 로그아웃 실행           |
| ServiceJoin-1 | /boardjoin       | GET    | -                                                          | -                                                                                                                                                                                   | -                     | -              | View, 회원가입 페이지    |
| ServiceJoin-2 | /boardjoinaction | POST   | String: id, String: passwd, String username, String: phone | member: {String: userid, String: userpw, String: username, String: phone, Date: regdate, Date: udate, Boolean: enabled, List<auth>: authlist}, auth: {String: userid, String: auth} | String(redirect: URL) | -              | 회원가입 실행           | 

| API NAME                    | URL                 | Method | URL Params                                            | Data Params                                                                                                                                    | Success Response                                  | Error Response                                                                           | etc                     |
|:-----------------------------:|:---------------------:|:--------:|:-------------------------------------------------------:|:------------------------------------------------------------------------------------------------------------------------------------------------:|:---------------------------------------------------:|:------------------------------------------------------------------------------------------:|:-------------------------:|
| ID check                    | /idcheckaction      | GET    | String: id                                            | -                                                                                                                                              | Map<String,Object> response (response: “success”) | Map<String,Object> response (result: “failure”)                                          | 아이디 존재 여부 확인               |
| User Join information Check | /etcdatacheckaction | GET    | String: passwd, String: username, String: phonenumber | -                                                                                                                                              | Map<String,Object> response (result: “success”)   | Map<String,Object> response (result: “failure”)                                          | 회원 가입시 입력정보 적합성 확인         |  
| ID and Password Search      | /idsearch           | GET    | -                                                     | -                                                                                                                                              | -                                                 | -                                                                                        | View,아이디, 비밀번호를 찾는 페이지     |
| ID Search                   | /searchauth         | GET    | String: email, String: phone                          | -                                                                                                                                              | Map<String,Object> response (result: “success”)   | Map<String,Object> response (userid: “noid”) (userid: “String”) (result: “subsuccess”)   | 아이디 찾기 실행                  |
| Password Search             | /searchpass         | GET    | String: userid, String: email, String: phone          | -                                                                                                                                              | Map<String,Object> response (result: “success”)   | Map<String,Object> response (result: “failure”)                                          | 비밀번호 재설정 위한 작업 실행          | 
| Password Reset              | /resetpassword      | POST   | String: userid, String: newpass, String: renewpass    | member: {String: userid, String: userpw, String: username, String: phone, Date: regdate, Date: udate, Boolean: enabled, List<auth>: authlist}, | Map<String,Object> response (result: “success”)   | Map<String,Object> response (result: “notpass”) (result: “notmatch”) (result: “failure”) |                            |


| API NAME                   | URL              | Method | URL Params                                         | Data Params                                                                                                                        | Success Response                                          | Error Response                                       | Etc                     | 
|:----------------------------:|:------------------:|:--------:|:----------------------------------------------------:|:------------------------------------------------------------------------------------------------------------------------------------:|:-----------------------------------------------------------:|:------------------------------------------------------:|:-------------------------:|
| User information Read      | /getuserinfo     | GET    | String: userid                                     | -                                                                                                                                  | Map<String,Object> response (userinformation: “member”)   |                                                      | 회원의 정보 가져오기 실행                |
| User Name Read             | /getuserinfoname | GET    | String: userid                                     | -                                                                                                                                  | Map<String,Object> response (userrealname: “String”)      | Map<String,Object> (userrealname: “존재하지 않는 아이디입니다.”) | 회원의 아이디 받은 후 닉네임 반환 실행     | 
| MyPage-1                   | /myPage          | GET    | Model: model                                       | -                                                                                                                                  | -                                                         | -                                                    | View, 마이페이지                | 
| MyPage-2                   | /myPageetc       | GET    | String: userid                                     | -                                                                                                                                  | Map<String,Object> response (etc: “member_info_etc”)      | -                                                    | 회원의 기타정보 가져오기 실행-1         |  
| User etcinformation Read-1 | /etcread         | GET    | String userid                                      | -                                                                                                                                  | Map<String,Object> response (etc_info: “member_info_etc”) | -                                                    | 회원의 기타정보 가져오기 실행-2         |  
| User etcinformation Insert | /etcinsert       | POST   | member_info_etc: infoetc, RedirectAttributes: rttr | member_info_etc:{Long: member_etc, String: userid, String: mail, String: birth_date, String: about_me, Date: regdate, Date: udate} | String(redirect: URL)                                     | String(redirect: URL)                                | 회원의 기타정보 입력하기 실행           |  

| API NAME                   | URL         | Method | URL Params                                                      | Data Params                                                                                                                        | Success Response                                             | Error Response                                                                                  | Etc                       |  
|:----------------------------:|:-------------:|:--------:|:-----------------------------------------------------------------:|:------------------------------------------------------------------------------------------------------------------------------------:|:--------------------------------------------------------------:|:-------------------------------------------------------------------------------------------------:|:---------------------------:|
| User etcinformation Update | /etcupdate  | POST   | String: userid, String: birthday, String: mail, String: aboutme | member_info_etc:{Long: member_etc, String: userid, String: mail, String: birth_date, String: about_me, Date: regdate, Date: udate} | Map<String,Object> response (result: “success”)              | Map<String,Object> response (result: “violate”) Map<String,Object> response (result: “failure”) | 회원의 기타정보 수정하기 실행             | 
| User etcinformation Delete | /etcdelete  | POST   | String: userid                                                  | -                                                                                                                                  | Map<String,Object> response (result: “success”)              | Map<String,Object> response (result: “violate”) Map<String,Object> response (result: “failure”) | 회원의 기타정보 삭제하기 실행             | 
| User ban Check             | /checkban   | GET    | String: userid, String: userpass                                | -                                                                                                                                  | Map<String,Object> (banuser:“bancontent”, result: “success”) | Map<String,Object> response (result: “failure”)                                                 | 회원의 차단 여부 확인                 | 
| User ban Read              | /getban     | GET    | String: userid                                                  | -                                                                                                                                  | Map<String,Object> response  (banuser: “banuserobj”)         | -                                                                                               | 회원의 차단정보 가져오기 실행             |
| User auth Manage           | /authmanage | GET    | Model: model                                                    | -                                                                                                                                  | -                                                            | -                                                                                               | View, 회원들의 권한과 차단 관리 페이지  | 
| User auth Insert           | /authaction | POST   | String: userid, String: auth                                    | auth:{String: userid, String: auth}                                                                                                | Map<String,Object> response (result: “success”)              | Map<String,Object> response (result: “failure”)                                                 | 회원에게 권한 부여 실행                | 

| API NAME           | URL             | Method | URL Params                                      | Data Params                                                                                            | Success Response                                | Error Response                                  | Etc              |
|:--------------------:|:-----------------:|:--------:|:-------------------------------------------------:|:--------------------------------------------------------------------------------------------------------:|:-------------------------------------------------:|:-------------------------------------------------:|:------------------:|
| User auth Deleate  | /authdeaction   | POST   | String: userid, String: auth                    | -                                                                                                      | Map<String,Object> response (result: “success”) | Map<String,Object> response (result: “failure”) | 회원으로부터 권한 회수 실행    |
| User ban Insert    | /banaction      | POST   | String: userid, String: banreason, int: bantime | banuser:{Long: bannum, String: userid, String: banreason, int: period, Date: startdate, Date: enddate} | Map<String,Object> response (result: “success”) | Map<String,Object> response (result: “failure”) | 회원 차단 실행                | 
| User ban Delete    | /bandeaction    | POST   | String: userid                                  | -                                                                                                      | Map<String,Object> response (result: “success”) | Map<String,Object> response (result: “failure”) | 회원 차단 해제 실행           |  
| User Service out-1 | /boardout       | GET    | -                                               | -                                                                                                      | -                                               | -                                               | View, 회원 탈퇴 페이지        | 
| User Service out-2 | /boardoutaction | GET    | String: id, String: pass, Boolean: datareset    | -                                                                                                      | Map<String,Object> response (result: “success”) | Map<String,Object> response (result: “failure”) | 회원 탈퇴 실행                |

---

### **chat api**

| API NAME               | URL                  | Method             | URL Params                      | Data Params                                                                            | Success Response                                                 | Error Response                                  | etc          | 
|:------------------------:|:----------------------:|:--------------------:|:---------------------------------:|:----------------------------------------------------------------------------------------:|:------------------------------------------------------------------:|:-------------------------------------------------:|:--------------:|
| Friend insert          | /insertfriend        | POST               | String: userid, String: fuserid | friend:{String: friend_code, String: userid, String:  userid, Date: regdate}           | Map<String,Object> response (result: “success”)                  | Map<String,Object> response (result: “failure”) | 친구 추가             | 
| Friend delete          | /deletefriend        | POST               | String: userid, String: fuserid | -                                                                                      | Map<String,Object> response  (result: “success”)                 | Map<String,Object> response (result: “failure”) | 친구 삭제             |   
| Chatroom create        | /chatcreation        | POST               | String: title                   | chatroom:{String: chatroom_code, String: chatroom_title, String: regid, Date: regdate} | Map<String,Object> response (chatcode: “String”, user: “String”) |                                                 | 채팅방 생성           |  
| Chatroom participation | /chat                | GET                | String: code, Model: model      |                                                                                        | -                                                                | -                                               | 채팅방 참가           |  
| Chat send              | /chat/message/{code} | MESSAGE(WebSocket) | String: code, chatmessage: chat |                                                                                        | -                                                                | -                                               | 채팅 메시지 전송       |  
| Chatroom verification  | /chatverification    | GET                | String: code                    |                                                                                        | Map<String,Object> response (result: “success”)                  | Map<String,Object> response (result: “failure”) | 채팅방 존재여부 확인   |  

| API NAME        | URL         | Method | URL Params | Data Params | Success Response                                         | Error Response                                  | etc          |
|:-----------------:|:-------------:|:--------:|:------------:|:-------------:|:----------------------------------------------------------:|:-------------------------------------------------:|:--------------:|
| Chatroom list   | /chatlist   | GET    | -          | -           | Map<String,Object> response (chatlist: “List<chatroom>”) |                                                 | 채팅방 목록 불러오기  | 
| Chatroom delete | /chatdelete | POST   | -          | -           | Map<String,Object> response (result: “success”)          | Map<String,Object> response (result: “failure”) | 채팅방 삭제       |



---
---
🖥️🖥️🖥️🖥️🖥️🖥️🖥️🖥️

## 예시 화면 및 주요 화면 흐름도

https://whimsical.com/web-project-flowchart-KzhnuRNZndTGoWYduFpk2u
