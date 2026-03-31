class ProcessNode {
    int processId, burstTime, remainingTime, priority;
    ProcessNode next;

    public ProcessNode(int id, int bt, int p) {
        processId = id;
        burstTime = bt;
        remainingTime = bt;
        priority = p;
        next = null;
    }
}

class RoundRobinSimulator {
    private ProcessNode head, tail;

    // Add process (circular list)
    public void addProcess(int id, int bt, int p) {
        ProcessNode newNode = new ProcessNode(id, bt, p);

        if (head == null) {
            head = tail = newNode;
            newNode.next = head;
        } else {
            tail.next = newNode;
            tail = newNode;
            tail.next = head;
        }
    }

    // Round Robin Scheduling
    public void simulate(int timeQuantum) {
        if (head == null) {
            System.out.println("No processes to execute");
            return;
        }

        if (timeQuantum <= 0) {
            System.out.println("Invalid Time Quantum");
            return;
        }

        ProcessNode current = head;
        ProcessNode prev = tail;

        while (head != null) {

            // Execute process
            System.out.println("Executing Process " + current.processId);
            current.remainingTime -= timeQuantum;

            // If process finished
            if (current.remainingTime <= 0) {
                System.out.println("Process " + current.processId + " Finished");

                // Only one node left
                if (current == current.next) {
                    head = tail = null;
                    break;
                }

                // Remove node
                prev.next = current.next;

                if (current == head)
                    head = current.next;

                if (current == tail)
                    tail = prev;

                current = current.next; // move forward after deletion
            } else {
                // Move normally
                prev = current;
                current = current.next;
            }
        }

        System.out.println("All processes completed");
    }
}

public class Main {
    public static void main(String[] args) {

        RoundRobinSimulator rr = new RoundRobinSimulator();

        // Add processes (id, burst time, priority)
        rr.addProcess(1, 10, 1);
        rr.addProcess(2, 5, 2);
        rr.addProcess(3, 8, 1);

        // Simulate with time quantum
        rr.simulate(3);
    }
}