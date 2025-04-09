import java.util.Scanner;

/**
 * CourseGradeCalculator.java
 *
 * This program calculates a student's final course grade based on weighted averages
 * from homework assignments and two exams. It accounts for extra credit, late days,
 * and lab attendance, ensuring that all scores are capped at their maximum values.
 *
 * Author: [Your Name]
 * Date: [Current Date]
 */
public class CourseGradeCalculator {
    public static void main(String[] args) {
        // Simulating user input as a single string (replace with actual line input if needed)
        String input = "50\n20\n10\n8.45\n2\n4\n81\n0\n95\n10";  // Example input string
        Scanner in = new Scanner(input);  // Using line input

        // Welcome message
        System.out.println("This program accepts your homework and two exam scores as input and computes your grade in the course.");

        // Input weights for homework and exams
        System.out.print("Homework weight? ");
        double homeworkWeight = Double.parseDouble(in.nextLine());

        System.out.print("Exam 1 weight? ");
        double exam1Weight = Double.parseDouble(in.nextLine());

        // Calculate weight for Exam 2
        double exam2Weight = 100 - (homeworkWeight + exam1Weight);
        System.out.printf("Using weights of %.0f %.0f %.0f\n", homeworkWeight, exam1Weight, exam2Weight);

        // Homework calculations
        System.out.println("\nHomework:");
        System.out.print("Number of assignments? ");
        int numAssignments = Integer.parseInt(in.nextLine());
        if (numAssignments <= 0) {
            numAssignments = 0; // No assignments means full credit for homework
        }

        System.out.print("Average Homework grade? ");
        double avgHomeworkScore = Math.max(Double.parseDouble(in.nextLine()), 0);
        avgHomeworkScore = Math.min(avgHomeworkScore, 10); // Cap at 10

        System.out.print("Number of late days used? ");
        int lateDays = Integer.parseInt(in.nextLine());

        System.out.print("Labs attended? ");
        int numLabsAttended = Integer.parseInt(in.nextLine());
        int labPoints = numLabsAttended * 4; // Each lab is worth 4 points

        // Calculate total homework points
        double homeworkPoints = 0;
        if (numAssignments > 0) {
            homeworkPoints = avgHomeworkScore * numAssignments + labPoints;

            // Handle late days and extra credit
            if (lateDays > (numAssignments / 2)) {
                homeworkPoints *= 0.90; // Reduce by 10% for excessive late days
            } else if (lateDays == 0 && avgHomeworkScore < 10) {
                homeworkPoints += 5; // Add 5 extra points, capped at max
            }

            homeworkPoints = Math.min(homeworkPoints, numAssignments * 10 + labPoints); // Cap at max homework points
        }

        double totalHomeworkPoints = homeworkPoints;
        double totalHomeworkPossible = numAssignments * 10 + labPoints;

        double homeworkWeightedScore = (totalHomeworkPoints / totalHomeworkPossible) * (homeworkWeight / 100.0);

        System.out.printf("Total points = %.1f / %.1f\n", totalHomeworkPoints, totalHomeworkPossible);
        System.out.printf("Weighted score = %.2f\n", homeworkWeightedScore * 100); // Display percentage

        // Exam 1 calculations
        System.out.println("\nExam 1:");
        System.out.print("Score? ");
        double exam1Score = Math.max(Double.parseDouble(in.nextLine()), 0);
        exam1Score = Math.min(exam1Score, 100); // Cap at 100

        System.out.print("Curve? ");
        double exam1Curve = Double.parseDouble(in.nextLine());

        double totalExam1Points = exam1Score + exam1Curve;
        totalExam1Points = Math.min(totalExam1Points, 100); // Cap at 100
        double exam1WeightedScore = (totalExam1Points / 100.0) * (exam1Weight / 100.0);

        System.out.printf("Total points = %.1f / 100\n", totalExam1Points);
        System.out.printf("Weighted score = %.1f\n", exam1WeightedScore * 100); // Display percentage

        // Exam 2 calculations
        System.out.println("\nExam 2:");
        System.out.print("Score? ");
        double exam2Score = Math.max(Double.parseDouble(in.nextLine()), 0);
        exam2Score = Math.min(exam2Score, 100); // Cap at 100

        System.out.print("Curve? ");
        double exam2Curve = Double.parseDouble(in.nextLine());

        double totalExam2Points = exam2Score + exam2Curve;
        totalExam2Points = Math.min(totalExam2Points, 100); // Cap at 100
        double exam2WeightedScore = (totalExam2Points / 100.0) * (exam2Weight / 100.0);

        System.out.printf("Total points = %.1f / 100\n", totalExam2Points);
        System.out.printf("Weighted score = %.1f\n", exam2WeightedScore * 100); // Display percentage

        // Final course grade calculation
        double finalGrade = (homeworkWeightedScore + exam1WeightedScore + exam2WeightedScore) * 100;
        System.out.printf("\nCourse grade = %.2f\n", finalGrade);

        in.close();  // Close the scanner
    }
}
