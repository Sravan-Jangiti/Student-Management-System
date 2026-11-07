import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class StudentManager implements Searchable {
    private final ArrayList<Student> students = new ArrayList<>();
    private final Path filePath = Paths.get("data", "students.txt");

    public void addStudent(Student student) {
        if (searchById(student.getId()) != null) {
            System.out.println("A student with this ID already exists.");
            return;
        }

        students.add(student);
        System.out.println("Student added successfully.");
    }

    public void displayStudents() {
        if (students.isEmpty()) {
            System.out.println("No student records found.");
            return;
        }

        for (Student student : students) {
            student.displayDetails();
        }
    }

    @Override
    public Student searchById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    // Java NIO: write all student records to a file.
    public void saveRecords() {
        try {
            Files.createDirectories(filePath.getParent());

            List<String> lines = new ArrayList<>();
            for (Student student : students) {
                lines.add(student.toFileLine());
            }

            Files.write(filePath, lines, StandardCharsets.UTF_8);
            System.out.println("Records saved to " + filePath);
        } catch (IOException e) {
            System.out.println("Could not save records: " + e.getMessage());
        }
    }

    // Java NIO: read student records from a file.
    public void loadRecords() {
        if (!Files.exists(filePath)) {
            return;
        }

        try {
            List<String> lines = Files.readAllLines(filePath, StandardCharsets.UTF_8);
            for (String line : lines) {
                if (!line.trim().isEmpty()) {
                    try {
                        Student student = Student.fromFileLine(line);
                        if (searchById(student.getId()) == null) {
                            students.add(student);
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Skipping invalid record: " + e.getMessage());
                    }
                }
            }
            System.out.println("Student records loaded.");
        } catch (IOException e) {
            System.out.println("Could not load records: " + e.getMessage());
        }
    }

    // Multithreading: save records on a separate thread.
    public void saveRecordsInBackground() {
        Thread saveThread = new Thread(this::saveRecords, "StudentSaveThread");
        saveThread.start();

        try {
            saveThread.join(); // Wait so the application does not exit before saving finishes.
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Saving was interrupted.");
        }
    }
}
