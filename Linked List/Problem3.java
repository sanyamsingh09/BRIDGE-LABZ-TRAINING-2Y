class TaskScheduler {
    private TaskNode head, tail, currentTask;

    public void addTask(int id, String name, int p, String date) {
        TaskNode newNode = new TaskNode(id, name, p, date);
        if (head == null) {
            head = tail = newNode;
            newNode.next = head;
            currentTask = head;
        } else {
            tail.next = newNode;
            tail = newNode;
            tail.next = head;
        }
    }

    public void moveNext() {
        if (currentTask != null)
            currentTask = currentTask.next;
    }

    public void printCurrentTask() {
        if (currentTask == null) {
            System.out.println("No task available");
            return;
        }
        System.out.println("ID: " + currentTask.taskId +
                ", Name: " + currentTask.taskName +
                ", Priority: " + currentTask.priority +
                ", Due: " + currentTask.dueDate);
    }
}

public class Main {   
    public static void main(String[] args) {

        TaskScheduler ts = new TaskScheduler();

        ts.addTask(1, "Coding", 1, "2026-04-01");
        ts.addTask(2, "Study", 2, "2026-04-02");

        ts.printCurrentTask();
        ts.moveNext();
        ts.printCurrentTask();
    }
}