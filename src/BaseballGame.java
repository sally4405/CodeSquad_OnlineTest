import java.util.*;

public class BaseballGame {
    public static void main(String[] args) {

        System.out.println("신나는 야구 게임!");
        System.out.println("첫 번째 타자가 타석에 입장했습니다.\n");

        int[] arr = new int[4];
        Random random = new Random();

        while(true) {
            int num = random.nextInt(4);

            choice(num, arr);

            if(arr[2] == 3) {
                System.out.println("최종 안타수: " + arr[3] + "\nGAME OVER");
                break;
            }

        }

    }

    private static void choice(int num, int[] arr) {

        arr[num]++;

        switch(num) {
            case 0: System.out.print("스트라이크!");
                break;
            case 1: System.out.print("볼!");
                break;
            case 2:
                if(arr[2] == 3) System.out.print("아웃!");
                else {
                    System.out.print("아웃!");
                    next(arr);
                }
                break;
            case 3:
                System.out.print("안타!");
                next(arr);
                break;
        }
        check(arr);

        System.out.println("\n" + arr[0] + "S " + arr[1] + "B " + arr[2] + "O\n");
    }

    private static void check(int[] arr) {
        if(arr[0] == 3) {
            arr[2]++;
            System.out.print("\n아웃!");
            if(arr[2] != 3) next(arr);
        }
        if(arr[1] == 4) {
            arr[3]++;
            System.out.print("\n안타!");
            next(arr);
        }

    }

    private static void next(int[] arr) {
        arr[0] = 0;
        arr[1] = 0;
        System.out.print(" 다음 타자가 타석에 입장했습니다.");
    }

}