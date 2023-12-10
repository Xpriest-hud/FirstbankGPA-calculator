package GPAcalculator;

import java.util.InputMismatchException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class GPACalculator {

    public static void runGPAProgram() {
        Scanner scanner = new Scanner(System.in);
        List<Course> courses = new ArrayList<>();

        System.out.println("Welcome to the GPA Calculator!");

        while (true) {
            Course course = new Course();
            try {

                System.out.print("Enter course code(example- MTS101): ");
                course.setName(scanner.nextLine());

                System.out.print("Enter course units: ");
                course.setUnits(scanner.nextInt());

                System.out.print("Enter course score: ");
                double courseScore = scanner.nextDouble();
                course.setGrade(determineGrade(courseScore));
                scanner.nextLine();

                // This is to add the course to the list
                courses.add(course);

                System.out.print("Would you like to enter another course? (Yes/No): ");
                String continueInput = scanner.nextLine().toLowerCase();

                if (!continueInput.equals("yes")) {
                    break; // This part exits the loop if the user does not want to enter more courses
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid value.");
                scanner.nextLine();
            }
        }



        GPACalculator calculateGPA = new GPACalculator();
        try {

            // Display recorded course information in a well-divided table
            calculateGPA.displayCourseTable(courses);


            double gpa = calculateGPA.calculateGPA(courses);
            calculateGPA.displayGPAInfo(courses, gpa);
        }

        catch(ArithmeticException e){
                System.out.println("Error calculating GPA: " + e.getMessage());
            } catch(Exception e){
                System.out.println("An unexpected error occurred: " + e.getMessage());
            } finally{
                scanner.close();
            }
    }
    private double calculateGPA(List<Course> courses) {
        int totalGradeUnit = calculateTotalUnits(courses);
        double totalQualityPoints = 0.0;

        for (Course course : courses) {
            totalQualityPoints += (course.getScore() * course.getUnits());
        }

        if (totalGradeUnit == 0) {
            return 0.0;
        }
        return totalQualityPoints / totalGradeUnit;
    }

    private int calculateTotalUnits(List<Course> courses) {
        int totalUnits = 0;
        for (Course course : courses) {
            totalUnits += course.getUnits();
        }
        return totalUnits;
    }

    private static String determineGrade(double score) {
        if (score >= 70 && score <= 100) {
            return "A";
        } else if (score >= 60 && score < 70) {
            return "B";
        } else if (score >= 50 && score < 60) {
            return "C";
        } else if (score >= 45 && score < 50) {
            return "D";
        } else if (score >= 40 && score < 45) {
            return "E";
        } else if (score >= 0 && score < 40) {
            return "Fail";
        } else {
            return "Invalid Score";
        }
    }

    // Code to display recorded course information in a well-divided table
    private void displayCourseTable(List<Course> courses) {
        System.out.println("\nRecorded Courses:");
        System.out.println("|----------------------|---------------|---------|---------------|");
        System.out.println("| COURSE & CODE        | COURSE UNIT   | GRADE   | GRADE UNIT    |");
        System.out.println("|----------------------|---------------|---------|---------------|");

        for (Course course : courses) {
            System.out.printf("|  %-20s| %-13d |   %-7s| %-13d|  \n",
                    course.getName(),
                    course.getUnits(),
                    course.getGrade(),
                    course.calculateGradeUnits());
        }

        System.out.println("|----------------------|---------------|---------|---------------|");
    }

    // Display GPA information
    private void displayGPAInfo(List<Course> courses, double gpa) {
        System.out.println("\nGPA Information:");
        System.out.printf("Your GPA is =: %.2f to 2 decimal places\n", gpa);
    }
}

