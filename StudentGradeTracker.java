
import java.util.Scanner;

public class StudentGradeTracker {

    private static final String[] SUBJECTS = {
        "Java", "C Programming", "DBMS"
    };

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("========================================");
        System.out.println("       STUDENT GRADE TRACKER");
        System.out.println("========================================");

        int studentCount = readIntInRange(
                sc, "Enter number of students (1-100): ", 1, 100);

        String[] names = new String[studentCount];
        int[][] marks = new int[studentCount][SUBJECTS.length];
        int[] studentTotals = new int[studentCount];
        double[] studentAverages = new double[studentCount];

        // Input student names and subject-wise marks
        for (int i = 0; i < studentCount; i++) {

            System.out.println("\nStudent " + (i + 1));

            while (true) {
                System.out.print("Enter student name: ");
                names[i] = sc.nextLine().trim();

                if (!names[i].isEmpty()) {
                    break;
                }

                System.out.println("Name cannot be empty.");
            }

            for (int j = 0; j < SUBJECTS.length; j++) {
                System.out.println("Subject: " + SUBJECTS[j]);

                marks[i][j] = readIntInRange(
                        sc, "Enter marks (0-100): ", 0, 100);

                studentTotals[i] += marks[i][j];
            }

            studentAverages[i] =
                    (double) studentTotals[i] / SUBJECTS.length;
        }

        // Calculate overall highest and lowest subject marks
        int highest = marks[0][0];
        int lowest = marks[0][0];
        int overallTotal = 0;

        for (int i = 0; i < studentCount; i++) {
            for (int j = 0; j < SUBJECTS.length; j++) {

                overallTotal += marks[i][j];

                if (marks[i][j] > highest) {
                    highest = marks[i][j];
                }

                if (marks[i][j] < lowest) {
                    lowest = marks[i][j];
                }
            }
        }

        double overallAverage =
                (double) overallTotal
                        / (studentCount * SUBJECTS.length);

        // Display individual student reports
        System.out.println("\n========================================");
        System.out.println("       INDIVIDUAL STUDENT REPORTS");
        System.out.println("========================================");

        for (int i = 0; i < studentCount; i++) {

            System.out.println("\nStudent Name: " + names[i]);
            System.out.println("----------------------------------------");

            for (int j = 0; j < SUBJECTS.length; j++) {
                System.out.printf("%-18s : %3d%n",
                        SUBJECTS[j], marks[i][j]);
            }

            System.out.println("----------------------------------------");
            System.out.println("Total Marks : "
                    + studentTotals[i] + " / "
                    + (SUBJECTS.length * 100));

            System.out.printf("Average     : %.2f%n",
                    studentAverages[i]);

            System.out.println("Grade       : "
                    + calculateGrade(studentAverages[i]));
        }

        // Display overall class summary
        System.out.println("\n========================================");
        System.out.println("          CLASS SUMMARY");
        System.out.println("========================================");

        System.out.println("Total Students : " + studentCount);
        System.out.println("Total Subjects : " + SUBJECTS.length);
        System.out.println("Total Marks    : " + overallTotal);
        System.out.printf("Class Average  : %.2f%n", overallAverage);
        System.out.println("Highest Subject Mark : " + highest);
        System.out.println("Lowest Subject Mark  : " + lowest);

        System.out.println("========================================");
        System.out.println("Thank you for using Student Grade Tracker!");

        sc.close();
    }

    // Read and validate integer input
    private static int readIntInRange(
            Scanner sc, String prompt, int min, int max) {

        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine().trim();

            try {
                int value = Integer.parseInt(input);

                if (value >= min && value <= max) {
                    return value;
                }

                System.out.println(
                        "Enter a value between " + min + " and " + max + ".");

            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Enter a whole number.");
            }
        }
    }

    // Calculate grade from the student's average
    private static String calculateGrade(double average) {

        if (average >= 90) {
            return "A+";
        } else if (average >= 80) {
            return "A";
        } else if (average >= 70) {
            return "B";
        } else if (average >= 60) {
            return "C";
        } else if (average >= 40) {
            return "D";
        } else {
            return "F";
        }
    }
}
