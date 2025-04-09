import java.util.Scanner;

public class CourseGradeCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read user inputs
        System.out.print("Enter the number of assignments: ");
        int numAssignments = scanner.nextInt();

        System.out.print("Enter the average homework score (out of 10): ");
        double avgHomeworkScore = scanner.nextDouble();

        System.out.print("Enter the number of labs attended: ");
        int labsAttended = scanner.nextInt();

        System.out.print("Enter the exam 1 score (out of 100): ");
        double exam1Score = scanner.nextDouble();

        System.out.print("Enter the exam 2 score (out of 100): ");
        double exam2Score = scanner.nextDouble();

        System.out.print("Enter the weight for homework (out of 100): ");
        double homeworkWeight = scanner.nextDouble();

        System.out.print("Enter the weight for exam 1 (out of 100): ");
        double exam1Weight = scanner.nextDouble();

        // Calculate exam 2 weight
        double exam2Weight = 100 - homeworkWeight - exam1Weight;

        // Adjust invalid inputs
        if (numAssignments <= 0) {
            numAssignments = 10; // Assume default number of assignments if invalid
        }
        if (avgHomeworkScore < 0) {
            avgHomeworkScore = 0;
        } else if (avgHomeworkScore > 10) {
            avgHomeworkScore = 10;
        }
        if (labsAttended < 0) {
            labsAttended = 0;
        }
        if (exam1Score < 0) {
            exam1Score = 0;
        } else if (exam1Score > 100) {
            exam1Score = 100;
        }
        if (exam2Score < 0) {
            exam2Score = 0;
        } else if (exam2Score > 100) {
            exam2Score = 100;
        }

        // Homework calculations
        final double maxHomeworkPoints = 10 * numAssignments;
        final double maxLabPoints = 4 * numAssignments;
        double homeworkPoints = avgHomeworkScore * numAssignments + labsAttended * 4;

        if (labsAttended <= numAssignments / 2) {
            // Apply extra credit if no late days
            homeworkPoints = Math.min(homeworkPoints + 5, maxHomeworkPoints);
        } else if (labsAttended > numAssignments / 2) {
            // Reduce homework points by 10% if too many late days
            homeworkPoints = Math.max(homeworkPoints * 0.9, 0);
        }

        // Exam scores after curve and cap
        double curvedExam2Score = Math.min(exam2Score + 10, 100);

        // Calculate weighted scores
        double weightedHomeworkScore = (homeworkWeight / 100) * (homeworkPoints / (maxHomeworkPoints + maxLabPoints));
        double weightedExam1Score = (exam1Weight / 100) * (exam1Score / 100);
        double weightedExam2Score = (exam2Weight / 100) * (curvedExam2Score / 100);

        // Calculate final grade
        double finalGrade = weightedHomeworkScore + weightedExam1Score + weightedExam2Score;

        // Display results
        System.out.printf("Weighted Homework Score: %.2f\n", weightedHomeworkScore * 100);
        System.out.printf("Weighted Exam 1 Score: %.2f\n", weightedExam1Score * 100);
        System.out.printf("Weighted Exam 2 Score: %.2f\n", weightedExam2Score * 100);
        System.out.printf("Final Course Grade: %.2f\n", finalGrade * 100);

        scanner.close();
    }
}


