
import java.util.Scanner;

public class StudentGradeTracker {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("==================================");
        System.out.println("      STUDENT GRADE TRACKER");
        System.out.println("==================================");

        int studentCount = readIntInRange(
                sc, "Enter number of students (1-100): ", 1, 100);

        String[] names = new String[studentCount];
        int[] marks = new int[studentCount];

        // Input student names and marks
        for (int i = 0; i < studentCount; i++) {

            System.out.println("\nStudent " + (i + 1));

            while (true) {
                System.out.print("Enter student name: ");
                names[i] = sc.nextLine().trim();

                if (!names[i].isEmpty()) {
                    break;
                }

                System.out.println("Name cannot be empty. Try again.");
            }

            marks[i] = readIntInRange(
                    sc, "Enter marks (0-100): ", 0, 100);
        }

        // Calculate total, highest and lowest marks
        int totalMarks = 0;
        int highest = marks[0];
        int lowest = marks[0];

        for (int i = 0; i < studentCount; i++) {

            totalMarks += marks[i];

            if (marks[i] > highest) {
                highest = marks[i];
            }

            if (marks[i] < lowest) {
                lowest = marks[i];
            }
        }

        double average = (double) totalMarks / studentCount;

        // Display report for all students
        System.out.println("\n==================================");
        System.out.println("         STUDENT REPORT");
        System.out.println("==================================");

        System.out.printf("%-20s %-10s %-10s%n",
                "Student Name", "Marks", "Grade");

        System.out.println("----------------------------------");

        for (int i = 0; i < studentCount; i++) {
            System.out.printf("%-20s %-10d %-10s%n",
                    names[i], marks[i], calculateGrade(marks[i]));
        }

        // Display overall summary
        System.out.println("\n==================================");
        System.out.println("         SUMMARY REPORT");
        System.out.println("==================================");

        System.out.println("Total Students : " + studentCount);
        System.out.println("Total Marks    : " + totalMarks);
        System.out.printf("Average Marks  : %.2f%n", average);
        System.out.println("Highest Marks  : " + highest);
        System.out.println("Lowest Marks   : " + lowest);

        System.out.println("==================================");
        System.out.println("   Thank you for using the app!");
        System.out.println("==================================");

        sc.close();
    }

    // Accept only valid integer input within a range
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
                        "Please enter a value between "
                                + min + " and " + max + ".");

            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    // Assign a grade based on marks
    private static String calculateGrade(int marks) {

        if (marks >= 90) {
            return "A+";
        } else if (marks >= 80) {
            return "A";
        } else if (marks >= 70) {
            return "B";
        } else if (marks >= 60) {
            return "C";
        } else if (marks >= 40) {
            return "D";
        } else {
            return "F";
        }
    }
}
