class TaskNode {
    int taskId, priority;
    String taskName, dueDate;
    TaskNode next;

    public TaskNode(int id, String name, int p, String date) {
        taskId = id; taskName = name; priority = p; dueDate = date;
    }
}

class TaskScheduler {
    private TaskNode head, tail, currentTask;

    public void addTask(int id, String name, int p, String date) {
        TaskNode newNode = new TaskNode(id, name, p, date);
        if (head == null) {
            head = tail = newNode;
            newNode.next = head;
        } else {
            tail.next = newNode;
            tail = newNode;
            tail.next = head; // Maintain circular property
        }
    }

    public void moveNext() {
        if (currentTask == null && head != null) currentTask = head;
        else if (currentTask != null) currentTask = currentTask.next;
    }

    public TaskNode viewCurrentTask() {
        return currentTask;
    }
}