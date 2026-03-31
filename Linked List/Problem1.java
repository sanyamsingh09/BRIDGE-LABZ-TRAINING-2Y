class StudentNode {
    int rollNumber, age;
    String name, grade;
    StudentNode next;

    public StudentNode(int roll, String n, int a, String g) {
        rollNumber = roll;
        name = n;
        age = a;
        grade = g;
        next = null;
    }
}

class StudentManager {
    private StudentNode head;

    // Add student at end
    public void addStudent(int roll, String name, int age, String grade) {
        StudentNode newNode = new StudentNode(roll, name, age, grade);

        if (head == null) {
            head = newNode;
            return;
        }

        StudentNode current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
    }

    // Delete student by roll number
    public void deleteStudent(int roll) {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }

        if (head.rollNumber == roll) {
            head = head.next;
            System.out.println("Student deleted");
            return;
        }

        StudentNode current = head;
        while (current.next != null && current.next.rollNumber != roll) {
            current = current.next;
        }

        if (current.next == null) {
            System.out.println("Student not found");
        } else {
            current.next = current.next.next;
            System.out.println("Student deleted");
        }
    }

    // Search student
    public StudentNode search(int roll) {
        StudentNode current = head;
        while (current != null) {
            if (current.rollNumber == roll)
                return current;
            current = current.next;
        }
        return null;
    }

    // Update grade
    public void updateGrade(int roll, String newGrade) {
        StudentNode student = search(roll);
        if (student != null) {
            student.grade = newGrade;
            System.out.println("Grade updated");
        } else {
            System.out.println("Student not found");
        }
    }

    // Display all students
    public void displayAll() {
        if (head == null) {
            System.out.println("No students found");
            return;
        }

        StudentNode current = head;
        while (current != null) {
            System.out.println("Roll: " + current.rollNumber +
                    ", Name: " + current.name +
                    ", Age: " + current.age +
                    ", Grade: " + current.grade);
            current = current.next;
        }
    }

    // Print single student
    public void printStudent(int roll) {
        StudentNode student = search(roll);
        if (student == null) {
            System.out.println("Student not found");
        } else {
            System.out.println("Roll: " + student.rollNumber +
                    ", Name: " + student.name +
                    ", Age: " + student.age +
                    ", Grade: " + student.grade);
        }
    }
}

public class Main {
    public static void main(String[] args) {

        StudentManager sm = new StudentManager();

        // Add students
        sm.addStudent(1, "Sanyam", 20, "A");
        sm.addStudent(2, "Rahul", 21, "B");
        sm.addStudent(3, "Amit", 22, "C");

        System.out.println("All Students:");
        sm.displayAll();

        // Search
        System.out.println("\nSearch Roll 2:");
        sm.printStudent(2);

        // Update
        System.out.println("\nUpdating Grade:");
        sm.updateGrade(2, "A+");
        sm.printStudent(2);

        // Delete
        System.out.println("\nDeleting Roll 1:");
        sm.deleteStudent(1);

        System.out.println("\nFinal List:");
        sm.displayAll();
    }
}