package Virtual_Classroom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class ClassroomManager {
    private static ClassroomManager instance;
    private Map<String, Classroom> classrooms;

    private ClassroomManager() {
        classrooms = new HashMap<>();
    }

    public static ClassroomManager getInstance() {
        if (instance == null) {
            instance = new ClassroomManager();
        }
        return instance;
    }

    public void addClassroom(String name) {
        if (!classrooms.containsKey(name)) {
            classrooms.put(name, new Classroom(name));
            System.out.println("Classroom " + name + " has been created.");
        } else {
            System.out.println("Classroom " + name + " already exists.");
        }
    }

    public void addStudent(String classroomName, String studentID) {
        Classroom classroom = classrooms.get(classroomName);
        if (classroom != null) {
            classroom.addStudent(studentID);
        } else {
            System.out.println("Classroom " + classroomName + " does not exist.");
        }
    }

    public void scheduleAssignment(String classroomName, String assignmentDetails) {
        Classroom classroom = classrooms.get(classroomName);
        if (classroom != null) {
            classroom.scheduleAssignment(assignmentDetails);
        } else {
            System.out.println("Classroom " + classroomName + " does not exist.");
        }
    }

    public void submitAssignment(String classroomName, String studentID, String assignmentDetails) {
        Classroom classroom = classrooms.get(classroomName);
        if (classroom != null) {
            classroom.submitAssignment(studentID, assignmentDetails);
        } else {
            System.out.println("Classroom " + classroomName + " does not exist.");
        }
    }
}

class Classroom {
    private String name;
    private List<String> students;
    private List<String> assignments;
    private Map<String, List<String>> submissions;

    public Classroom(String name) {
        this.name = name;
        this.students = new ArrayList<>();
        this.assignments = new ArrayList<>();
        this.submissions = new HashMap<>();
    }

    public void addStudent(String studentID) {
        if (!students.contains(studentID)) {
            students.add(studentID);
            System.out.println("Student " + studentID + " has been enrolled in " + name + ".");
        } else {
            System.out.println("Student " + studentID + " is already enrolled.");
        }
    }

    public void scheduleAssignment(String assignmentDetails) {
        assignments.add(assignmentDetails);
        System.out.println("Assignment '" + assignmentDetails + "' has been scheduled for " + name + ".");
    }

    public void submitAssignment(String studentID, String assignmentDetails) {
        if (students.contains(studentID)) {
            submissions.putIfAbsent(studentID, new ArrayList<>());
            submissions.get(studentID).add(assignmentDetails);
            System.out.println("Assignment '" + assignmentDetails + "' submitted by student " + studentID + " in " + name + ".");
        } else {
            System.out.println("Student " + studentID + " is not enrolled in this classroom.");
        }
    }
}

public class VirtualClassroomManagerApp {
    public static void main(String[] args) {
        ClassroomManager manager = ClassroomManager.getInstance();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\nVIRTUAL CLASSROOM MANAGER ===");
            System.out.println("1. Add Classroom");
            System.out.println("2. Add Student");
            System.out.println("3. Schedule Assignment");
            System.out.println("4. Submit Assignment");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  

            switch (choice) {
                case 1:
                    System.out.print("Enter classroom name: ");
                    String classroomName = scanner.nextLine();
                    manager.addClassroom(classroomName);
                    break;

                case 2:
                    System.out.print("Enter classroom name: ");
                    classroomName = scanner.nextLine();
                    System.out.print("Enter student ID: ");
                    String studentID = scanner.nextLine();
                    manager.addStudent(classroomName, studentID);
                    break;

                case 3:
                    System.out.print("Enter classroom name: ");
                    classroomName = scanner.nextLine();
                    System.out.print("Enter assignment details: ");
                    String assignmentDetails = scanner.nextLine();
                    manager.scheduleAssignment(classroomName, assignmentDetails);
                    break;

                case 4:
                    System.out.print("Enter classroom name: ");
                    classroomName = scanner.nextLine();
                    System.out.print("Enter student ID: ");
                    studentID = scanner.nextLine();
                    System.out.print("Enter assignment details: ");
                    assignmentDetails = scanner.nextLine();
                    manager.submitAssignment(classroomName, studentID, assignmentDetails);
                    break;

                case 5:
                    exit = true;
                    System.out.println("Exiting Virtual Classroom Manager.");
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }
}
