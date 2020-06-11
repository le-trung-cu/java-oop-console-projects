package app;

import java.util.Scanner;

public class LuckyNumber {
    static Scanner scan;
    static String userContinue;
    static final int MAXIMUM = 100;
    static int totalGame = 0, totalGuesses = 0, bestGame = -1;

    public static void main(String[] args) throws Exception {
        scan = new Scanner(System.in);

        do {
            play();

            System.out.print("bạn có muốn tiếp tục chơi không? ");
            userContinue = scan.next().toUpperCase();
            System.out.println();
            
        } while (userContinue.equals("Y") || userContinue.equals("YES"));

        scan.close();
    }

    private static void play() {
        totalGame++;
        int userAnswer, countAnswer = 0;
        long luckyNumber = Math.round(Math.random() * MAXIMUM);
        // System.out.println(luckyNumber);
        do {
            countAnswer++;
            totalGuesses++;
            System.out.print("câu trả lời của bạn là: ");
            userAnswer = scan.nextInt();
            if (userAnswer < luckyNumber) {
                System.out.println("số may mắn lớn hơn số dự đoán của bạn.");
            } else if (userAnswer > luckyNumber) {
                System.out.println("số may mắn nhỏ hơn số dự đoán của bạn.");
            } else {
                System.out.println("chúc mừng bạn đã đoán đúng con số may mắn sau " + countAnswer + " lần dự đoán!");
                bestGame = bestGame == -1 ? countAnswer : bestGame > countAnswer ? countAnswer : bestGame;
                break;
            }
        } while (true);
    }

    public static void report() {
        System.out.println("tổng số lần chơi: " + totalGame);
        System.out.println("tổng số lần dự đoán: " + totalGuesses);
        double avgGuessesPerGame = Math.round(10.0 * totalGuesses / totalGame) / 10.0;
        System.out.println("số dự đoán trung bình mỗi lượt: " + avgGuessesPerGame);
        System.out.println("best game: " + bestGame);
    }
}
