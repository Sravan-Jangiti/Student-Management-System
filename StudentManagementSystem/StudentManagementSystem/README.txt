STUDENT MANAGEMENT SYSTEM - CORE JAVA MINI PROJECT

PROJECT STRUCTURE
StudentManagementSystem/
|-- src/
|   |-- Main.java
|   |-- Person.java
|   |-- Student.java
|   |-- Searchable.java
|   |-- StudentManager.java
|-- data/
|   |-- students.txt (created automatically when records are saved)
|-- README.txt

JAVA CONCEPTS DEMONSTRATED
1. Inheritance: Student extends Person.
2. Method overriding: Student overrides displayDetails().
3. Interface: StudentManager implements Searchable.
4. Collections: ArrayList<Student> stores student objects.
5. Multithreading: A separate thread performs the save operation.
6. Exception handling: NumberFormatException, IOException and invalid-record handling.
7. Java NIO: Path, Paths, Files.readAllLines(), Files.write() and Files.createDirectories().

HOW TO RUN
1. Open a terminal in the StudentManagementSystem folder.
2. Compile:
   javac -d out src/*.java
3. Run:
   java -cp out Main

The data/students.txt file is created automatically when records are saved.
Student names and course names should not contain commas because the project uses
a simple comma-separated text file for storage.
