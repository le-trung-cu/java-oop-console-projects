package app;

import java.util.Scanner;

public class GradeStudent {
    final int ATTEND_POINT_MAX = 30; // Điểm tối đa của phần Attend là 30, nếu vượt quá 30 thì vẫn chỉ tính là 30.
    final int ATTEND_POINT_ONE = 5; // với mỗi buổi được điểm danh, thì sinh viên sẽ được tính 5 điểm
    final int ASSM_POINT_MAX = 150; //Điểm tối đa của phần Assignment là 150, nếu vượt quá thì cũng chỉ tính là 150 điểm.
    final int EXAMINE_SCORE_MAX = 100; // Điểm tối đa 1 bài thi


    int weightMidterm; // trọng số của điểm thi giữ kỳ
    int scoreEarnedMidterm; // điểm thi giữ kỳ
    boolean wereScoreShiftedMidterm; // điểm thi giữi kỳ bạn có được tăng không
    int shiftAmountMidterm;

    int weightFinal; // trọng số của điểm thi cuối kỳ
    int scoreEarnedFinal; // điểm thi cuối kỳ
    boolean wereScoreShiftedFinal; // là điểm thi cuối kỳ bạn có được tăng không
    int shiftAmountFinal;

    int weightHome; // trọng số của điểm bài tập về nhà
    int totalAssmMax; // tổng điểm Assignment max 
    int totalAssmScore; // tổng điểm Assignment
    int sectionAttend; //số lượng buổi học sinh viên đã đi học và được điểm danh

    Scanner scan = new Scanner(System.in);

    GradeStudent(Scanner scanner) {
        scan = scanner;
    }

    /**
     * Total points: là tổng số điểm, bao gồm điểm ban đầu và số điểm được tăng
     * thêm, điểm tối đa của total point là 100
     */
    public int getTotalPointMidterm() {
        int score = scoreEarnedMidterm + shiftAmountMidterm;
        if (score > 100) {
            score = 100;
        }
        return score;
    }

    /**
     * Total points: là tổng số điểm, bao gồm điểm ban đầu và số điểm được tăng
     * thêm, điểm tối đa của total point là 100
     */
    public int getTotalPointFinal() {
        int score = scoreEarnedFinal + shiftAmountFinal;
        if (score > 100) {
            score = 100;
        }
        return score;
    }

    /**
     * Điểm tối đa của phần Attend là 30, nếu vượt quá 30 thì vẫn chỉ tính là 30
     */
    public int getAttendPoint() {
        int sectionPoint = sectionAttend * ATTEND_POINT_ONE;
        if (sectionPoint > ATTEND_POINT_MAX) {
            sectionPoint = ATTEND_POINT_MAX;
        }
        return sectionPoint;
    }

    public static void main(String[] args) throws Exception {
        System.out.println("This program read axam/homework scores");
        System.out.println("and reports your overall course grade");
        Scanner scan = new Scanner(System.in);
        GradeStudent gradeStudent = new GradeStudent(scan);

        gradeStudent.begin();
    }

    public void begin() throws Exception {
        Midterm();
        Final();
        homework();

        int sumWeight = weightMidterm + weightFinal + weightHome;
        if (sumWeight != 100) {
            // System.out.println("total of 3 weight must be equal to 100");
            throw new Exception("total of 3 weight must be equal to 100");
        }
        report();
    }

    public void Midterm() {
        System.out.println("Midterm:");
        weightMidterm = inputWeight();
        scoreEarnedMidterm = inputScoreEarned();

        wereScoreShiftedMidterm = inputWereScoreShifted();
        if (wereScoreShiftedMidterm) {
            shiftAmountMidterm = inputShiftAmount();
        }
        showTotalPoint("midterm");
        showWeightedScore("midterm");
        System.out.println();
    }

    public void Final() {
        System.out.println("Final:");
        weightFinal = inputWeight();
        scoreEarnedFinal = inputScoreEarned();
        wereScoreShiftedFinal = inputWereScoreShifted();
        if (wereScoreShiftedFinal) {
            shiftAmountFinal = inputShiftAmount();
        }
        showTotalPoint("final");
        showWeightedScore("final");
        System.out.println();
    }

    public void homework() {
        System.out.println("Homework:");
        weightHome = inputWeight();
        inputAssignment();
        sectionAttend = inputSectionAttend();
        showSectionPoint();
        showTotalPoint("homework");
        showWeightedScore("homework");
        ////
    }

    public void report() {
        System.out.println();
        System.out.println("----------------------------");
        double percentage = getPercebtage();
        double grade;
        if (percentage >= 85) {
            grade = 3.0;
        } else if (percentage >= 75 && percentage < 85) {
            grade = 2.0;
        } else if (percentage >= 60 && percentage < 75) {
            grade = 0.7;
        } else {
            grade = 0.0;
        }
        System.out.println("Overal percentage = " + percentage);
        System.out.println("Your grade will be at least: " + grade);

    }

    private int inputWeight() {
        System.out.print("Weight (0-100)? ");
        return scan.nextInt();
    }

    private int inputScoreEarned() {
        System.out.print("Score earned? ");
        return scan.nextInt();
    }

    private boolean inputWereScoreShifted() {
        int in;
        do {
            System.out.print("Were scores shifted (1=yes, 2=no)? ");
            in = scan.nextInt();
        } while (!(in == 1 || in == 2));
        return in == 1;
    }

    private int inputShiftAmount() {
        System.out.print("Shift amount? ");
        return scan.nextInt();
    }

    private void inputAssignment() {
        System.out.print("Number of assignments? ");
        int assmNumber = scan.nextInt();
        for (int i = 0; i < assmNumber;) {
            System.out.print("Assignment " + (++i) + "score and max? ");
            double score = scan.nextDouble();
            double max = scan.nextDouble();
            totalAssmScore += score;
            totalAssmMax += max;
        }
        if (totalAssmMax > ASSM_POINT_MAX) {
            totalAssmMax = ASSM_POINT_MAX;
        }
        if (totalAssmScore > ASSM_POINT_MAX) {
            totalAssmScore = ASSM_POINT_MAX;
        }

    }

    private int inputSectionAttend() {
        System.out.print("How many sections did you attend? ");
        return scan.nextInt();
    }

    private void showTotalPoint(String totalPointName) {
        int totalPoint = 0;
        int maxScore = 0;
        switch (totalPointName) {
        case "midterm":
            totalPoint = getTotalPointMidterm();
            maxScore = EXAMINE_SCORE_MAX;
            break;
        case "final":
            totalPoint = getTotalPointFinal();
            maxScore = EXAMINE_SCORE_MAX;
            break;
        case "homework":
            totalPoint = totalAssmScore + getAttendPoint();
            maxScore = totalAssmMax + ATTEND_POINT_MAX;
            break;
        }

        System.out.println("Total points = " + totalPoint + " / " + maxScore);
    }

    private void showWeightedScore(String weightName) {
        double score = 0;
        int weight = 0;
        switch (weightName) {
        case "midterm":
            score = (double) getTotalPointMidterm() * weightMidterm / EXAMINE_SCORE_MAX;
            weight = weightMidterm;
            break;
        case "final":
            score = (double) getTotalPointFinal() * weightFinal / EXAMINE_SCORE_MAX;
            weight = weightFinal;
            break;
        case "homework":
            score = (double) weightHome * (totalAssmScore + getAttendPoint()) / (totalAssmMax + ATTEND_POINT_MAX);
            weight = weightHome;
            break;
        default:
            break;

        }
        score = round(score);
        System.out.println("Weighted score = " + score + " / " + weight);

    }

    private void showSectionPoint() {

        System.out.println("Section points = " + getAttendPoint() + "/" + ATTEND_POINT_MAX);
    }

    private double getPercebtage() {
        double weightedMidtermScore = (double) getTotalPointMidterm() * weightMidterm / EXAMINE_SCORE_MAX;
        double weightedFinalScore = (double) getTotalPointFinal() * weightFinal / EXAMINE_SCORE_MAX;
        double weightedHomeworkScore = (double) (totalAssmScore + getAttendPoint()) * weightHome
                / (totalAssmMax + ATTEND_POINT_MAX);
        double sum = weightedMidtermScore + weightedFinalScore + weightedHomeworkScore;

        return round(sum);
    }

    /**
     * làm tròn tới 1 chữ số đằng sau dấu thập phân. 
     */
    public static double round(double num) {
        num = Math.round(num * 10) / 10.0;
        return num;
    }

}