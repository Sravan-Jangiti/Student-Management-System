public class Student extends Person {
    private int id;
    private String course;
    private double marks;

    public Student(int id, String name, int age, String course, double marks) {
        super(name, age);
        this.id = id;
        this.course = course;
        this.marks = marks;
    }

    public int getId() {
        return id;
    }

    public String toFileLine() {
        return id + "," + name + "," + age + "," + course + "," + marks;
    }

    // Method overriding
    @Override
    public void displayDetails() {
        System.out.println("ID: " + id);
        super.displayDetails();
        System.out.println("Course: " + course);
        System.out.println("Marks: " + marks);
        System.out.println("-------------------------");
    }

    public static Student fromFileLine(String line) {
        String[] parts = line.split(",", -1);
        if (parts.length != 5) {
            throw new IllegalArgumentException("Invalid student record: " + line);
        }

        return new Student(
            Integer.parseInt(parts[0]),
            parts[1],
            Integer.parseInt(parts[2]),
            parts[3],
            Double.parseDouble(parts[4])
        );
    }
}
