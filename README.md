# CodeSquad_OnlineTest

## step-1

- 시작 문구 출력

- Record 클래스의 re 객체 생성
- Random 클래스의 random 객체 생성

- while문 안에서 
  - random 객체를 통해 0~3 사이의 랜덤한 정수를 생성후 rand 변수에 저장
  - occur 메소드에 rand 를 인수로 주어 EventType enum의 type 을 결정후 저장
  - re 객체에 type 을 인수로 주어 add 메소드 실행
  - re 객체의 info 메소드 실행
  - re 객체의 out 변수가 3이 되면 최종 안타수를 출력후 게임 종료

### EventType enum
- EventType 은 야구에서 발생할 수 있는 4가지 이벤트들
- STRIKE, BALL, OUT, HIT으로 구성

### Main class
- occur(int) : 랜덤한 실수를 인수로 받아 EventType 중에 한 가지를 return

### Record class 
- Record는 strike, ball, out, hit의 개수를 저장하고 게임을 진행하기 위한 메소드들을 가지고 있음
#### variable
- strike, ball, out, hit
#### method
- add(EventType) : EventType형의 인수를 받아 type 종류에 따라 스트라이크, 볼, 아웃, 안타의 수를 증가
- reset() : 아웃, 안타의 경우 스트라이크와 볼의 개수를 0으로 초기화 해주고 다음 타자를 출력
- info() : 스트라이크, 볼, 아웃의 숫자를 출력


## step-2

- Team 형의 teamList ArrayList 생성
	- ArrayList로 관리함으로써 팀의 수가 늘어날 경우 비교적 간단한 변경을 통해 다른 팀과의 시합 진행 가능

- while문 안에서
  - 시작 문구와 메뉴 출력
  - 메뉴 입력시 예외가 발생하면 에러메세지 출력
  	- 정수가 아닌 경우
  	- 1~3을 벗어난 경우
  	- 데이터를 입력하기 전 시합을 시작하려는 경우
  - 메뉴 1번 선택시 teamList 를 dataInput 메소드의 인수로 주어 실행
  	- Team 클래스의 team 객체 생성
  	- Player 형의 playerList ArrayList 를 생성후 team 객체에 setPlayerList
  	- Record 클래스의 record 객체 생성, team 객체에 setRecord
  	- teamList 에 Team 객체를 add
  	- 9명의 타자 정보를 입력받아 Player 클래스의 player에 저장한 후 playerList 에 add
  		- 형식에 맞지 않게 입력한 경우 에러 메세지 출력
  - 메뉴 2번 선택시 teamList 를 dataOutput 메소드의 인수로 주어 실행
  	- teamList 로 부터 각 team 객체의 name 과 playerList 의 player 정보 출력
  - 메뉴 3번 선택시 teamList 를 gameStart 메소드의 인수로 주어 실행
  	- 시작 문구 출력
  	- 각 회마다 각 team 의 record 객체의 play 메소드로 경기를 진행후 resetAll 메소드로 해당 회에서의 정보를 초기화
  	- 모든 회차가 끝난 후 각 팀의 score 비교 후 경기 종료 (무승부인 경우 표시)

### EventType enum
- EventType 은 야구에서 발생할 수 있는 4가지 이벤트들
- STRIKE, BALL, OUT, HIT으로 구성

### Main class
#### method
- occur(float, Player) : 랜덤한 실수와 Player 형의 격체를 인수로 받아 player 객체의 타율과 실수의 크기를 비교하여 EventType 중에 한 가지를 return
- findPlayer(int, Team) : 인수로 받은 실수와 같은 숫자를 num 변수로 가지고 있는 player 를 return

### Team class
- Team 은 팀의 이름과, 선수 명단, 선공 여부, 팀의 성적을 기록하는 객체를 가지고 있음
#### variable
- name, playerList, firstAttackIs(선공을 결정), record
#### method
- startText(int) : 각 회에 선공 여부에 따라 회초인지 회말인지 결정후 공격 문구 출력

### Player class
- Player 는 선수의 번호, 이름, 각 공을 칠 확률을 계산하여 가지고 있음
#### variable
- num, name, batAve, strPer, ballPer, outPer
#### method
- infoDetail() : 선수의 번호, 이름, 타율 출력
- info() : 선수의 번호, 이름 출력

### Record class 
- Record 는 strike, ball, out, hit, score의 개수를 저장하고 게임을 진행하기 위한 메소드들을 가지고 있음
#### variable
- strike, ball, out, hit, batterNum(현재 타자의 번호), score
#### method
- play(Team)
	- batterNum 을 인수로 주어 findPlayer 메소드로 선수를 찾은 후, 그 선수를 occur 메소드의 인수로 주어 EventType 을 결정
	- EventType 과 Team 을 add 메소드의 인수로 주어 점수를 증가
	- batterNum 이 10이 되면 1로 변경 해주고 out의 수가 3이 되면 게임을 종료
- add(EventType, Team) : EventType 형의 인수를 받아 type 종류에 따라 스트라이크, 볼, 아웃, 안타, score 의 수를 증가
- reset(Team) : 아웃, 안타의 경우 스트라이크와 볼의 개수를 0으로 초기화 해주고 다음 타자를 출력
- resetAll(Team) : 회가 끝나는 경우 score 를 제외한 변수들을 모두 초기화 
- info() : 스트라이크, 볼, 아웃의 숫자를 출력
