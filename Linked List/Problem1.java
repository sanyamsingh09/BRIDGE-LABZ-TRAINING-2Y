class StudentNode {
    int rollNumber, age;
    String name, grade;
    StudentNode next;

    public StudentNode(int roll, String n, int a, String g) {
        rollNumber = roll; name = n; age = a; grade = g;
    }
}

class StudentManager {
    private StudentNode head;

    public void addStudent(int roll, String name, int age, String grade) {
        StudentNode newNode = new StudentNode(roll, name, age, grade);
        if (head == null) {
            head = newNode;
            return;
        }
        StudentNode current = head;
        while (current.next != null) current = current.next;
        current.next = newNode;
    }

    public void deleteStudent(int roll) {
        if (head == null) return;
        if (head.rollNumber == roll) {
            head = head.next;
            return;
        }
        StudentNode current = head;
        while (current.next != null && current.next.rollNumber != roll) {
            current = current.next;
        }
        if (current.next != null) current.next = current.next.next;
    }

    public StudentNode search(int roll) {
        StudentNode current = head;
        while (current != null) {
            if (current.rollNumber == roll) return current;
            current = current.next;
        }
        return null;
    }

    public void updateGrade(int roll, String newGrade) {
        StudentNode student = search(roll);
        if (student != null) student.grade = newGrade;
    }
}