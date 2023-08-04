#### Team-Boutique

# 호텔 예약 서비스 구현 🏨

`Back-End` 와 `DB`를 연결해 호텔, 모텔, 리조트 등을 예약하는 서비스

## 0️⃣ 개발 환경
- ORACLE
 - SQLDeveloper
 - Eclipse
 - JAVA 8 버전

## 1️⃣ 팀원 소개

<table>
    <tr>
        <td align="center">
	    <a href="https://github.com/ezurno">
	    	<img src="https://avatars.githubusercontent.com/u/108059303?v=4?s=100" width="100px;" alt=""/>
	    	<br/>
	    	<sub>
	    	<b>이준모</b>
	    	<br/>
	    	<img src="https://us-central1-progress-markdown.cloudfunctions.net/progress/100"/>
	        </sub>
	    </a>
	</td>
        <td align="center">
	    <a href="https://github.com/sjsin0905">
	    	<img src="https://avatars.githubusercontent.com/u/97722177?v=4" width="100px;" alt=""/>
	    	<br/>
	    	<sub>
	    	<b>조용훈</b>
	    	<br/>
	    	<img src="https://us-central1-progress-markdown.cloudfunctions.net/progress/100"/>
	        </sub>
	    </a>
	    <br />
	</td>
        <td align="center">
	    <a href="https://github.com/gaamjaa">
	    	<img src="https://avatars.githubusercontent.com/u/49315208?v=4" width="100px;" alt=""/>
	    	<br/>
	    	<sub>
	    	<b>이해연</b>
	    	<br/>
	    	<img src="https://us-central1-progress-markdown.cloudfunctions.net/progress/100"/>
	        </sub>
	    </a>
	    <br />
	</td>
	<td align="center">
	    <a href="https://github.com/joareum">
	    	<img src="https://avatars.githubusercontent.com/u/43288938?v=4?s=100" width="100px;" alt=""/>
	    	<br/>
	    	<sub>
	    	<b>조아름</b>
	    	<br/>
	    	<img src="https://us-central1-progress-markdown.cloudfunctions.net/progress/100"/>
	        </sub>
	    </a>
	    <br />
	</td>
</table>

<br/>

## 2️⃣ Usecase Diagram 설계
- [Usecase Dirgram 설계 툴](draw.io)
- 해당 설계를 구현하기 위해 Usecase 를 설계

<br/>
<img src="md_resources/resource_01.png" width=400/>
<br/>

고객은 크게 3가지 `예약, 회원가입, 조회` 에 접근 할 수 있음

`예약`을 해야만 예약을 `변경`, `취소` 할 수 있기 때문에 예약과 예약 취소, 변경은 ***include 관계***

<br/>

## 3️⃣ DB 모델링

<br/>
<img src="md_resources/resource_02_DBModeling.png"  width=400/>
<br/>

`eXERD` 를 이용해 **DB 모델링**

`Customer` (고객) 와 `Accommodation` (숙소) 는 **서로 다 vs 다 관계** 이므로 각각 `PK` 를 `FK` 로 주어 1 vs 다 구조로 변경해주어야 함

따라서 `Book` (예약) table 을 생성해 비실체화 함

<br/>

## 4️⃣ Class Diagram 구성

<br/>
<img src="md_resources/resource_05_CD.drawio.png" width=600/>
<br/>

상단의 **DB 모델링을 이용해 Class Diagram 을 설계** 했음

각각의 `Hotel`, `Motel`, `Resort`, `Pension` 은 특정한 변수를 갖고 있으며 `Accommodation` 을 상속받음

<br/>

## 5️⃣ FE-UI 구현
- FE 기술을 사용하지 않아도 출력화면을 구현을 해야 구조를 이해하기 편함
- 생각치 못한 부분을 떠올릴 수 있음
- 고객과 관리자로 두가지 구현

<br/>
<img src="md_resources/resource_04_Customer.png" width=600/>
<br/>

상단 바에 숙소 명을 기입해 찾을 수 있음

`호텔`, `모텔`, `리조트`, `팬션` 버튼을 이용해 하단의 `LIST` 에 해당 조건에 맞는 출력 할 수 있음

`가격대별`, `지역별`로 조회 가능

<br/>

특정 호텔을 클릭 후 예약날짜를 기입, 숙박 명수 를 기입 해 예약

<br/>

**우측에는 회원정보 기입란이 있으며 해당 정보를 등록하면 주민등록번호 (SSN) 이 중복되지 않는 이상 해당 고객을 등록**

중복되는 SSN 을 갖고 있을 경우 `Exception` 발생

<br/>

하단에 해당 고객의 예약 정보를 조회 가능, 만약 예약 날짜를 변경할 경우 중단에 날짜 기입을 한 후 수정

<br/>
<img src="md_resources/resource_03_Admin.png" width=600/>
<br/>

해당 화면은 관리자 탭.

상단의 검색 바에서 **아이디**, **이름**, **지역** 등 카테고리에 일치하는 값을 찾아 리스트에 출력

만약 호텔을 추가할 경우 하단의 업체명과 **Radio-Button** 을 이용해 숙박 시설을 선택, 호텔의 등급을 별모양을 눌러 적용 및 최대 수용가능인원 입력

입력 받은 값으로 등록과 수정이 가능함

<br/>

우측 고객조회 란은 고객 조회를 할 수 있으며 **아이디**, **이름**을 이용해 조회

하단에 ***고객의 상세정보를 조회*** 할 수 있음

맨 ***우측에는 선택 고객의 예약 리스트***를 볼 수 있음