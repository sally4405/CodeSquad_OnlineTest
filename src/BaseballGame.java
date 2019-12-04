import java.util.*;

public class BaseballGame {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        ArrayList<Team> teamList = new ArrayList<>();

        int selectNum;

        while (true) {
            System.out.println("신나는 야구시합");
            System.out.println("1. 데이터 입력");
            System.out.println("2. 데이터 출력");
            System.out.println("3. 시합 시작\n");
            System.out.print("메뉴선택 (1 - 3) ");
            selectNum = scan.nextInt();
            scan.nextLine();    //개행문자(\n) 제거를 위해

            if(selectNum == 1) dataInput(teamList);
            else if(selectNum == 2) dataOutput(teamList);
            else if(selectNum == 3) {
                gameStart(teamList);
                break;
            }
            else System.out.println("* 1-3 사이의 숫자를 입력해주세요. *");
        }
    }

    public static void dataInput(ArrayList<Team> teamList) {
        Scanner scan = new Scanner(System.in);

        String[] playerInfo;
        String name;
        float batAve;

        for(int i = 0; i < 2; i++){
            Team team = new Team();
            ArrayList<Player> playerList = new ArrayList<>();
            Record record = new Record();

            System.out.print((i+1) + "팀의 이름을 입력하세요> ");
            team.setName(scan.nextLine());
            team.setPlayerList(playerList);
            teamList.add(team);

            if(i == 0) team.setFirstAttackIs(true);
            team.setRecord(record);

            for(int j = 0; j < 9; j++) {
                System.out.print((j+1) + "번 타자 정보 입력> ");
                playerInfo = scan.nextLine().split(", ");
                name = playerInfo[0];
                batAve = Float.parseFloat(playerInfo[1]);

                Player player = new Player(j+1, name, batAve);
                playerList.add(player);
            }
            System.out.println();
        }

        System.out.println("\n팀 데이터 입력이 완료되었습니다.\n ");

    }

    public static void dataOutput(ArrayList<Team> teamList) {
        for(Team team : teamList) {
            System.out.println(team.getName() + " 팀 정보");
            for(Player player : team.getPlayerList()) {
                player.infoDetail();
            }
            System.out.println();
        }
    }

    public static void gameStart(ArrayList<Team> teamList) {
        Random random = new Random();

        System.out.println(teamList.get(0).getName() + " VS " + teamList.get(1).getName() + "의 시합을 시작합니다. \n");

        for(int i = 0; i < 6; i++) {
            for(Team team : teamList) {
                team.startText(i+1);
                ArrayList<Player> playerList = team.getPlayerList();
                team.getRecord().play(team);
                team.getRecord().resetAll(team);
            }
        }
        System.out.println("경기 종료\n");

        System.out.println(teamList.get(0).getName() + " VS " + teamList.get(1).getName());
        System.out.print(teamList.get(0).getRecord().getScore() + " : " + teamList.get(1).getRecord().getScore());
        if(teamList.get(0).getRecord().getScore() == teamList.get(1).getRecord().getScore()) System.out.print(" 무승부");
        System.out.println("\nThank you!");
    }

    public static EventType occur(float rand, Player player) {
        if(rand < player.getBatAve()) return EventType.HIT;
        else if(rand < (player.getBatAve() + player.getStrPer())) return EventType.STRIKE;
        else if(rand < (player.getBatAve() + player.getStrPer() + player.getBallPer())) return EventType.BALL;
        else return EventType.OUT;
    }

    public static Player findPlayer(int num, Team team) {
        for(Player player : team.getPlayerList()) {
            if(player.getNum() == num) return player;
        }
        return team.getPlayerList().get(0);
    }
}

class Team {
    private String name;
    private ArrayList<Player> playerList;
    private boolean firstAttackIs = false;
    private Record record;

    public void startText(int i) {
        if(this.firstAttackIs) System.out.println(i + "회초 " + this.name + " 공격\n");
        else System.out.println(i + "회말 " + this.name + " 공격\n");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(ArrayList<Player> playerList) {
        this.playerList = playerList;
    }

    public void setFirstAttackIs(boolean firstAttackIs) {
        this.firstAttackIs = firstAttackIs;
    }

    public Record getRecord() {
        return record;
    }

    public void setRecord(Record record) {
        this.record = record;
    }
}

class Player {
    private int num;
    private String name;
    private float batAve;
    private float strPer;
    private float ballPer;
    private float outPer;

    Player(int num, String name, float batAve) {
        this.num = num;
        this.name = name;
        this.batAve = batAve;
        this.strPer = (1 - batAve) / 2 - 0.05f;
        this.ballPer = (1 - batAve) / 2 - 0.05f;
        this.outPer = 0.1f;
    }

    public void infoDetail() {
        System.out.println(this.num + "번 " + this.name + ", " + this.batAve);
    }

    public void info() {
        System.out.println(this.num + "번 " + this.name);
    }

    public int getNum() {
        return num;
    }

    public float getBatAve() {
        return batAve;
    }

    public float getStrPer() {
        return strPer;
    }

    public float getBallPer() {
        return ballPer;
    }

    public float getOutPer() {
        return outPer;
    }
}

class Record {
    private int strike = 0;
    private int ball = 0;
    private int out = 0;
    private int hit = 0;
    private int batterNum = 1;
    private int score = 0;


    public void play(Team team) {
        Random random = new Random();

        BaseballGame.findPlayer(this.batterNum, team).info();

        while (true) {
            if(this.batterNum == 10) this.batterNum = 1;
            float rand = random.nextFloat();
            EventType type = BaseballGame.occur(rand, BaseballGame.findPlayer(this.batterNum, team));

            add(type, team);

            if(this.out == 3) break;
        }
    }

    public void add(EventType type, Team team) {


        switch (type) {
            case STRIKE:
                this.strike++;
                System.out.print("스트라이크!");
                if(this.strike == 3) {
                    System.out.println();
                    add(EventType.OUT, team);
                }
                else info();
                break;
            case BALL:
                this.ball++;
                System.out.print("볼!");
                if(this.ball == 4) {
                    System.out.println();
                    add(EventType.HIT, team);
                }
                else info();
                break;
            case OUT:
                this.out++;
                System.out.print("아웃!");
                if(this.out == 3) break;
                reset(team);
                break;
            case HIT:
                this.hit++;
                System.out.print("안타!");
                if(this.hit >= 4) this.score++;
                reset(team);
                break;
        }
    }

    public void reset(Team team) {
        this.strike = 0;
        this.ball = 0;
        this.batterNum++;
        if(this.batterNum == 10) this.batterNum = 1;
        info();
        BaseballGame.findPlayer(this.batterNum, team).info();
    }

    public void resetAll(Team team) {
        info();
        this.strike = 0;
        this.ball = 0;
        this.out = 0;
        this.hit = 0;
        this.batterNum = 1;
    }

    public void info() {
        System.out.println("\n" + strike + "S " + ball + "B " + out + "O\n");
    }

    public int getScore() {
        return score;
    }
}