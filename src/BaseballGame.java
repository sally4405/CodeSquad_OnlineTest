import java.util.*;

public class BaseballGame {
    public static void main(String[] args) {

        System.out.println("신나는 야구 게임!");
        System.out.println("첫 번째 타자가 타석에 입장했습니다.\n");

        Record re = new Record();
        Random random = new Random();

        while(true) {
            int rand = random.nextInt(4);
            EventType type = occur(rand);

            re.add(type);
            re.info();

            if(re.getOut() == 3) {
                System.out.println("최종 안타수: " + re.getHit() + "\nGAME OVER");
                break;
            }
        }
    }

    public static EventType occur(int rand) {
        if(rand == 0) return EventType.STRIKE;
        else if(rand == 1) return EventType.BALL;
        else if(rand == 2) return EventType.OUT;
        else return EventType.HIT;
    }
}

class Record {
    private int strike = 0;
    private int ball = 0;
    private int out = 0;
    private int hit = 0;

    public void add(EventType type) {
        switch (type) {
            case STRIKE:
                this.strike++;
                System.out.print("스트라이크!");
                if(this.strike == 3) {
                    System.out.println();
                    add(EventType.OUT);
                }
                break;
            case BALL:
                this.ball++;
                System.out.print("볼!");
                if(this.ball == 4) {
                    System.out.println();
                    add(EventType.HIT);
                }
                break;
            case OUT:
                this.out++;
                System.out.print("아웃!");
                if(this.out == 3) break;
                reset();
                break;
            case HIT:
                this.hit++;
                System.out.print("안타!");
                reset();
                break;
        }
    }

    public void reset() {
        this.strike = 0;
        this.ball = 0;
        System.out.print(" 다음 타자가 타석에 입장했습니다. ");
    }

    public void info() {
        System.out.println("\n" + strike + "S " + ball + "B " + out + "O\n");
    }

    public int getStrike() {
        return strike;
    }

    public int getBall() {
        return ball;
    }

    public int getOut() {
        return out;
    }

    public int getHit() {
        return hit;
    }
}