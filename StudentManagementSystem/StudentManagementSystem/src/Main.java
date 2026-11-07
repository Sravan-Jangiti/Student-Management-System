import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentManager manager = new StudentManager();

        manager.loadRecords();

        while (true) {
            System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
            System.out.println("1. Add student");
            System.out.println("2. Display all students");
            System.out.println("3. Search student by ID");
            System.out.println("4. Save records (using a thread)");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());

                switch (choice) {
                    case 1:
                        addStudent(scanner, manager);
                        break;
                    case 2:
                        manager.displayStudents();
                        break;
                    case 3:
                        System.out.print("Enter student ID: ");
                        int id = Integer.parseInt(scanner.nextLine().trim());
                        Student found = manager.searchById(id);
                        if (found == null) {
                            System.out.println("Student not found.");
                        } else {
                            found.displayDetails();
                        }
                        break;
                    case 4:
                        manager.saveRecordsInBackground();
                        break;
                    case 5:
                        manager.saveRecords();
                        System.out.println("Thank you for using the application.");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Please select a choice from 1 to 5.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Please enter a valid number.");
            }
        }
    }

    private static void addStudent(Scanner scanner, StudentManager manager) {
        try {
            System.out.print("Enter student ID: ");
            int id = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Enter name (do not use commas): ");
            String name = scanner.nextLine().trim();

            System.out.print("Enter age: ");
            int age = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Enter course (do not use commas): ");
            String course = scanner.nextLine().trim();

            System.out.print("Enter marks: ");
            double marks = Double.parseDouble(scanner.nextLine().trim());

            if (name.isEmpty() || course.isEmpty() || age <= 0 || marks < 0 || marks > 100) {
                System.out.println("Please enter valid details. Age must be positive and marks 0-100.");
                return;
            }

            manager.addStudent(new Student(id, name, age, course, marks));
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. ID, age and marks must be numbers.");
        }
    }
}
